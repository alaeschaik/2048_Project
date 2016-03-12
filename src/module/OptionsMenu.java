package module;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

    public OptionsMenu() {

        JFrame frame = new JFrame("Menu");

        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        rangeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Calc.setRange(rangeSlider.getValue());
            }
        });
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Calc.setTableSize(sizeSlider.getValue());
            }
        });
        spawnSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Calc.setSpawnRate(spawnSlider.getValue());
            }
        });
    }


    public static void main(String[] args) {
        OptionsMenu oM = new OptionsMenu();
    }


    private void createUIComponents() {
        ImageLabel = new JLabel(new ImageIcon("banner.png"));
        rangeSlider = new JSlider(2, 128, 2);


    }
}
