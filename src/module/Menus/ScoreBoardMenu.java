package module.Menus;

import module.Score.Score;
import module.Score.ScoreBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreBoardMenu {
    private JLabel ImageLabel;
    private JPanel panel;
    private JScrollBar scrollBar1;
    JFrame frame;
    private JPanel panelTest;
    public ScoreBoardMenu() {

        frame = new JFrame("Menu");

        frame.setContentPane (panelTest);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        ScoreBoardMenu oM = new ScoreBoardMenu();
    }


    private void createUIComponents() throws IOException, ClassNotFoundException {
        try {
            ImageLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/crown.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JTextField TextFieldScore = new JTextField ();
        panelTest = new JPanel ();
        panel = new JPanel();
        panel.add (new JTextField ("uhu"));
        panelTest.add (new JTextField ("uhu"));
        for (Score score : ScoreBoard.scoreBoard) {
            panelTest.add (score);
            panelTest.add (new JTextArea (score.toString ()));
        }
        Score test = new Score ("name", 10, 10);

//        test.paint ();
    }
}






