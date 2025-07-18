package coffee.view;

import javax.swing.*;
import coffee.controller.RequestController;
import coffee.model.RoundedBoot;
import coffee.receipt.ReceiptGenerator;

import java.awt.*;

public class ReceiptScreen extends JFrame {

    private final RequestController requestController;
    private JLabel receiptText; 

    public ReceiptScreen(RequestController requestController) {
        this.requestController = requestController;
        initialize();
    }

    private void initialize() {
        setTitle("RECEIPT");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //===================================== Background panel =============================================

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // =========================== HTML receipt generated with utility class =============================

        String html = ReceiptGenerator.generateHTML(
            requestController.getCart(),
            requestController.getClient(),
            requestController.getOrderNumber(),
            requestController.getPaymentMethod(),
            requestController.getValueReceived(),
            requestController.getChange(),
            requestController.calculateTotal()
        );
        receiptText = new JLabel(html, SwingConstants.CENTER);
        receiptText.setFont(new Font("Arial", Font.PLAIN, 15));
        receiptText.setForeground(Color.white);
        receiptText.setHorizontalAlignment(SwingConstants.CENTER);
        receiptText.setVerticalAlignment(SwingConstants.TOP);
        receiptText.setBounds(width/2 - 350, height/2 - 420, 700, 620); // centered
        backgroundPanel.add(receiptText);

        // ========================== Payment buttons, centered below the receipt =========================

        int btnW = 100, btnH = 25;
        int spacing = 20;
        int baseY = height/2 + 180;

        JButton jbnCredit = new RoundedBoot("CREDIT");
        jbnCredit.setBounds(width/2 - btnW - btnW/2 - spacing, baseY, btnW, btnH);
        jbnCredit.setFont(new Font("Arial", Font.PLAIN, 13));
       

        JButton jbnDebit = new RoundedBoot("DEBIT");
        jbnDebit.setBounds(width/2 - btnW/2, baseY, btnW, btnH);
        jbnDebit.setFont(new Font("Arial", Font.PLAIN, 13));
        

        JButton jbnCash = new RoundedBoot("CASH");
        jbnCash.setBounds(width/2 + btnW/2 + spacing, baseY, btnW, btnH);
        jbnCash.setFont(new Font("Arial", Font.PLAIN, 13));
        

        JButton jbnPay = new RoundedBoot("PAY");
        jbnPay.setBounds(width/2 - btnW - spacing/2, baseY + 65, btnW, btnH);
        jbnPay.setFont(new Font("Arial", Font.BOLD, 13));
        backgroundPanel.add(jbnPay);

        JButton jbnBack = new RoundedBoot("BACK");
        jbnBack.setBounds(width/2 + spacing/2, baseY + 65, btnW, btnH);
        jbnBack.setFont(new Font("Arial", Font.BOLD, 13));
        backgroundPanel.add(jbnBack);

        jbnPay.setEnabled(false);

        backgroundPanel.add(jbnCredit);
        backgroundPanel.add(jbnDebit);
        backgroundPanel.add(jbnCash);

        // =========================================== Acions ===============================================

        jbnBack.addActionListener(e -> dispose());

        jbnCredit.addActionListener(e -> {
            requestController.setPaymentMethod("CREDIT");
            requestController.setValueReceived(null);
            requestController.setChange(null);
            highlightSelected(jbnCredit, jbnDebit, jbnCash);
            jbnPay.setEnabled(true);
            updateReciboNaTela(); 
        });

        jbnDebit.addActionListener(e -> {
            requestController.setPaymentMethod("DEBIT");
            requestController.setValueReceived(null);
            requestController.setChange(null);
            highlightSelected(jbnDebit, jbnCredit, jbnCash);
            jbnPay.setEnabled(true);
            updateReciboNaTela();
        });

        jbnCash.addActionListener(e -> {
            PaymentCash windowMoney = new PaymentCash(this, requestController);
            highlightSelected(jbnCash, jbnCredit, jbnDebit);
            windowMoney.setLocation(width/2 - 370, baseY + -619);
            jbnPay.setEnabled(false); 
        });

        jbnPay.addActionListener(e -> {
            requestController.generateAndSetOrderNumber();
            updateReciboNaTela();
            requestController.saveReceiptToHtmlFile();
            requestController.printReceiptComplete();
            requestController.printPreparationList();

            new EndScreen(requestController.getClient());
            dispose();
        });

        setVisible(true);
    }

    private void updateReciboNaTela() {
        String html = ReceiptGenerator.generateHTML(
            requestController.getCart(),
            requestController.getClient(),
            requestController.getOrderNumber(),
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

    // ======================= Customized background panel to fill the entire screen! ========================
    
    static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;
        public BackgroundPanel() {
            String path = "C:/cafeteria/imagens/back.png";
            ImageIcon icon = new ImageIcon(path);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            backgroundImage = icon.getImage().getScaledInstance(
                screenSize.width, screenSize.height, Image.SCALE_SMOOTH
            );
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    // ============================================== Main Method ============================================
    // This is just for testing purposes, you can remove it later.

    public static void main(String[] args) {
        RequestController fakeRequestController = new RequestController();
        new ReceiptScreen(fakeRequestController);
    }
}



