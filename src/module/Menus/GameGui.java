package module.Menus;

import module.Logic.Calc;
import module.Score.Score;
import module.Score.ScoreBoard;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static module.Logic.Calc.getTileSize;
import static module.Logic.Calc.getTilesMargin;

/**
 * Created by Biko on 03.03.2016.
 */
public class GameGui {
    Calc game2048;
    JFrame game;

    public GameGui(boolean useBackup) throws IOException, ClassNotFoundException {

        game2048 = new Calc(useBackup);
        createGame();
        game2048.setGameFrame(game);
    }

    public GameGui() {
        game2048 = new Calc(Calc.getTableSize());
        createGame();

    }

    private void createGame() {
        game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        game.setSize(Calc.getTableSize() * (getTileSize() +getTilesMargin()) +getTilesMargin() + 10, Calc.getTableSize() * (Calc.getTileSize() + Calc.getTilesMargin() + Calc.getTilesMargin()));
        game.setResizable(false);
        game.setVisible(true);
        game.add(game2048);
        game2048.setGuiX(game.getX());
        game2048.setGuiY(game.getY());
        game.setLocationRelativeTo(null); // centers the game at start
        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    scoreWindow();

                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                } finally {
                    System.out.println("Closed");
                    e.getWindow().dispose();

                }

            }
        });
    }

    private void scoreWindow() throws IOException, ClassNotFoundException {
        Object[] options = {"Save", "stop and enter into scoreboard"};
        int choice = JOptionPane.showOptionDialog(game,
                "Would you like to save your current progress",
                "Create Backup?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
        if (choice == 1) {
            System.out.println("lol");
            options = new Object[]{"Save", "Discard"};
            choice = JOptionPane.showOptionDialog(game,
                    "Would you like to enter the Score into the scoreboard?",
                    "Scoreboard entry?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
            if (choice == 0) {
                String response = JOptionPane.showInputDialog(null,
                        "What is your name?",
                        "Enter your name",
                        JOptionPane.QUESTION_MESSAGE);
                ScoreBoard.add(new Score(response.toString(), Calc.getTableSize(), game2048.getScoreValue()));
            }
        } else if(choice==0) {
            System.out.println("lol");
            game2048.saveStatus();

        }

    }
}
