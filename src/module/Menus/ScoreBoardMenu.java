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
        frame.pack();
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

        GridLayout layout = new GridLayout(ScoreBoard.scoreBoard.size() + 1, 3);
        layout.setVgap(10); // vertical spacing
        panel = new JPanel(layout);
        JLabel nameTitle_JLB = new JLabel("Name");
        nameTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel scoreTitle_JLB = new JLabel("Score");
        scoreTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel timeTitle_JLB = new JLabel("Time");
        timeTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);

        // sets the titles to bold
        Font font = nameTitle_JLB.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());

        nameTitle_JLB.setFont(boldFont);
        scoreTitle_JLB.setFont(boldFont);
        timeTitle_JLB.setFont(boldFont);

        // adding the title "bar"
        panel.add(nameTitle_JLB);
        panel.add(scoreTitle_JLB);
        panel.add(timeTitle_JLB);


        for (Score score : ScoreBoard.scoreBoard) {
            JLabel name_JLB = new JLabel (score.getName1 ());
            name_JLB.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel score_JLB = new JLabel ("" + score.getScore ());
            score_JLB.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel time_JLB = new JLabel (score.getGlobalTime());
            time_JLB.setHorizontalAlignment(SwingConstants.CENTER);

            panel.add(name_JLB);
            panel.add(score_JLB);
            panel.add(time_JLB);

        }
    }
}






