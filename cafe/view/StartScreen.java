package cafe.view;

import javax.swing.*;

import cafe.model.RoundedBoot;

import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        initialize();
    }

    private void initialize() {
        setTitle("COFFEE");
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 820);
        panel.setBackground(Color.BLACK);
        add(panel);



        JButton jbnStart = new RoundedBoot("Start your order");

        jbnStart.setBounds(270, 550, 280, 50);
        jbnStart.setBackground(Color.DARK_GRAY);
        jbnStart.setFont(new Font("Arial", Font.PLAIN, 20));
        jbnStart.setForeground(Color.white);
        panel.add(jbnStart);

        JLabel image = createImage();
        panel.add(image);

        /*JButton telaPagamento = new BotaoArredondado("Finish");
        telaPagamento.setBounds(270, 550, 280, 50);
        telaPagamento.setFont(new Font("Arial", Font.PLAIN, 20));
        telaPagamento.setBackground(Color.DARK_GRAY);
        telaPagamento.setForeground(Color.white);
        telaPagamento.add(telaPagamento);*/

        jbnStart.addActionListener(e -> {
            new OrderScreen();
            dispose();
        });

        /*telaPagamento.addActionListener(e -> {
            new TelaPagamento(null);
            dispose();
        });*/

        setVisible(true);
    }

    private JLabel createImage() {
        String path = "C:/dev/JS/treinamento_java/cafeteria/imagens/logo.jpg";
        ImageIcon icon = new ImageIcon(path);
        Image imageResized = icon.getImage().getScaledInstance(800, 820, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imageResized));
        label.setBounds(0, 0, 800, 820);
        return label;
    }
}
