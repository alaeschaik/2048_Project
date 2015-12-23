package module;

import java.util.Random;

/**
 * Created by Biko K. & Ali S. on 23.12.2015.
 */
public class Calc {
    private int[][] table;
    private int tableSize;


    public Calc(int tableSize) {
        this.tableSize = tableSize;

        table = new int[tableSize][tableSize];
        // at the start of the game, 2 values are set
        initializeValue();
        initializeValue();
    }

    /**
     * Randomly creates a position in the array and tries to insert even number into position.
     *
     * @return true if position is assigned with value, else false if not.
     */
    public boolean initializeValue() {
        int rRow = new Random().nextInt(table.length);
        int rCol = new Random().nextInt(table.length);

        if (table[rRow][rCol] == 0) {
            table[rRow][rCol] = returnEvenNumber(4);
            return true;
        }
        return false;
    }

    /**
     * Creates even random number for table
     *
     * @return even number;
     */
    public int returnEvenNumber(int range) {
        int random = 0;
        int i = 0;
        while (i == 0) {
            random = new Random().nextInt(range + 1);
            if (random % 2 == 0) i = random;
        }
        return i;
    }

    /**
     * if the field to the left has the same number --> number is moves to the left and added up
     * original field set to zero
     * - changes made by Ali S. on 23.12.2015
     */
    public void onKeyPressLeft() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {

                if (table[i][j] > 0 && j - 1 >= 0 && table[i][j] == table[i][j - 1]) { // number to the left is the same
                    table[i][j - 1] += table[i][j - 1];
                    table[i][j] = 0;
                }
            }
        }
    }

    /**
     * if the field to the right has the same number --> number is moves to the right and added up
     * original field set to zero
     * - changes made by Ali S. on 23.12.2015
     */
    public void onKeyPressRight() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {

                if (table[i][j] > 0 && j + 1 <= tableSize - 1 && table[i][j] == table[i][j + 1]) { // number to the right is the same
                    table[i][j + 1] += table[i][j + 1];
                    table[i][j] = 0;
                }
            }
        }
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
        }
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


}
