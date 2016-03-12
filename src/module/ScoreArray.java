package module;

import java.io.Serializable;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreArray implements Serializable {
    private int score;
    private int[][] table;

    public int getScore() {
        return score;
    }

    public ScoreArray setScore(int score) {
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

    public ScoreArray(int score, int[][] table) {
        this.score = score;
        this.table = table;
    }
}
