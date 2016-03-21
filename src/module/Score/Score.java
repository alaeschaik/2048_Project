package module.Score;

import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Biko on 12.03.2016.
 */
public class Score extends JPanel {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String LocalTime = dateFormat.format(new Date());

    int score = 0;
    String name;
    int tableSize;
    String serverTime;

    /**
     * Constructor for creating local score where no time is set
     *
     * @param name
     * @param tableSize
     * @param score
     **/
    public Score(String name, int tableSize, int score) {
        this.tableSize = tableSize;
        this.score = score;
        this.name = name;
    }

    /**
     * Constructor used for recieving date from the server (http:biko.wolko.at) as it also transmits the time the score was set.
     *
     * @param name
     * @param tableSize
     * @param score
     * @param serverTime
     */
    public Score(String name, int tableSize, int score, String serverTime) {
        this.tableSize = tableSize;
        this.score = score;
        this.name = name;
        this.serverTime = serverTime;
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

    public void setName1(String name) {
        this.name = name;

    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public String getLocalTime() {
        return dateFormat.format(new Date());
    }

    public String getGlobalTime() {
        return serverTime;
    }

    @Override
    public String toString() {

        return "Score:" +
                " Name: " + name +
                " Size: " + tableSize +
                " Time: " + getLocalTime() +
                " Score: " + score + "\n";
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
