package coffee.view;

import javax.swing.*;
import coffee.controller.UserRepository;
import coffee.model.RoundedBoot;
import java.awt.*;

public class Registration extends JFrame {

    public Registration() {
        setTitle("COFFEE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 

        //===================================== Background panel ========================================

        JLabel background = createBackground();
        setContentPane(background);
        background.setLayout(new GridBagLayout());

       // ============================= Create a transparent panel for the form =======================

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setPreferredSize(new Dimension(400, 320));

        JLabel title = new JLabel("REGISTRATION", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel("Username: ");
        lblUser.setFont(new Font("Arial", Font.BOLD, 18));
        lblUser.setForeground(Color.white);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField fieldUser = new JTextField();
        fieldUser.setFont(new Font("Arial", Font.BOLD, 15));
        fieldUser.setMaximumSize(new Dimension(300, 30));
        fieldUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPass = new JLabel("Password: ");
        lblPass.setFont(new Font("Arial", Font.BOLD, 18));
        lblPass.setForeground(Color.white);
        lblPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField fieldPass = new JPasswordField();
        fieldPass.setFont(new Font("Arial", Font.BOLD, 15));
        fieldPass.setMaximumSize(new Dimension(300, 30));
        fieldPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbnSignUp = new RoundedBoot("SIGN UP");
        jbnSignUp.setBackground(Color.white);
        jbnSignUp.setFont(new Font("Arial", Font.PLAIN, 20));
        jbnSignUp.setForeground(Color.black);
        jbnSignUp.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jbnCancel = new JButton("CANCEL");
        jbnCancel.setContentAreaFilled(false);
        jbnCancel.setBorderPainted(false);
        jbnCancel.setFocusPainted(false);
        jbnCancel.setOpaque(false);
        jbnCancel.setForeground(Color.white);
        jbnCancel.setFont(new Font("Arial", Font.ITALIC, 15));
        jbnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        formPanel.add(jbnSignUp);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(jbnCancel);
        background.add(formPanel, new GridBagConstraints()); // Adds the centralized form panel

        // ========================================== Actions ==========================================
        
        jbnCancel.addActionListener(e -> {
            new LoginScreen();
            dispose();
        });

        jbnSignUp.addActionListener(e -> {
            String username = fieldUser.getText();
            String password = new String(fieldPass.getPassword());

            if (UserRepository.userExists(username)) {
                JOptionPane.showMessageDialog(this, "User already exists! Choose another name.");
            } else {
                UserRepository.saveUser(username, password);
                fieldUser.setText("");
                fieldPass.setText("");
                JOptionPane.showMessageDialog(this, "Successfully registered!");
            }
        });

        setVisible(true);
    }

    //============================== Use the user's screen size to resize the background =======================

    private JLabel createBackground() {
        String path = "C:/cafeteria/imagens/backBlack.png";
        ImageIcon icon = new ImageIcon(path);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image imageResized = icon.getImage().getScaledInstance(
                screenSize.width, screenSize.height, Image.SCALE_SMOOTH
        );
        JLabel label = new JLabel(new ImageIcon(imageResized));
        label.setSize(screenSize);
        return label;
    }

    // ============================== Main Method =======================================
    // This is just for testing purposes, you can remove it later.

    public static void main(String[] args) {
        new Registration();
    }
}

