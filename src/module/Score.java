package module;

import java.time.ZonedDateTime;

/**
 * Created by Biko on 12.03.2016.
 */
public class Score {
    ZonedDateTime tod = ZonedDateTime.now();
    int score = 0;
    String name;

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }


    public int getScore() {
        return score;
    }

    public Score setScore(int score) {
        this.score = score;
        return this;
    }

    public String getName() {
        return name;
    }

    public Score setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Score:" +
                " Name='" + name +
                " Score=" + score +
                " Time=" + tod +
                '\'' +
                '}';
    }
}
