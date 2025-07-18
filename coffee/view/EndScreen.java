package coffee.view;

import javax.swing.*;

import coffee.model.JLabelShadow;
import coffee.model.RoundedBoot;

import java.awt.*;

public class EndScreen extends JFrame {

    public EndScreen(String client) {
        setTitle("END");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //============================== Panel with background image ==================================

        JLabel background = createBackgroundLabel();
        setContentPane(background);
        background.setLayout(new GridBagLayout()); 

        // ============================= Create a transparent panel for the form =======================

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Transparent!
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setPreferredSize(new Dimension(600, 550));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int largura = screenSize.width;
        int altura = screenSize.height;
        
        int tituloLargura = 700;
        JLabel title = new JLabel("APROVED!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 80));
        title.setForeground(Color.white);
        setBounds((largura - tituloLargura) / 2, (int) (altura * 0.06), tituloLargura, 40);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        JLabel lblSuccessful = new JLabelShadow("ORDER SUCCESSFUL!");
        lblSuccessful.setBounds(100, 700, 500, 30);
        lblSuccessful.setFont(new Font("Arial", Font.ITALIC, 25));
        lblSuccessful.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccessful.setForeground(Color.WHITE);
        lblSuccessful.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        JLabel lblPrepared = new JLabel(client + " your order is being prepared!");
        lblPrepared.setBounds(160, 480, 500, 50);
        lblPrepared.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrepared.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrepared.setForeground(Color.white);
        lblPrepared.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbnFinish = new RoundedBoot("Finish");
        int btnWidth = 400, btnHeight = 100;
        jbnFinish.setBounds((largura - btnWidth) / 2, (int)(altura * 0.7), btnWidth, btnHeight);
        jbnFinish.setFont(new Font("Arial", Font.PLAIN, 20));
        jbnFinish.setBackground(Color.DARK_GRAY);
        jbnFinish.setForeground(Color.white);
        jbnFinish.setHorizontalAlignment(SwingConstants.CENTER);
        jbnFinish.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Image for the cup
        ImageIcon iconHome = new ImageIcon("C:/cafeteria/imagens/cup.png");
        Image img = iconHome.getImage().getScaledInstance(300, 280, Image.SCALE_SMOOTH);
        JLabel lblCup = new JLabel(new ImageIcon(img));
        lblCup.setBounds(largura - 65, 504, 38, 38);
        lblCup.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================================================ Panel =========================================
       
        background.add(formPanel, new GridBagConstraints());
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(4));
        formPanel.add(lblSuccessful);
        formPanel.add(Box.createVerticalStrut(0));
        formPanel.add(lblCup);
        formPanel.add(Box.createVerticalStrut(0));
        formPanel.add(lblPrepared);
        formPanel.add(Box.createVerticalStrut(25));
        formPanel.add(jbnFinish);

        //================================================== Actions ========================================

        jbnFinish.addActionListener(e -> {
            for (Window w : Window.getWindows()) {
                w.dispose(); }
                new StartScreen();
        });

        setVisible(true);
    }

    // ================== Creates the JLabel with the background image resized for the screen ================

        private JLabel createBackgroundLabel() {
            // Takes the screen resolution
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            // Absolute or relative image path
            String path = "C:/cafeteria/imagens/back.png";
            ImageIcon icon = new ImageIcon(path);
            Image imageResized = icon.getImage().getScaledInstance(screen.width, screen.height, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(imageResized));
    }
    
   
    // ============================================== Main Method ==============================================
    // This is just for testing purposes, you can remove it later.


        public static void main(String[] args) {
        new EndScreen("Renato"); // or any test name
    }

}

       


