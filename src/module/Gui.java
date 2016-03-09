package module;

import javax.swing.*;

/**
 * Created by Biko on 03.03.2016.
 */
public class Gui extends JPanel {


    public static void main(String[] args) {
        Calc game2048 = new Calc(2);
        game2048.printTable();
        JFrame game = new JFrame();
        game.setTitle("2048 Game");
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(400,400);
        game.setResizable(false);
        game.setVisible(true);
        game.add(game2048);

        game.setLocationRelativeTo(null); // centers the game at start


        System.out.println (game2048.getTableSize ());
    }

}
