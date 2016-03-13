package module.Menus;

import module.Score.Score;
import module.Score.ScoreBoard;
import module.Score.ScoreBoardEmptyException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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


    public Menu() {
        JFrame frame = new JFrame("Menu");

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

                    GameGui gameGui = new GameGui();


                } else {
                    try {
                        GameGui gameGui = new GameGui(true);
                    } catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(frame,
                                "No Backup available.",
                                "NoBackupError",
                                JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(frame,
                                "No Backup available.",
                                "NoBackupError",
                                JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        JOptionPane.showMessageDialog(frame,
                                "No Backup available.",
                                "NoBackupError",
                                JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }


            }
        });
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsMenu oM = new OptionsMenu();
                frame.setState(Frame.NORMAL);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(-11);
            }
        });

        frame.setLocationRelativeTo(null);
        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ScoreBoard.add(new Score("huhu", 4, 19));
                    ScoreBoard.initialize();
                    ScoreBoardMenu sBM = new ScoreBoardMenu();
                } catch (IOException | ScoreBoardEmptyException | NullPointerException | ClassNotFoundException e1) {
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
        Menu menu = new Menu();
    }


    private void createUIComponents() {

        try {
            label1 = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/banner.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


