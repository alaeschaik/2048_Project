package module.Menus;

import module.Logic.Calc;
import module.Score.ScoreBoardEmptyException;
import module.Server.ServerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static module.Score.ScoreBoard.*;

/**
 * Created by Biko on 12.03.2016.
 */
public class Menu {
    private JPanel panel1;
    private JButton startGameButton;
    private JButton exitButton;
    private JButton optionsButton;
    private JLabel label1;
    private JButton scoreboardButton;
    private JFrame frame;


    public Menu() throws UnsupportedLookAndFeelException {
        frame = new JFrame("Menu");

        $$$setupUI$$$();
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        startGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"new Game",
                        "Continue"};
                int choice = JOptionPane.showOptionDialog(frame,
                        "Would you like to use a Backup or create a new Game?",
                        "Choose",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,     //do not use a custom Icon
                        options,  //the titles of buttons
                        options[0]); //default button title

                if (choice == 0) {
                    try {
                        initializeGlobal(Calc.getTableSize());

                    } catch (IOException | ClassNotFoundException | ServerException e1) {
                        JOptionPane.showMessageDialog(frame,
                                "No entrys for this tablesize yet. Scoreboard will be initialized with 0 as highscore",
                                "NoScoreBoardEntrysError",
                                JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    } finally {
                        new GameGui();
                    }


                } else if (choice == 1) {
                    try {
                        initializeGlobal(Calc.getTableSize());
                        new GameGui(true);
                    } catch (IOException | ClassNotFoundException | ServerException e1) {
                        JOptionPane.showMessageDialog(frame,
                                "No Backup available.",
                                "NoBackupError",
                                JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();

                    }

                }
            }
        });
        optionsButton.addActionListener(e -> {
            OptionsMenu oM = new OptionsMenu();
            frame.setState(Frame.NORMAL);
        });

        exitButton.addActionListener(e -> System.exit(-11));

        frame.setLocationRelativeTo(null);
        scoreboardButton.addActionListener(e -> {
            Object[] options = {"Local Scoreboard",
                    "Global Scoreboard"};
            int choice = JOptionPane.showOptionDialog(frame,
                    "Would you like to use display the local or global Scoreboard?",
                    "Choose",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
            if (choice == 0) {
                try {
                    initializeLocal();
                    sort();
                    new ScoreBoardMenu(false);
                } catch (IOException | ScoreBoardEmptyException | NullPointerException | ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "No Scoreboard Entrys.",
                            "NoScoreBoardEntrysError",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();

                }
            } else if (choice == 1) {
                try {
                    new ScoreBoardMenu(true);
                } catch (IOException | NullPointerException | ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "No Scoreboard Entrys.",
                            "NoScoreBoardEntrysError",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
    }


    private void createUIComponents() {

        try {
            label1 = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/banner.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new Menu();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), 50, 10));
        panel1.setBackground(new Color(-720907));
        panel1.setEnabled(true);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(panel1.getFont().getName(), panel1.getFont().getStyle(), panel1.getFont().getSize()), new Color(-12862688)));
        exitButton = new JButton();
        exitButton.setEnabled(true);
        exitButton.setHideActionText(true);
        exitButton.setHorizontalAlignment(0);
        exitButton.setHorizontalTextPosition(0);
        exitButton.setText("Exit");
        exitButton.setToolTipText("");
        exitButton.setVerticalAlignment(0);
        panel1.add(exitButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 0, false));
        label1.setText("");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(0, 144), null, 0, false));
        optionsButton = new JButton();
        optionsButton.setEnabled(true);
        optionsButton.setHideActionText(true);
        optionsButton.setText("Options");
        panel1.add(optionsButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 0, false));
        startGameButton = new JButton();
        startGameButton.setBackground(new Color(-16187648));
        startGameButton.setEnabled(true);
        startGameButton.setForeground(new Color(-2304));
        startGameButton.setHideActionText(false);
        startGameButton.setHorizontalAlignment(0);
        startGameButton.setText("Start Game");
        startGameButton.putClientProperty("hideActionText", Boolean.FALSE);
        panel1.add(startGameButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 3, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 0, false));
        scoreboardButton = new JButton();
        scoreboardButton.setText("Scoreboard");
        panel1.add(scoreboardButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}


