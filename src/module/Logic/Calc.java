package module.Logic;

import module.Score.ScoreArray;
import module.Score.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Random;

/**
 * Created by Biko K. & Ali S. on 23.12.2015.
 * Calc contains the actually game logic and drawing of the tiles in the gui.
 */
public class Calc extends JPanel {
    private static final Color BG_COLOR = new Color (0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 64;
    private static final int TILES_MARGIN = 16;
    public static int range = 4; //standard value of the numbers that can spawn when tile movement is done. Standard 4 means that even values including 4 can be spawned(2,4)
    public static int spawnRate = 2;
    private static int tableSize = 4; //size of the table, sides are proportional
    public int[][] table; //{{8, 4, 0, 2}, {0, 0, 2, 0}, {0, 0, 2, 2}, {0, 2, 0, 2}}; remove or keep initialization as comment depending on if you want specific or general testing
    private int scoreValue;
    private boolean moved = true;
    private int guiX;
    private int guiY;
    public boolean eOG = false;


    /**
     * Constructor used for creating a new Playing field
     **/
    public Calc(int tableSize) {
        setTableSize (tableSize);
        guiX = 0;
        guiY = 0;

        table = new int[tableSize][tableSize];
/**     at the start of the game, *spawnrate* values are set **/
        initializeValue (range, spawnRate);


        setFocusable (true);
        addKeyListener (new KeyAdapter () {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode () == KeyEvent.VK_ESCAPE) {

                }
                switch (e.getKeyCode ()) { // delivers you which key was pressed
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        onKeyPressLeftNew ();
                        break;

                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        onKeyPressRightNew ();
                        break;

                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        onKeyPressDownNew ();
                        break;

                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        onKeyPressUpNew ();
                        break;
                }
                    repaint ();
            }


        });

    }

    /**
     * Constructor used for using the field u have previously created and just continue playing
     **/
    public Calc(boolean useBackUp) throws IOException, ClassNotFoundException {
        ScoreArray backup = readStatus ();
        System.out.println (readStatus ().toString ());
        table = readStatus ().getTable ();
        tableSize = readStatus ().getTable ().length;


/**     at the start of the game, spawnRate values are set **/


        setFocusable (true);
        addKeyListener (new KeyAdapter () {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode () == KeyEvent.VK_ESCAPE) {
                    resetGame ();
                }
                switch (e.getKeyCode ()) { // delivers you which key was pressed
                    case KeyEvent.VK_LEFT:

                        onKeyPressLeftNew ();
                        break;
                    case KeyEvent.VK_RIGHT:
                        onKeyPressRightNew ();

                        break;
                    case KeyEvent.VK_DOWN:

                        onKeyPressDownNew ();
                        break;
                    case KeyEvent.VK_UP:

                        onKeyPressUpNew ();
                        break;

                }
                repaint ();
            }


        });

    }

    public int getGuiX() {
        return guiX;
    }

    public void setGuiX(int guiX) {
        this.guiX = guiX;
    }

    public int getGuiY() {
        return guiY;

    }

    public void setGuiY(int guiY) {
        this.guiY = guiY;
    }

    /**
     * Getters and Setters
     */


    public static int getSpawnRate() {
        return spawnRate;
    }

    public static void setSpawnRate(int spawnRate) {
        Calc.spawnRate = spawnRate;
    }

    public static int getTableSize() {
        return tableSize;
    }

    public static boolean setTableSize(int tableSize) {
        if (tableSize <= 25) {
            Calc.tableSize = tableSize;
            return true;
        } else
            System.out.println ("Only values up to 25 are allowed");
        return false;
    }

    public static Color getBgColor() {
        return BG_COLOR;
    }

    public static String getFontName() {
        return FONT_NAME;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getTilesMargin() {
        return TILES_MARGIN;
    }

    /**
     * Tile Back & Foreground
     */


    public static Color getBackground(int value) {

        switch (value) {

            case 2:
                return new Color (0xeee4da);
            case 4:
                return new Color (0xede0c8);
            case 8:
                return new Color (0xf2b179);
            case 16:
                return new Color (0xf59563);
            case 32:
                return new Color (0xf67c5f);
            case 64:
                return new Color (0xf65e3b);
            case 128:
                return new Color (0xedcf72);
            case 256:
                return new Color (0xedcc61);
            case 512:
                return new Color (0xedc850);
            case 1024:
                return new Color (0xedc53f);
            case 2048:
                return new Color (0xedc22e);

        }
        return new Color (0xcdc1b4);
//        return new Color (value * new Random ().nextInt (100) * 100);
    }


    public static Color getForeground(int value) {
        return value < 16 ? new Color (0x776e65) : new Color (0xf9f6f2);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
    }

    /**
     * Randomly creates a position in the array and tries to insert even number into position.
     *
     * @return true if position is assigned with value, else false if not.
     */
    public boolean initializeValue(int range, int spawnRate) {
        if (endOfGame () || !moved) {
            return false;
        }
        Calc.range = range;
        while (spawnRate > 0) {
            int rRow = new Random ().nextInt (table.length);
            int rCol = new Random ().nextInt (table.length);

            if (table[rRow][rCol] == 0) { //if the game is not over, we insert a new value
                table[rRow][rCol] = returnEvenNumber (range);
            } else {
                if (!(table[rRow][rCol] == 0) && availableField ()) //if the field is already occupied && another field still empty, we call the method again and look for an empty field
                    return initializeValue (range, spawnRate);

            }

            spawnRate--;
        }
        return false;
    }

    /**
     * checks table for empty field, if not available, end the game
     */
    public boolean endOfGame() {
        int counter = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) {
                    return false;
                }
                if (i > 0 && j < table[i].length - 1)
                    if (table[i][j] == table[i - 1][j] || table[i][j] == table[i][j + 1] || table[i][j] == table[i - 1][j] || table[i][j] == table[i - 1][j])
                        counter++;
            }
        }
        if (counter > 0) {
            System.out.println ("continue game!");
            return false;
        }
        System.out.print ("Game Over");
        eOG = true;
        return true;
    }


    public boolean availableField() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void resetGame() {

        table = new int[tableSize][tableSize];
        initializeValue (range, spawnRate);
    }

    /**
     * Creates even random number for table
     *
     * @return even number;
     */
    private int returnEvenNumber(int range) {
        int random;
        int i = 0;
        if (range > 2) {
            this.range = range;
            while (i == 0) {
                random = new Random ().nextInt (range + 1);
                if (random % 2 == 0) i = random;
            }
            return i;
        } else {
            System.out.println ("minimum range of 2 is required");
        }
        return 0;
    }

    public static int getRange() {
        return range;
    }

    public static void setRange(int range) {
        Calc.range = range;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;

    }

    /**
     * Representation of a previous way to actually move the tiles. Was a lot too performace heavy for such a simple game.
     * There the entire concept of shifting the fields got "refurbished".
     **/
    @Deprecated
    public void onKeyPressUp() {
        int k = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] > 0 && i - 1 >= 0) {
                    if (table[i - 1][j] == table[i][j]) { // if they are the same
                        table[i - 1][j] += table[i][j];
                        table[i][j] = 0;
                    } else if (table[i - 1][j] == 0) {
                        k = i - 1;
                        while (k >= 0) {


                            if (table[k][j] == table[i][j]) {
                                table[k][j] += table[k][j];
                                table[i][j] = 0;
                                continue;

                            }
                            if (!(table[k][j] == 0)) {
                                k++;
                                break;
                            }

                            if (k == 0) break;
                            if (table[k][j] == 0) k--;

                        }

                        table[k][j] = table[i][j];
                        table[i][j] = 0;

                    }
                }
            }
        }
        initializeValue (range, spawnRate);
    }


    public void onKeyPressLeftNew() {
        rotate ();
        rotate ();
        mergeLine ();
        rotate ();
        rotate ();
    }

    public void onKeyPressRightNew() {
        mergeLine ();
        initializeValue (range, spawnRate);
    }

    public void onKeyPressUpNew() {
        rotate ();
        rotate ();
        rotate ();
        mergeLine ();
        rotate ();

    }

    public void onKeyPressDownNew() {
        rotate ();
        mergeLine ();
        rotate ();
        rotate ();
        rotate ();
    }

    public boolean isEmpty(int row) {

        int counter = 0;
        if (row < table.length) {
            for (int i = 0; i < table[row].length; i++) {
                if (table[row][i] > 0) {
                    return false;
                }
            }
        } else {
            System.err.println ("row must be smaller than the size of the array!");
        }
        return true;

    }

    /**
     * rotates the array counter clockwise
     * eg. [0] [2] = [2] [4]
     *     [0] [4]   [0] [0]
     */
    public void rotate() {
        final int w = table.length;
        final int h = table[0].length;
        int[][] ret = new int[h][w];
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                ret[i][j] = table[j][h - i - 1];
            }
        }
        table = ret;
    }

    /**
     * mergeLine() is basically a moveRight method, but by rotating the array it creates
     * the impression as if you would move the tiles in any direction:
     */
    public void mergeLine() {
        moved = false;
        int temp = 1;
        int innerTemp = 0;
        for (int i = 0; i < table.length; i++) {
            if (!isEmpty (i)) {

                for (int j = 0; j + 1 < table[i].length; j++) {

                    if (table[i][j] > 0 && j + 1 < table[i].length) {//if the field's value is higher than 0 and the next field is still within the array
                        temp = j + 1;

                        if (table[i][j] == table[i][temp]) {// if the field and the next field have the same value
                            table[i][temp] += table[i][j];
                            scoreValue += table[i][temp];
                            table[i][j] = 0;
                            j = temp;
                            moved = true;

                        } else if (table[i][temp] == 0) {//if the next field is zero
                            innerTemp = temp;
                            while (innerTemp + 1 < table[i].length && table[i][innerTemp] == 0) {//move to the last available empty field
                                innerTemp++;
                            }
                            if (table[i][innerTemp] == table[i][j]) {
                                table[i][innerTemp] += table[i][j];
                                scoreValue += table[i][innerTemp];
                                table[i][j] = 0;
                                j = innerTemp;
                                moved = true;
                            } else if (table[i][innerTemp] == 0) {
                                table[i][innerTemp] = table[i][j];
                                table[i][j] = 0;
                                moved = true;
                            } else { //if the innerTemp value has another value than the moved tile, shift the tile to the last empty field;
                                table[i][innerTemp - 1] = table[i][j];
                                table[i][j] = 0;
                                moved = true;
                            }
                        }
                    }

                }


                for (int emptyFiller = table[i].length - 1; emptyFiller > 0; emptyFiller--) {
                    if (table[i][emptyFiller] == 0) {
                        int tempValue = emptyFiller;
                        while (table[i][tempValue] == 0 && tempValue > 0) {
                            tempValue--;
                        }
                        table[i][emptyFiller] = table[i][tempValue];
                        table[i][tempValue] = 0;

                    }

                }


            }
        }
        initializeValue (range, spawnRate);
    }

    /**
     * Drawing the playing field
     **/
    @Override
    public void paint(Graphics g) {

        g.setColor (BG_COLOR);
        g.fillRect (0, 0, this.getSize ().width, this.getSize ().height);
        for (int x = 0; x < getTableSize (); x++) {
            for (int y = 0; y < getTableSize (); y++) { //every single tile gets drawn separately
                drawTile (g, x, y);

            }
        }
    }

    private void drawTile(Graphics g2, int x, int y) {

        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint (RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        int value = table[y][x];
        int xOffset = offsetCoors (x);
        int yOffset = offsetCoors (y);
        final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
        g.setColor (getBackground (table[y][x]));
        g.fillRoundRect (xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
        g.setColor (getForeground (table[y][x]));

        final Font font = new Font (FONT_NAME, Font.BOLD, size);
        g.setFont (font);

        String s = String.valueOf (value);
        final FontMetrics fm = getFontMetrics (font);

        final int w = fm.stringWidth (s);
        final int h = -(int) fm.getLineMetrics (s, g).getBaselineOffsets ()[2];

        if (value != 0) g.drawString (s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
        g.setFont (new Font (FONT_NAME, Font.PLAIN, 18));
        g.drawString ("Score: " + scoreValue, guiX + 50, guiY + 15);
        if (ScoreBoard.scoreBoard.isEmpty ()) g.drawString ("HighScore: " + 0, guiX + 150, guiY + 15);
        else if (scoreValue > ScoreBoard.scoreBoard.get (0).getScore ())
            g.drawString ("HighScore: " + scoreValue, guiX + 150, guiY + 15);
        else g.drawString ("HighScore: " + ScoreBoard.scoreBoard.get (0).getScore (), guiX + 150, guiY + 15);

        if (eOG) {
            System.out.println ("enter the if condiition");
            g.setColor (new Color (255, 255, 255, 30));
            g.fillRect (0, 0, getWidth (), getHeight ());
            g.setColor (new Color (78, 139, 202));
            g.setFont (new Font (FONT_NAME, Font.BOLD, 48));
//

            g.drawString ("Game over!", 50, 130);
            g.drawString ("You lose!", 64, 200);

            g.setFont (new Font (FONT_NAME, Font.PLAIN, 16));
            g.setColor (new Color (128, 128, 128, 128));
            g.drawString ("Press the " + "X" + "to play again", 80, getHeight () - 40);
        }


    }


    /**
     * readStatus() saves the necessary informations that are individual for each instance(Score,Table).
     * That's what the ScoreArray Class is used for.
     *
     * @return ScoreArray backup;
     **/

    public ScoreArray readStatus() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream (new FileInputStream ("save.ser"));
        ScoreArray backup = (ScoreArray) ois.readObject ();
        ois.close ();
        System.out.println ("Serialized figures read");
        return backup;
    }

    public void saveStatus() throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream ("save.ser"));
        oos.writeObject (new ScoreArray (scoreValue, table));
        oos.close ();
        System.out.println ("figures serialized");
    }


    /**
     * Table is printed into the terminal
     */
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print ("[" + table[i][j] + "]");
            }
            System.out.println ();
        }
    }

    @Override
    public String toString() {
        return "Calc{" +
                "tableSize=" + tableSize +
                ", range=" + range +
                '}';
    }
}







