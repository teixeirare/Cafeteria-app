package cafe.model;

import javax.swing.*;
import java.awt.*;

public class JLabelComSombra extends JLabel {

    public JLabelComSombra(String texto) {
        super(texto);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 15));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        FontMetrics fm = g2.getFontMetrics();
        int x = 0;
        int y = fm.getAscent();

        // Sombra
        g2.setColor(Color.GRAY);
        g2.drawString(getText(), x + 0, y + 0);

        // Texto principal
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);

        g2.dispose();
    }
}


