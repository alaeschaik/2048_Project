package module;

import java.util.Comparator;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreBoardComparator implements Comparator<Score> {


    @Override
    public int compare(Score o1, Score o2) {
        return Integer.valueOf(o1.getScore()).compareTo(Integer.valueOf(o2.getScore()));
    }
}
