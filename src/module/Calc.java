package module;

import java.util.Random;

/**
 * Created by Biko on 23.12.2015.
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
     *  Randomly creates a position in the array and tries to insert even number into position.
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
     *  Creates even random number for table
     *  @return even number;
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

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println();
        }
    }




    public void onKeyPressLeft() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {

                if (!(table[i][j] == 0) && j - 1 >= 0) {
                    if (table[i][j - 1] == 0) {
                        if(table[i][j]==table[i][j-1])
                        {
                            table[i][j]=0;
                            table[i][j-1] += table[i][j-1];
                        }

                    }
                }
            }
        }
    }
}