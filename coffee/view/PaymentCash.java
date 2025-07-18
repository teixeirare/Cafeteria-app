package coffee.view;

import javax.swing.*;
import coffee.controller.RequestController;
import coffee.model.JLabelShadow;
import coffee.model.RoundedBoot;
import java.awt.*;

public class PaymentCash extends JFrame {

    public PaymentCash(JFrame previousScreen, RequestController requestController) {
        setTitle("CASH PAYMENT");
        setSize(720, 480); // Proportion for the new image
        setLocationRelativeTo(null); // Center on the screen
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // =================================== Image background panel  ===================================

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        // ==================================== Centralized transparent panel =============================

        JPanel paymentPanel = new JPanel(null);
        paymentPanel.setOpaque(false);
        paymentPanel.setBounds(0, 0, 720, 480);
        backgroundPanel.add(paymentPanel);

        // ========================================== Centralized title ====================================

        JLabel lblTitle = new JLabel("CASH PAYMENT", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE); // Café claro
        lblTitle.setBounds(0, 60, 720, 45);
        paymentPanel.add(lblTitle);

        // ============================================= LABELS AND FIELDS ===================================

        JLabel lblValue = new JLabelShadow("Value:");
        lblValue.setFont(new Font("Arial", Font.ITALIC, 25));
        lblValue.setForeground(Color.WHITE);
        lblValue.setBounds(210, 145, 150, 32);
        paymentPanel.add(lblValue);

        JTextField fieldValue = new JTextField();
        fieldValue.setBounds(320, 145, 100, 30);
        fieldValue.setFont(new Font("Arial", Font.PLAIN, 20));
        fieldValue.setHorizontalAlignment(SwingConstants.CENTER);
        fieldValue.setOpaque(true);
        fieldValue.setForeground(Color.BLACK);
        fieldValue.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        paymentPanel.add(fieldValue);

        JLabel lblChange = new JLabelShadow("Change:");
        lblChange.setFont(new Font("Arial", Font.ITALIC, 25));
        lblChange.setForeground(Color.WHITE);
        lblChange.setBounds(210, 200, 120, 28);
        paymentPanel.add(lblChange);

        JLabel changeResult = new JLabel("$ 0.00", SwingConstants.LEFT);
        changeResult.setFont(new Font("Arial", Font.ITALIC, 21));
        changeResult.setForeground(Color.WHITE);
        changeResult.setBounds(320, 200, 160, 28);
        paymentPanel.add(changeResult);

        // ============================================== Buttons =========================================

        int botY = 300, width = 130, height = 25, spacing = 90;
        int cx = 670 / 2;
        JButton jbnCalculate = new RoundedBoot("CALCULATE");
        jbnCalculate.setBounds(cx - width - spacing, botY, width, height);
        jbnCalculate.setFont(new Font("Arial", Font.PLAIN, 13));
        paymentPanel.add(jbnCalculate);

        JButton jbnBack = new RoundedBoot("BACK");
        jbnBack.setBounds(cx - width/2, botY, width, height);
        jbnBack.setFont(new Font("Arial", Font.PLAIN, 13));
        paymentPanel.add(jbnBack);

        JButton jbnPaid = new RoundedBoot("PAID");
        jbnPaid.setBounds(cx + spacing, botY, width, height);
        jbnPaid.setFont(new Font("Arial", Font.PLAIN, 13));
        jbnPaid.setEnabled(false);
        paymentPanel.add(jbnPaid);

        // =========================================== Acions ===============================================

        jbnCalculate.addActionListener(e -> {
            try {
                float valueReceived = Float.parseFloat(fieldValue.getText().replace(',', '.'));
                float change = valueReceived - requestController.calculateTotal();

                if (change < 0) {
                    JOptionPane.showMessageDialog(this, "Not enough.", "Error", JOptionPane.ERROR_MESSAGE);
                    changeResult.setText("$ 0.00");
                    jbnPaid.setEnabled(false);
                } else {
                    changeResult.setText(String.format("$ %.2f", change));
                    jbnPaid.setEnabled(true);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Type a valid value.", "Error", JOptionPane.ERROR_MESSAGE);
                jbnPaid.setEnabled(false);
            }
        });

        jbnBack.addActionListener(e -> dispose());

        jbnPaid.addActionListener(e -> {
            try {
                float valueReceived = Float.parseFloat(fieldValue.getText().replace(',', '.'));
                float total = requestController.calculateTotal();
                float change = valueReceived - total;

                requestController.setPaymentMethod("CASH");
                requestController.setValueReceived(valueReceived);
                requestController.setChange(change);

                requestController.generateAndSetOrderNumber();
                requestController.saveReceiptToHtmlFile();
                requestController.printReceiptComplete();
                requestController.printPreparationList();

                new coffee.view.EndScreen(requestController.getClient());
                if (previousScreen != null) previousScreen.dispose();
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Type a valid value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

      // ================================= Background panel for a 720x480 window =================================

     static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;
        public BackgroundPanel() {
            String path = "C:/cafeteria/imagens/back.png";
            ImageIcon icon = new ImageIcon(path);
            backgroundImage = icon.getImage().getScaledInstance(
                720, 480, Image.SCALE_SMOOTH
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

    public static void main(String[] args) {
        JFrame fakePreviousScreen = null;
        RequestController fakeRequestController = new RequestController();
        new PaymentCash(fakePreviousScreen, fakeRequestController);
    }
}




