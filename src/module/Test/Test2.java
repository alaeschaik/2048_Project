package module.Test;

import module.Score.Score;

/**
 * Created by Biko on 25.12.2015.
 * for specific debugging
 */
public class Test2 {
    public static void main(String[] args) {
        new Test2().testScore();

    }

    public void testScore() {
        Score score = new Score("huhu", 4, 10);
        System.out.println(score.toString());
    }

}
