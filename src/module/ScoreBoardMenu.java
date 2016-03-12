package module;

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

    public ScoreBoardMenu() {

        JFrame frame = new JFrame("Menu");

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

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
        JTextField TextFieldlol = new JTextField();

        panel = new JPanel();

        for (Score e : ScoreBoard.scoreBoard) {
            ScoreBoard.printScoreBoard();
            for (Score score : ScoreBoard.scoreBoard) {
                System.out.println("huhu");

            }
        }
    }

}




