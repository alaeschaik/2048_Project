package module.Score;

import java.io.Serializable;

/**
 * Created by Biko Kie&szlig; on 12.03.2016.
 */
public class ScoreArray implements Serializable {
    private static long serialVersionUID = 1113799434508676095L;
    private int score;
    private int[][] table;

    public ScoreArray(int score, int[][] table) {
        this.score = score;
        this.table = table;
    }

    public int getscore() {
        return score;
    }

    public ScoreArray setscore(int score) {
        this.score = score;
        return this;
    }

    public int[][] getTable() {
        return table;
    }

    public ScoreArray setTable(int[][] table) {
        this.table = table;
        return this;
    }


}
