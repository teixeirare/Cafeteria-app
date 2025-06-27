package coffee.view;

import javax.swing.*;

import coffee.controller.RequestController;
import coffee.model.RoundedBoot;
import coffee.receipt.ReceiptGenerator;

import java.awt.*;

public class ReceiptScreen extends JFrame {

    private final RequestController requestController;
    private JLabel receiptText; // Agora campo da classe

    public ReceiptScreen(RequestController requestController) {
        this.requestController = requestController;
        initialize();
    }

    private void initialize() {
        setTitle("RECEIPT");
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 820);
        panel.setBackground(new Color(222, 184, 135));

        // Recibo HTML gerado com utilitário
        String html = ReceiptGenerator.generateHTML(
            requestController.getCart(),
            requestController.getClient(),
            requestController.getOrderNumber(),
            requestController.getCnpj(),
            requestController.getPaymentMethod(),
            requestController.getValueReceived(),
            requestController.getChange(),
            requestController.calculateTotal()
        );
        receiptText = new JLabel(html, SwingConstants.CENTER);
        receiptText.setBounds(100, 120, 600, 500); // Ajuste conforme layout
        receiptText.setFont(new Font("Arial", Font.PLAIN, 13));
        receiptText.setForeground(Color.white);
        receiptText.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(receiptText);

        // Botões de pagamento
        JButton jbnCredit = new RoundedBoot("CREDIT");
        jbnCredit.setBounds(200, 680, 130, 30);
        jbnCredit.setBackground(Color.white);
        jbnCredit.setForeground(Color.black);
        jbnCredit.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton jbnDebit = new RoundedBoot("DEBIT");
        jbnDebit.setBounds(340, 680, 130, 30);
        jbnDebit.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnDebit.setHorizontalAlignment(SwingConstants.CENTER);
        jbnDebit.setBackground(Color.white);
        jbnDebit.setForeground(Color.black);

        JButton jbnCash = new RoundedBoot("CASH");
        jbnCash.setBounds(480, 680, 130, 30);
        jbnCash.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnCash.setBackground(Color.white);
        jbnCash.setForeground(Color.black);

        JButton jbnPay = new RoundedBoot("PAY");
        jbnPay.setBounds(270, 720, 130, 30);
        jbnPay.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton jbnBack = new RoundedBoot("BACK");
        jbnBack.setBounds(430, 720, 130, 30);
        jbnBack.setFont(new Font("Arial", Font.PLAIN, 15));

        jbnPay.setEnabled(false);

        add(panel);
        panel.add(jbnCredit);
        panel.add(jbnDebit);
        panel.add(jbnCash);
        panel.add(jbnPay);
        panel.add(jbnBack);

        JLabel image = createImage();
        panel.add(image);

        // =========== EVENTOS ==============
        jbnBack.addActionListener(e -> dispose());

        jbnCredit.addActionListener(e -> {
            requestController.setPaymentMethod("CREDIT");
            requestController.setValueReceived(null);
            requestController.setChange(null);
            highlightSelected(jbnCredit, jbnDebit, jbnCash);
            jbnPay.setEnabled(true);
            atualizarReciboNaTela();
        });

        jbnDebit.addActionListener(e -> {
            requestController.setPaymentMethod("DEBIT");
            requestController.setValueReceived(null);
            requestController.setChange(null);
            highlightSelected(jbnDebit, jbnCredit, jbnCash);
            jbnPay.setEnabled(true);
            atualizarReciboNaTela();
        });

        jbnCash.addActionListener(e -> {
            PaymentCash windowMoney = new PaymentCash(this, requestController);
            highlightSelected(jbnCash, jbnCredit, jbnDebit);
            windowMoney.setLocation(getX(), getY());
            jbnPay.setEnabled(false); // Só libera o PAY quando pago em cash
        });

        jbnPay.addActionListener(e -> {
            // Gera número do pedido na hora da finalização
            requestController.generateAndSetOrderNumber();

            // Atualiza recibo na tela com o número do pedido antes de imprimir/salvar
            atualizarReciboNaTela();

            // Salva, imprime, gera tela final
            requestController.saveReceiptToHtmlFile();
            requestController.printReceiptComplete();
            requestController.printPreparationList();

            new EndScreen(requestController.getClient());
            dispose();
        });

        setVisible(true);
    }

    private void atualizarReciboNaTela() {
        // Sempre exibe a versão mais atual do recibo
        String html = ReceiptGenerator.generateHTML(
            requestController.getCart(),
            requestController.getClient(),
            requestController.getOrderNumber(),
            requestController.getCnpj(),
            requestController.getPaymentMethod(),
            requestController.getValueReceived(),
            requestController.getChange(),
            requestController.calculateTotal()
        );
        receiptText.setText(html);
    }

    private void highlightSelected(JButton selected, JButton... others) {
        selected.setBackground(Color.darkGray);
        selected.setForeground(Color.white);
        for (JButton other : others) {
            other.setBackground(Color.white);
            other.setForeground(Color.black);
        }
    }

    private JLabel createImage() {
        String path = "C:/cafeteria/imagens/fundo.jpg";
        ImageIcon icon = new ImageIcon(path);
        Image imageResized = icon.getImage().getScaledInstance(800, 820, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imageResized));
        label.setBounds(0, 0, 800, 820);
        return label;
    }
}


