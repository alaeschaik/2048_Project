package module.Menus;

import module.Logic.Calc;
import module.Score.ScoreBoardEmptyException;
import module.Server.ServerException;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.io.File;
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


    public Menu() throws UnsupportedLookAndFeelException {
        JFrame frame = new JFrame("Menu");
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        startGameButton.addActionListener(e -> {
            try {
                playClickSound();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            }
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
        });


        optionsButton.addActionListener(e -> {
            try {
                playClickSound();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            }
            OptionsMenu oM = new OptionsMenu();
            frame.setState(Frame.NORMAL);
        });


        frame.setLocationRelativeTo(null);
        scoreboardButton.addActionListener(e -> {
            try {
                playClickSound();
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            }
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


        exitButton.addActionListener(
                e ->

                {
                    try {
                        playClickSound();
                    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
                        e1.printStackTrace();
                    }

                    System.exit(-11);
                }

        );
    }

    public static void main(String[] args) {
        try {
            new Menu();
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

    private void playClickSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/resources/click2.wav").getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-25.0f);
        clip.start();
    }


}


