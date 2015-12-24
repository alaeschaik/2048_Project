package module;

import java.util.Random;

/**
 * Created by Biko K. & Ali S. on 23.12.2015.
 */
public class Calc {
    private int[][] table /*= {{0, 4, 0, 2}, {0, 0, 2, 0}, {0, 0, 2, 2}, {0, 2, 0, 2}}*/;
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
        int random;
        int i = 0;
        while (i == 0) {
            random = new Random().nextInt(range + 1);
            if (random % 2 == 0) i = random;
        }
        return i;
    }

    /**
     * implication of shifting fields with value to the left if possible & adding up same numbers
     * original field set to zero
     * - changes made by Biko K. on 24.12.2015
     */
    public void onKeyPressLeft() { //// TODO: 24.12.2015 last row has issues - debugger shows no mistakes though // Please write comments
        int k = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] > 0 && j - 1 >= 0) {
                    if (table[i][j - 1] == table[i][j]) { // if they are the same
                        k = j;
                        if (j - 2 >= 0) {
                            k = j - 2;
                        }
                        while (k > 0 && table[i][k] == 0) { // spaces with zero are ignored
                            k--;
                        }
                        table[i][k] = table[i][j] + table[i][j];
                        table[i][j] = 0;
                        table[i][j - 1] = 0;
                    } else if (table[i][j - 1] == 0) {
                        k = j - 1;
                        while (k >= 0) {


                            if (table[i][k] == table[i][j]) {
                                table[i][k] = table[i][k] * 2;
                                table[i][j] = 0;
                                return;

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
        initializeValue();
    }


    /**
     * if the field to the right has the same number --> number is moved to the right and added up
     * original field set to zero
     * - changes made by Ali S. on 24.12.2015
     */

    public void onKeyPressRight() { // TODO: 24.12.2015 correct the algorithmus
        int k = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = table[i].length - 1; j >= 0; j--) {
                if (table[i][j] > 0 && j - 1 >= 0) {
                    if (table[i][j - 1] == table[i][j]) { // if they are the same
                        k = j;
                        if (j - 2 >= 0) {
                            k = j - 2;
                        }
                        while (k > 0 && table[i][k] == 0) { // spaces with zero are ignored
                            k--;
                        }
                        table[i][k] = table[i][j] + table[i][j];
                        table[i][j] = 0;
                        table[i][j - 1] = 0;
                    } else if (table[i][j - 1] == 0) {
                        k = j - 1;
                        while (k >= 0) {


                            if (table[i][k] == table[i][j]) {
                                table[i][k] = table[i][k] * 2;
                                table[i][j] = 0;
                                return;

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
        initializeValue();
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
