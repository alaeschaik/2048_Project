package module.Menus;

import module.Score.Score;
import module.Score.ScoreBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreBoardMenu {
    private JLabel ImageLabel;
    private JScrollBar scrollBar1;
    JFrame frame;
    private JPanel panel;

    public ScoreBoardMenu() throws IOException, ClassNotFoundException {
        createUIComponents ();
        frame = new JFrame ("Menu");

        frame.setContentPane (panel);
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.setSize (500, 500);
        frame.setLocationRelativeTo (null);
        frame.setVisible (true);

    }


    public static void main(String[] args) {
        try {
            ScoreBoardMenu oM = new ScoreBoardMenu ();
        } catch (IOException e) {
            e.printStackTrace ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }


    private void createUIComponents() throws IOException, ClassNotFoundException {
        try {
            ImageLabel = new JLabel (new ImageIcon (ImageIO.read (getClass ().getResource ("/resources/crown.jpg"))));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        JTextField TextFieldScore = new JTextField ();
        panel = new JPanel (new BorderLayout ());
        for (Score score : ScoreBoard.scoreBoard) {
            JLabel name_JLB = new JLabel (score.getName1 ());
            JLabel score_JLB = new JLabel ("" + score.getScore ());
            JLabel time_JLB = new JLabel (score.getDateFormat ());

            panel.add (name_JLB, BorderLayout.CENTER);
            panel.add (score_JLB, BorderLayout.CENTER);
            panel.add (time_JLB, BorderLayout.CENTER);
        }
        Score test = new Score ("name", 10, 10);

//        test.paint ();
    }
}






