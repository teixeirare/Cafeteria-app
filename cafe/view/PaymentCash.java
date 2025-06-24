package cafe.view;

import cafe.controller.RequestController;
//import cafe.model.Pedido;
import cafe.model.RoundedBoot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentCash extends JFrame {

    public PaymentCash(JFrame previousScreen, RequestController requestController) {
        setTitle("CASH PAYMENT");
        setSize(800, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(222, 184, 135));
        panel.setBounds(0, 0, 800, 150);
        add(panel);

        JLabel lblValue = new JLabel("VALUE");
        lblValue.setBounds(10, 15, 150, 25);
        lblValue.setFont(new Font("Arial", Font.PLAIN, 18));
        lblValue.setForeground(Color.white);
        panel.add(lblValue);

        JTextField fieldValue = new JTextField();
        fieldValue.setBounds(150, 15, 100, 25);
        fieldValue.setFont(new Font("Arial", Font.PLAIN, 17));
        panel.add(fieldValue);

        JButton jbnCalculate = new RoundedBoot("Calculate");
        jbnCalculate.setBounds(270, 15, 130, 30);
        jbnCalculate.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnCalculate.setBackground(Color.white);
        jbnCalculate.setForeground(Color.black);
        panel.add(jbnCalculate);

        JLabel lblChange = new JLabel("Change:");
        lblChange.setBounds(10, 50, 100, 25);
        lblChange.setFont(new Font("Arial", Font.PLAIN, 17));
        lblChange.setForeground(Color.white);
        panel.add(lblChange);

        JLabel changeResult = new JLabel("$ 0.00");
        changeResult.setBounds(150, 50, 100, 25);
        changeResult.setFont(new Font("Arial", Font.PLAIN, 17));
        changeResult.setForeground(Color.white);
        panel.add(changeResult);

        JButton jbnBack = new RoundedBoot("Back");
        jbnBack.setBounds(405, 15, 130, 30);
        jbnBack.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnBack.setBackground(Color.white);
        jbnBack.setForeground(Color.black);
        panel.add(jbnBack);

        JButton jbnPaid = new RoundedBoot("Paid");
        jbnPaid.setBounds(540, 15, 130, 30);
        jbnPaid.setFont(new Font("Arial", Font.PLAIN, 15));
        jbnPaid.setBackground(Color.white);
        jbnPaid.setForeground(Color.black);
        jbnPaid.setEnabled(false);
        panel.add(jbnPaid);

        JLabel image = createImage();
        panel.add(image);

        jbnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float valueReceived = Float.parseFloat(fieldValue.getText());
                    float change = valueReceived - requestController.getTotal();

                    if (change < 0) {
                        JOptionPane.showMessageDialog(null, "Not enough.", "Error", JOptionPane.ERROR_MESSAGE);
                        changeResult.setText("$ 0.00");
                        jbnPaid.setEnabled(false);
                    } else {
                        changeResult.setText(String.format("$ %.2f", change));
                        jbnPaid.setEnabled(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Type a valid value.", "Error", JOptionPane.ERROR_MESSAGE);
                    jbnPaid.setEnabled(false);
                }
            }
        });

        jbnBack.addActionListener(e -> dispose());
        
        

        jbnPaid.addActionListener(e -> {
            new EndScreen(requestController.getClient());
            previousScreen.dispose();
            dispose();
        });

        setVisible(true);
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
