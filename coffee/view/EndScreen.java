package coffee.view;

import javax.swing.*;

import coffee.model.RoundedBoot;

import java.awt.*;
//import cafe.view.TelaInicio;

public class EndScreen extends JFrame {

    public EndScreen(String client) {
        setTitle("FINAL");
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 820);
        panel.setBackground(new Color(222, 184, 135));
        add(panel);

         
        JLabel lblAproved = new JLabel("APROVED!");
        lblAproved.setBounds(170, 340, 500, 70);
        lblAproved.setFont(new Font("Arial", Font.BOLD, 70));
        lblAproved.setHorizontalAlignment(SwingConstants.CENTER);
        lblAproved.setForeground(Color.WHITE);
        panel.add(lblAproved);

        JLabel lblSuccessful = new JLabel("ORDER SUCCESSFUL!");
        lblSuccessful.setBounds(250, 450, 320, 30);
        lblSuccessful.setFont(new Font("Arial", Font.BOLD, 20));
        lblSuccessful.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccessful.setForeground(Color.WHITE);
        panel.add(lblSuccessful);

        JLabel lblPrepared = new JLabel(client + " your order is being prepared!");
        lblPrepared.setBounds(160, 480, 500, 50);
        lblPrepared.setFont(new Font("Arial", Font.BOLD, 20));
        lblPrepared.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrepared.setForeground(Color.white);
        panel.add(lblPrepared);

        JButton jbnFinish = new RoundedBoot("Finish");
        jbnFinish.setBounds(270, 550, 280, 50);
        jbnFinish.setFont(new Font("Arial", Font.PLAIN, 20));
        jbnFinish.setBackground(Color.DARK_GRAY);
        jbnFinish.setForeground(Color.white);
        panel.add(jbnFinish);

        JLabel image = createImage();
        JLabel cup = createImage2();
        panel.add(image);
        panel.add(cup);
       

        

      
        //===========================================================================================================

        


        jbnFinish.addActionListener(e -> {
            for (Window w : Window.getWindows()) {
                w.dispose();
            }
            new StartScreen();
        });

        setVisible(true);
        
    }

    private JLabel createImage() {
        String path = "C:/cafeteria/imagens/fundo.jpg";
        ImageIcon icon = new ImageIcon(path);
        Image imageResized = icon.getImage().getScaledInstance(800, 820, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(imageResized));
        label.setBounds(0, 0, 800, 820);
        return label;
    }
    
    private JLabel createImage2() {
        String path = "C:/cafeteria/imagens/xicarates.jpg";
        ImageIcon icon = new ImageIcon(path);
        //Image imageResized = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(icon);
        label.setBounds(250, 250, 400, 400);
        return label;
    }

       
}

