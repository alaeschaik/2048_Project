package module.Score;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Biko on 12.03.2016.
 */
public class Score extends JPanel {
    DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
    String date = dateFormat.format (new Date ());

    int score = 0;
    String name;
    int tableSize;


    public Score(String name, int tableSize, int score) {
        this.tableSize = tableSize;

        this.score = score;
        this.name = name;
    }


    public int getScore() {
        return score;
    }

    public Score setScore(int score) {
        this.score = score;
        return this;
    }

    public String getName1() {
        return name;
    }

    public Score setName1(String name) {
        this.name = name;
        return this;
    }

    public int getTableSize() {
        return tableSize;
    }

    public Score setTableSize(int tableSize) {
        this.tableSize = tableSize;
        return this;
    }

    @Override
    public String toString() {
        return "Score:" +
                " Name: '" + name +
                " Size: " + tableSize +
                " Time: " + date +
                " Score: " + score +
                '\'';
    }

    //TODO: make the painting functional
    @Override
    public void paint(Graphics g2) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        g.setBackground(new Color(255, 102, 210));
        g.fillRect(100, 100, 40, 40);
        g.drawString("huhu", 200, 200);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw Text
        g.drawString("This is my custom Panel!", 10, 20);
    }


}
