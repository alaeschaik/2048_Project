package module.Menus;

import module.Logic.Calc;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton backButton;

    public OptionsMenu() {

        JFrame frame = new JFrame("Menu");
        spawnSlider.setValue(Calc.getSpawnRate());
        sizeSlider.setValue(Calc.getTableSize());
        rangeSlider.setValue(Calc.getRange());
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton) { // delete the comment to making some change tp push
                    frame.dispose();
                }
            }
        });
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
