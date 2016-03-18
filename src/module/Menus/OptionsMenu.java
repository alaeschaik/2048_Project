package module.Menus;

import module.Logic.Calc;
import module.Score.Score;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by Biko on 12.03.2016.
 */
public class OptionsMenu {
    private JTextPane sizeOfTheFieldTextPane;
    private JSlider sizeSlider;
    private JPanel jpanel;
    private JTextPane newTilesAfterEveryTextPane;
    private JSlider spawnSlider;
    private JLabel ImageLabel;
    private JTextPane rangeOfValuesThatTextPane;
    private JSlider rangeSlider;
    private Score score;

    public OptionsMenu() {
        JFrame frame = new JFrame("Menu");

        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        rangeSlider.addChangeListener(e -> Calc.setRange(rangeSlider.getValue()));
        sizeSlider.addChangeListener(e -> Calc.setTableSize(sizeSlider.getValue()));
        spawnSlider.addChangeListener(e -> Calc.setSpawnRate(spawnSlider.getValue()));
    }


    public static void main(String[] args) {
        OptionsMenu oM = new OptionsMenu();
    }


    private void createUIComponents() {
        try {
            ImageLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/banner.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rangeSlider = new JSlider(2, 128, 2);


    }
}
