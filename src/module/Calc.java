package module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Random;

/**
 * Created by Biko K. & Ali S. on 23.12.2015.
 */
public class Calc extends JPanel {
    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 64;
    private static final int TILES_MARGIN = 16;
    public static Calc Game2048;
    public int[][] table /*= {{4, 4, 0, 2}, {0, 0, 2, 0}, {0, 0, 2, 2}, {0, 2, 0, 2}}*/; //remove or keep initialization as comment depending on if you want specific or general testing
    public int range = 4; //standard value of the numbers that can spawn when tile movement is done. Standard 4 means that even values including 4 can be spawned(2,4)
    Calc temp = this;
    private int tableSize; //size of the table, sides are proportional

    public Calc(int tableSize) {
        setTableSize(tableSize);

        table = new int[tableSize][tableSize];
        //at the start of the game, 2 values are set
        initializeValue(range);
        initializeValue(range);
        initializeValue(range);


        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    resetGame();
                }
                switch (e.getKeyCode()) { // delivers you which key was pressed
                    case KeyEvent.VK_LEFT:
                        System.out.println("vk_left");
                        onKeyPressLeftNew();
                        break;
                    case KeyEvent.VK_RIGHT:
                        onKeyPressRightNew();
                        System.out.println("vk_right");
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("vk_down");
                        onKeyPressDownNew();
                        break;
                    case KeyEvent.VK_UP:
                        System.out.println("vk_up");
                        onKeyPressUpNew();
                        break;

                }
                repaint();
            }


        });

    }


    /**
     * Randomly creates a position in the array and tries to insert even number into position.
     *
     * @return true if position is assigned with value, else false if not.
     */
    public boolean initializeValue(int range) {
        this.range = range;
        int rRow = new Random().nextInt(table.length);
        int rCol = new Random().nextInt(table.length);

        if (table[rRow][rCol] == 0 && endOfGame()) { //if the game is not over, we insert a new value
            table[rRow][rCol] = returnEvenNumber(range);
            return true;
        } else if (!(table[rRow][rCol] == 0) && endOfGame()) //if the field is already occupied & game not over, we call the method again and look for an empty field
        {
            return initializeValue(range);
        } else { //if the game is over
            System.out.println("GAME OVER");
        }
        return false;
    }


    /**
     * checks table for empty field, if not available, end the game
     */
    public boolean endOfGame() {
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
    }

    /**
     * Creates even random number for table
     *
     * @return even number;
     */
    public int returnEvenNumber(int range) {
        int random;
        int i = 0;
        if (range > 2) {
            this.range = range;
            while (i == 0) {
                random = new Random().nextInt(range + 1);
                if (random % 2 == 0) i = random;
            }
            return i;
        } else {
            System.out.println("minimum range of 2 is required");
        }
        return 0;
    }


    /**
     * Getters and Setters
     *
     * @return
     */
    public int getTableSize() {
        return tableSize;
    }

    public boolean setTableSize(int tableSize) {
        if (tableSize < 10) {
            this.tableSize = tableSize;
            return true;
        } else
            System.out.println("Only values up to 10 are allowed");
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
     * Moving methods
     **/

    public void onKeyPressLeftNew() {
        rotate();
        rotate();
        for (int i = 0; i < table.length; i++) {
            if (!isEmpty(i)) {
                mergeLine(i);
            }
        }
        rotate();
        rotate();
        initializeValue(range);
    }

    public void onKeyPressRightNew() {
        for (int i = 0; i < table.length; i++) {
            if (!isEmpty(i)) {
                mergeLine(i);
            }
        }
        initializeValue(range);
    }

    public void onKeyPressUpNew() {
        rotate();
        rotate();
        rotate();
        for (int i = 0; i < table.length; i++) {
            if (!isEmpty(i)) {
                mergeLine(i);
            }
        }
        rotate();
        initializeValue(range);
    }

    public void onKeyPressDownNew() {
        rotate();
        for (int i = 0; i < table.length; i++) {
            if (!isEmpty(i)) {
                mergeLine(i);
            }
        }
        rotate();
        rotate();
        rotate();
        initializeValue(range);
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
            System.err.println("row must be smaller than the size of the array!");
        }
        return true;

    }

    /**
     * rotates the array counter clockwise
     * eg. [0] [2] = [2] [4]
     * [0] [4]   [0] [0]
     */
    public void rotate() {
        int w = table.length;
        int h = table[0].length;
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
     * the impression as if you would move the tiles in any direction
     *
     * @param row
     */
    public void mergeLine(int row) {
        int temp = 1;
        int innerTemp = 0;
        for (int j = 0; j + 1 < table[row].length; j++) {

            if (table[row][j] > 0) {
                temp = j + 1;
                if (temp < table[row].length) {
                    if (table[row][j] == table[row][temp]) {
                        table[row][temp] += table[row][j];
                        table[row][j] = 0;
                        innerTemp = temp;
                        while (innerTemp + 1 < table[row].length && table[row][innerTemp + 1] == 0) {
                            innerTemp++;
                        }
                        if (innerTemp != temp) {
                            table[row][innerTemp] = table[row][temp];
                            table[row][temp] = 0;
                        }

                    } else if (table[row][temp] == 0) {
                        innerTemp = temp;
                        while (innerTemp + 1 < table[row].length && table[row][innerTemp + 1] == 0) {

                            innerTemp++;
                        }

                        table[row][innerTemp] = table[row][j];
                        table[row][j] = 0;

                    }
                    if (j - 1 >= 0 && innerTemp - 1 >= 0 && table[row][j - 1] > 0) {//if there is a value right behind the value that is being shifted..

                        table[row][innerTemp - 1] = table[row][j - 1];
                        table[row][j - 1] = 0;
                    }

                }//if (temp < table[row].length)

            }// if (table[row][j] > 0)

        }// for (int j = 0; j + 1 < table[row].length; j++)


    }


    /**
     * Tile Back & Foreground
     */


    public Color getBackground(int value) {
        switch (value) {
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }

    public Color getForeground(int value) {
        return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
    }


    /**
     * Drawing the playing field
     **/
    @Override
    public void paint(Graphics g) {

        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < getTableSize(); x++) {
            for (int y = 0; y < getTableSize(); y++) {
                drawTile(g, x, y);
                //test
            }
        }
    }

    private void drawTile(Graphics g2, int x, int y) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        int value = table[x][y];

        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
        g.setColor(getBackground(table[x][y]));
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
        g.setColor(getForeground(table[x][y]));

        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        g.setFont(font);

        String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

        if (value != 0)
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
    }


    /**
     * Code responsible for saving the stats
     * and the current array values
     **/

    public void readStatus() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("save.ser");
        ObjectInputStream ois = new ObjectInputStream(fi);
        temp = null;
        temp = (Calc) ois.readObject();
        ois.close();
        System.out.println("Serialized figures read");
    }

    public void saveStatus() throws IOException {
        // FileOutputStream fo=new FileOutputStream("save.ser");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.ser"));
        oos.writeObject(this);
        oos.close();
        System.out.println("figures serialized");
    }


    /**
     * Table is printed into the terminal
     */
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println();
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







