package module.Menus;

import module.Logic.Calc;
import module.Score.ScoreBoard;
import module.Score.ScoreBoardEmptyException;
import module.Server.ServerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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


    public Menu() throws UnsupportedLookAndFeelException {
        JFrame frame = new JFrame("Menu");
        UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
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
                        ScoreBoard.initializeGlobal(Calc.getTableSize());

                    } catch (IOException | ClassNotFoundException | ServerException e1) {
                        JOptionPane.showMessageDialog(frame,
                                "No entrys for this tablesize yet. Scoreboard will be initialized with 0 as highscore",
                                "NoScoreBoardEntrysError",
                                JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();

                    }finally {
                        new GameGui();
                    }


                } else {
                    try {
                        new GameGui(true);
                    } catch (IOException | ClassNotFoundException e1) {
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

                    ScoreBoard.initializeLocal();
                    ScoreBoard.sort();
                    ScoreBoardMenu sBM = new ScoreBoardMenu();
                } catch (IOException | ScoreBoardEmptyException | NullPointerException | ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(frame,
                            "No Scoreboard Entrys.",
                            "NoScoreBoardEntrysError",
                            JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();


                }
            } else {
                try {
                    ScoreBoardMenu sBM = new ScoreBoardMenu();
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


    public static void main(String[] args) {
        try {
            Menu menu = new Menu();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }


    private void createUIComponents() {

        try {
            label1 = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/banner.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


