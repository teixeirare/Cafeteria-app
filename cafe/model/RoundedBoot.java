package cafe.model;

import javax.swing.*;
import java.awt.*;

public class RoundedBoot extends JButton {

    public RoundedBoot(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.black); // Cor do texto
        setBackground(Color.WHITE); // Cor do fundo
        setFont(new Font("Arial", Font.BOLD, 14));
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // 30 = raio do arredondamento
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Opcional: desenhar borda
    }
}

