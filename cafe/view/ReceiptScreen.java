package cafe.view;

import cafe.controller.RequestController;
import cafe.model.RoundedBoot;

import javax.swing.*;
import java.awt.*;

public class ReceiptScreen extends JFrame {

    private final RequestController requestController;

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
        

        // Texto com recibo

        int width = 500;
        int height = 500;
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;

        JLabel receiptText = new JLabel(requestController.generateReceiptHTML(), SwingConstants.CENTER);
        receiptText.setBounds(x, y, width, height);
        receiptText.setFont(new Font("Arial", Font.PLAIN, 15));
        receiptText.setForeground(Color.white);
        receiptText.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(receiptText);

        // Botões de pagamento
        JButton jbnCredit = new RoundedBoot("CREDIT");
        jbnCredit.setBounds( 200, 680, 130, 30);
        jbnCredit.setBackground(Color.white);
        jbnCredit.setForeground(Color.black);
        jbnCredit.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton jbnDebit = new RoundedBoot("DEBIT");
        jbnDebit.setBounds(340, 680,130, 30);
        jbnDebit.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnDebit.setHorizontalAlignment(SwingConstants.CENTER);
        jbnDebit.setBackground(Color.white);
        jbnDebit.setForeground(Color.black);

        JButton jbnCash =new RoundedBoot("CASH");
        jbnCash.setBounds(480, 680,130, 30);
        jbnCash.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnCash.setBackground(Color.white);
        jbnCash.setForeground(Color.black);

        JButton jbnPay = new RoundedBoot("PAY");
        jbnPay.setBounds(270, 720,130, 30);
        jbnPay.setFont(new Font("Arial", Font.PLAIN, 15));
        //botaoPagar.setBackground(Color.white);
        //botaoPagar.setForeground(Color.black);

        JButton jbnBack = new RoundedBoot("BACK");
        jbnBack.setBounds(430, 720,130, 30);
        jbnBack.setFont(new Font("Arial", Font.PLAIN, 15));
        //botaoCancelar.setBackground(Color.white);
        // botaoCancelar.setForeground(Color.black);

        jbnPay.setEnabled(false);

        add(panel);
        panel.add(jbnCredit);
        panel.add(jbnDebit);
        panel.add(jbnCash);
        panel.add(jbnPay);
        panel.add(jbnBack);

        JLabel image = createImage();
        panel.add(image);

        // ==========================================================================================

        jbnBack.addActionListener(e -> dispose());

        jbnCredit.addActionListener(e -> {
            highlightSelected(jbnCredit, jbnDebit, jbnCash);
            jbnPay.setEnabled(true);
        });

        jbnDebit.addActionListener(e -> {
            highlightSelected(jbnDebit, jbnCredit, jbnCash);
            jbnPay.setEnabled(true);
        });

        jbnCash.addActionListener(e -> {
            PaymentCash windowMoney = new PaymentCash(this, requestController);
            highlightSelected(jbnCash, jbnCredit, jbnDebit);
            windowMoney.setLocation(getX(), getY());
            jbnPay.setEnabled(false);
        });

        jbnPay.addActionListener(e -> {
            new EndScreen(requestController.getClient());
            dispose();
        });

        setVisible(true);
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
        String path = "C:/dev/JS/treinamento_java/cafeteria/imagens/fundo.jpg";
        ImageIcon icon = new ImageIcon(path);
        Image imageResized = icon.getImage().getScaledInstance(800, 820, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imageResized));
        label.setBounds(0, 0, 800, 820);
        return label;
    }
}


