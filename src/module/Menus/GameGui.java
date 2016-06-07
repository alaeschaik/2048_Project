package module.Menus;

import module.Logic.Calc;
import module.Score.Score;
import module.Score.ScoreBoard;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;


import static module.Menus.Tiledrawer.*;

/**
 * Created by Biko on 03.03.2016.
 */
public class GameGui extends JFrame {
    Tiledrawer game2048;


    public GameGui(boolean useBackup) throws IOException, ClassNotFoundException {

        game2048 = new Tiledrawer(useBackup,this);
        createGame();

    }

    public GameGui() {
        game2048 = new Tiledrawer(Calc.getTableSize(),this);
        createGame();

    }

    private void createGame() {

        setTitle("2048 Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Calc.getTableSize() * (getTileSize() + getTilesMargin()) + getTilesMargin() + 10, Calc.getTableSize() * (getTileSize() + getTilesMargin() + getTilesMargin()));
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0,(Calc.getTableSize() * (getTileSize() + getTilesMargin()) + getTilesMargin() + 10), Calc.getTableSize() * (getTileSize() + getTilesMargin() + getTilesMargin()), 25, 25));
        setResizable(false);
        setVisible(true);
        add(game2048);
        game2048.setGuiX(getX());
        game2048.setGuiY(getY());
        setLocationRelativeTo(null); // centers the game at start
        addWindowListener(new WindowAdapter() {
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
        int choice = JOptionPane.showOptionDialog(this,
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
            choice = JOptionPane.showOptionDialog(this,
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
                ScoreBoard.add(new Score(response.toString(), Calc.getTableSize(), game2048.getGame2048().getScoreValue()));
            }
        } else if (choice == 0) {
            System.out.println("lol");
            game2048.getGame2048().saveStatus();

        }

    }

    public void dispose()
    {
        try {
            scoreWindow();

        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        } finally {
            System.out.println("Closed");
            super.dispose();

        }
    }
}
