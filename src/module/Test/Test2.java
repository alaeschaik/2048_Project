package module.Test;

import module.Score.Score;

import java.util.ArrayList;


/**
 * Created by Biko on 25.12.2015.
 * for specific debugging
 */
public class Test2 {
    public static void main(String[] args) {
        new Test2 ().clone(new Score("huhu",4,10));

    }

    public void testScore() {
        Score score = new Score ("huhu", 4, 10);
        System.out.println (score.toString ());
    }

    public void testSaving() {
        int tempInt = 0;
        Score score = new Score ("test", 13, 123);
        Score[] scoreArray = new Score[10];
        int[] intArray = new int[10];
        for (int i = 0; i < 10; i++) {
            scoreArray[i] = clone(score);
            intArray[i] = tempInt;
            tempInt++;
            score.setScore (score.getScore ()+i);

        }
        score=new Score("huhu",999,666);

        for(int j=0;j<10;j++)
        {
            System.out.println (intArray[j]);
            System.out.println (scoreArray[j]);
        }
    }

    public Score clone(Score input)
    {
        ArrayList<Score> scoreArraynew =new ArrayList();
        Score output=input;
        output.setScore(123);
        scoreArraynew.add(output);
        scoreArraynew.add(input);
        input.setScore(1451);
        System.out.println(input);
input=new Score("huhu",10,214);
        for(Score s:scoreArraynew)
        {
            System.out.println(s);
        }
        return  input;
    }

}
