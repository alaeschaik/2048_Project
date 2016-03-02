package module;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

/**
 * Created by Biko K. & Ali S. on 23.12.2015.
 */
public class Calc extends JPanel {
    public int[][] table /*= {{4, 4, 0, 2}, {0, 0, 2, 0}, {0, 0, 2, 2}, {0, 2, 0, 2}}*/; //remove or keep initialization as comment depending on if you want specific or general testing
    private int tableSize; //size of the table, sides are proportional
    public int range = 4; //standard value of the numbers that can spawn when tile movement is done. Standard 4 means that even values including 4 can be spawned(2,4)
    Calc temp = this;

    public Calc(int tableSize) {
        setTableSize(tableSize);

        table = new int[tableSize][tableSize];
        //at the start of the game, 2 values are set
        initializeValue(range);
        initializeValue(range);
        initializeValue(range);
        initializeValue(range);
        initializeValue(range);
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
     * implication of shifting fields with value to the top if possible & adding up same numbers
     * original field set to zero
     * - changes made by Biko K. on 25.12.2015
     */
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
        initializeValue(range);
    }

    /**
     * implication of shifting fields with value to the bottom if possible & adding up same numbers
     * original field set to zero
     * - changes made by Biko K. on 25.12.2015
     */
    public void onKeyPressDown() {
        int k;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] > 0 && i + 1 < table.length) {
                    if (table[i + 1][j] == table[i][j]) { // if they are the same
                        table[i + 1][j] += table[i][j];
                        table[i][j] = 0;
                    } else if (table[i + 1][j] == 0) {
                        k = i + 1;
                        while (k < table.length) {


                            if (table[k][j] == table[i][j]) {
                                table[k][j] += table[k][j];
                                table[i][j] = 0;
                                continue;

                            }
                            if (!(table[k][j] == 0)) {
                                k--;
                                break;
                            }

                            if (k == table.length - 1) break;
                            if (table[k][j] == 0) k++;

                        }

                        table[k][j] = table[i][j];
                        table[i][j] = 0;

                    }
                }
            }
        }
        //initializeValue(range);
    }


    /**
     * implication of shifting fields with value to the left if possible & adding up same numbers
     * original field set to zero
     * - changes made by Biko K. on 25.12.2015
     */
    public void onKeyPressLeft() { //// UPDATE 25.12.2015: fixed mistake of ignoring the last row.(thinking mistake from my side) - Biko
        int k;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] > 0 && j - 1 >= 0) {
                    if (table[i][j - 1] == table[i][j]) { // if they are the same
                        table[i][j - 1] += table[i][j];
                        table[i][j] = 0;
                    } else if (table[i][j - 1] == 0) {
                        k = j - 1;
                        while (k >= 0) {


                            if (table[i][k] == table[i][j]) {
                                table[i][k] += table[i][k];
                                table[i][j] = 0;
                                continue;

                            }
                            if (!(table[i][k] == 0)) {
                                k++;
                                break;
                            }

                            if (k == 0) break;
                            if (table[i][k] == 0) k--;

                        }

                        table[i][k] = table[i][j];
                        table[i][j] = 0;
                    }
                }
            }

        }
        initializeValue(range);
    }


    /**
     * if the field to the right has the same number --> number is moved to the right and added up
     * original field set to zero
     * - changes made by Ali S. on 24.12.2015
     */
    public void onKeyPressRight() { // TODO: 24.12.2015 correct the algorithmus
        int k;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j + 1 < table[i].length; j++) {
                if (table[i][j] > 0) {
                    if (table[i][j + 1] == table[i][j]) { // if they are the same
                        table[i][j + 1] += table[i][j];
                        table[i][j] = 0;
                    } else if (table[i][j + 1] == 0) {
                        k = j + 1;
                        while (k < table.length) {


                            if (table[i][k] == table[i][j]) {
                                table[i][k] += table[i][j];
                                table[i][j] = 0;
                                continue;

                            }

                            if (!(table[i][k] == 0)) {
                                k++;
                                break;
                            }

                            if (k == table.length - 1) break;
                            if (table[i][k] == 0) k++;

                        }

                        table[i][k] = table[i][j];
                        table[i][j] = 0;
                    }
                }
            }

        }
        initializeValue(range);
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


    public void onKeyPressLeftNew() {
        rotate();
        rotate();
        for (int i = 0; i < table.length; i++) {
            if (isEmpty(i) > 0) {
                mergeLine(i);
            }
        }
        rotate();
        rotate();
    }

    public void onKeyPressRightNew() {
        for (int i = 0; i < table.length; i++) {
            if (isEmpty(i) > 0) {
                mergeLine(i);
            }
        }
    }

    public void onKeyPressUpNew() {
        rotate();
        rotate();
        rotate();
        for (int i = 0; i < table.length; i++) {
            if (isEmpty(i) > 0) {
                mergeLine(i);
            }
        }
        rotate();
    }

    public void onKeyPressDownNew() {
        rotate();
        for (int i = 0; i < table.length; i++) {
            if (isEmpty(i) > 0) {
                mergeLine(i);
            }
        }
        rotate();
        rotate();
        rotate();
    }


    public int isEmpty(int row) {
        int counter = 0;
        if (row < table.length) {
            for (int i = 0; i < table[row].length; i++) {
                if (table[row][i] > 0) {
                    counter++;
                }
            }
        } else {
            System.err.println("row must be smaller than the size of the array!");
        }
        return counter;

    }

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
                    if (j - 1 >= 0 && table[row][j - 1] > 0) {//if there is a value right behind the value that is being shifted..
                        table[row][innerTemp - 1] = table[row][j - 1];
                        table[row][j - 1] = 0;
                    }

                }

            }
        }


    }

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

    public void saveStatus() throws IOException {
        // FileOutputStream fo=new FileOutputStream("save.ser");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.ser"));
        oos.writeObject(this);
        oos.close();
        System.out.println("figures serialized");
    }

    /**
     * Serialize objects
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readStatus() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("save.ser");
        ObjectInputStream ois = new ObjectInputStream(fi);
        temp = null;
        // cast object to arraylist of Geometricalfigures
        temp = (Calc) ois.readObject();
        ois.close();
        System.out.println("Serialized figures read");
    }

    @Override
    public String toString() {
        return "Calc{" +
                "tableSize=" + tableSize +
                ", range=" + range +
                '}';
    }
}






