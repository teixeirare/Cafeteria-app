package coffee.view;

import javax.swing.*;
import java.awt.*;
import coffee.model.RoundedBoot;
import coffee.controller.UserRepository;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("COFFE SHOP - LOGIN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //============================== Panel with background image ==================================

        JLabel background = createBackgroundLabel();
        setContentPane(background);
        background.setLayout(new GridBagLayout());// Centers the formPanel

        // ============================= Create a transparent panel for the form =======================
        
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false); // Transparent!
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setPreferredSize(new Dimension(400, 320));

        JLabel title = new JLabel("LOG IN", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 18));
        lblUser.setForeground(Color.white);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField fieldUser = new JTextField(16);
        fieldUser.setFont(new Font("Arial", Font.BOLD, 15));
        fieldUser.setMaximumSize(new Dimension(300, 30));
        fieldUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.BOLD, 18));
        lblPass.setForeground(Color.white);
        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField fieldPass = new JPasswordField(16);
        fieldPass.setFont(new Font("Arial", Font.BOLD, 15));
        fieldPass.setMaximumSize(new Dimension(300, 30));
        fieldPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbnLogin = new RoundedBoot("LOG IN");
        jbnLogin.setBackground(Color.DARK_GRAY);
        jbnLogin.setFont(new Font("Arial", Font.PLAIN, 20));
        jbnLogin.setForeground(Color.white);
        jbnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbnRegistry = new JButton("REGISTRATION");
        jbnRegistry.setContentAreaFilled(false);
        jbnRegistry.setBorderPainted(false);
        jbnRegistry.setFocusPainted(false);
        jbnRegistry.setOpaque(false);
        jbnRegistry.setForeground(Color.white);
        jbnRegistry.setFont(new Font("Arial", Font.ITALIC, 15));
        jbnRegistry.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ========================================== Panel ===========================================

        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(35));
        formPanel.add(lblUser);
        formPanel.add(fieldUser);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(lblPass);
        formPanel.add(fieldPass);
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(jbnLogin);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(jbnRegistry);
        background.add(formPanel, new GridBagConstraints()); // Adds the centralized form panel

        // ========================================== Actions ==========================================

        jbnRegistry.addActionListener(e -> {
            new Registration();
            dispose();
        });

        jbnLogin.addActionListener(e -> {
            String username = fieldUser.getText();
            String password = new String(fieldPass.getPassword());

            if (UserRepository.validateUser(username, password)) {
                new StartScreen();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
                fieldUser.setText("");
                fieldPass.setText("");
            }
        });

        setVisible(true);
    }

    //  ================= Creates the JLabel with the background image resized for the screen =================
    
        private JLabel createBackgroundLabel() {
        // Takes the screen resolution
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // Absolute or relative image path
        String path = "C:/cafeteria/imagens/back.png";
        ImageIcon icon = new ImageIcon(path);
        Image imageResized = icon.getImage().getScaledInstance(screen.width, screen.height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(imageResized));
    }

}






