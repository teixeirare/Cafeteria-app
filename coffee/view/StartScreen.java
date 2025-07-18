package coffee.view;

import javax.swing.*;
import coffee.model.RoundedBoot;
import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        initialize();
    }

    private void initialize() {
        setTitle("COFFEE");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        //===================================== Background panel ========================================

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // ================================= Exit Button (top right) ====================================

        ImageIcon iconHome = new ImageIcon("C:/cafeteria/imagens/exit.png");
        Image img = iconHome.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH);
        JButton jbnExit = new JButton(new ImageIcon(img));
        jbnExit.setBounds(width - 75, 15, 50, 50);
        jbnExit.setContentAreaFilled(false);
        jbnExit.setBorderPainted(false);
        jbnExit.setFocusPainted(false);
        jbnExit.setToolTipText("Exit");
        backgroundPanel.add(jbnExit);

        // ================================== Start Order Button (center) =================================

        JButton jbnStart = new RoundedBoot("Start your order");
        int btnWidth = 300, btnHeight = 55;
        jbnStart.setBounds((width - btnWidth) / 2, (int)(height * 0.7), btnWidth, btnHeight);
        jbnStart.setBackground(Color.darkGray);
        jbnStart.setFont(new Font("Arial", Font.PLAIN, 22));
        jbnStart.setForeground(Color.white);
        backgroundPanel.add(jbnStart);

        // ========================================== Actions =============================================

        jbnExit.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Do you want to go out?" ,
             "Go out", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                new LoginScreen();
                dispose();
            }
        });

        jbnStart.addActionListener(e -> {
            new OrderScreen();
            dispose();
        });

        setVisible(true);
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
        new StartScreen();
    }
}

