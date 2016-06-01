package module.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.io.IOException;

public class Test2 extends JLabel {

    public Test2() {
    }

    public void paint(Graphics g) {
        String text = "Hello World";
        int x = 10;
        int y = 100;

        Font font = new Font("Georgia", Font.ITALIC, 50);
        Graphics2D g1 = (Graphics2D) g;

        TextLayout textLayout = new TextLayout(text, font, g1.getFontRenderContext());
        g1.setPaint(new Color(150, 150, 150));
        textLayout.draw(g1, x + 3, y + 3);

        g1.setPaint(Color.BLACK);
        textLayout.draw(g1, x, y);
    }

    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame();
        f.add(new Test2());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(450, 150);

        f.setVisible(true);

    }
}










