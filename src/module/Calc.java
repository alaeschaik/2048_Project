package module;

import java.util.Random;

/**
 * Created by Biko K. & Ali S. on 23.12.2015.
 */
public class Calc {
    private int[][] table /*= {{4, 4, 0, 2}, {0, 0, 2, 0}, {0, 0, 2, 2}, {0, 2, 0, 2}}*/; //remove or keep initialization as comment depending on if you want specific or general testing
    private int tableSize;


    public Calc(int tableSize) {
        this.tableSize = tableSize;

        table = new int[tableSize][tableSize];
        //at the start of the game, 2 values are set
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

        if (table[rRow][rCol] == 0 && end()) { //if the game is not over, we insert a new value
            table[rRow][rCol] = returnEvenNumber(4);
            return true;
        } else if (!(table[rRow][rCol] == 0) && end()) //if the field is already occupied & game not over, we call the method again and look for an empty field
        {
            return initializeValue();
        } else { //if the game is over
            System.out.println("GAME OVER");
        }
        return false;
    }


    /**
     * checks table for empty field, if not available, end the game
     */
    public boolean end() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) {
                    return true;
                }
            }
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
        initializeValue();
    }

    public void onKeyPressDown() {
        int k = 0;
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
        initializeValue();
    }


    /**
     * implication of shifting fields with value to the left if possible & adding up same numbers
     * original field set to zero
     * - changes made by Biko K. on 25.12.2015
     */
    public void onKeyPressLeft() { //// UPDATE 25.12.2015: fixed mistake of ignoring the last row.(thinking mistake from my side) - Biko
        int k = 0;
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


}
