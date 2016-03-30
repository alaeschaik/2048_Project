package module.Score;

import java.util.Comparator;

/**
 * Created by Biko on 12.03.2016.
 * All the local scores get sorted by the value of the score(so the actually so called "score").
 */
public class ScoreBoardComparator implements Comparator<Score> {



    @Override
    public int compare(Score o1, Score o2) {
        return Integer.valueOf (o2.getScore ()).compareTo (o1.getScore ());
    }
}
