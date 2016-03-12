package module;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Biko on 03.03.2016.
 */
public class GameGui {
    Calc game2048;

    public GameGui(boolean useBackup) throws IOException, ClassNotFoundException {

        game2048 = new Calc(useBackup);


        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        game.setSize(game2048.getTableSize() * (Calc.getTileSize() + Calc.getTilesMargin() + Calc.getTilesMargin()), game2048.getTableSize() * (Calc.getTileSize() + Calc.getTilesMargin() + Calc.getTilesMargin()));
        game.setResizable(false);
        game.setVisible(true);
        game.add(game2048);
        ScoreBoard sB = new ScoreBoard();
        game.setLocationRelativeTo(null); // centers the game at start

        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    game2048.saveStatus();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Closed");
                e.getWindow().dispose();
            }
        });
    }


    public GameGui() {
        game2048 = new Calc(Calc.getTableSize());

        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        game.setSize(game2048.getTableSize() * (Calc.getTileSize() + Calc.getTilesMargin() + Calc.getTilesMargin()), game2048.getTableSize() * (Calc.getTileSize() + Calc.getTilesMargin() + Calc.getTilesMargin()));
        game.setResizable(false);
        game.setVisible(true);
        game.add(game2048);

        game.setLocationRelativeTo(null); // centers the game at start
        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ScoreBoard.add(new Score("huhu", 10));
                    game2048.saveStatus();

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } finally {
                    System.out.println("Closed");
                    e.getWindow().dispose();
                    ScoreBoard.printScoreBoard();
                }

            }
        });
    }


}
