package module.Score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreBoard implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    public static ArrayList<Score> scoreBoard;


    public static ArrayList<Score> getScoreBoard() {
        return scoreBoard;
    }

    public static void add(Score score) throws IOException, ClassNotFoundException {
        if (ScoreBoard.scoreBoard == null) {
            scoreBoard = new ArrayList<> ();
            ScoreBoard.readList ();
        }
        scoreBoard.add (score);
        ScoreBoard.saveList ();
    }

    public static void setScoreBoard(ArrayList<Score> scoreBoard) {
        ScoreBoard.scoreBoard = scoreBoard;
    }

    public static void printScoreBoard() {
        if (scoreBoard.size () == 0) System.out.println ("no entry");
        for (Score score : scoreBoard) {
            System.out.println (score.toString ());

        }
    }

    public static void initialize() throws IOException, ClassNotFoundException, ScoreBoardEmptyException {
        if (scoreBoard == null) scoreBoard = new ArrayList<> ();
        if (scoreBoard.size () == 0) throw new ScoreBoardEmptyException ("no Entry in the scoreboard");
        scoreBoard = readList ();
        System.out.println ("initialize complete");
    }


    public static ArrayList<Score> readList() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream ("scoreBoard.ser");
        ObjectInputStream ois = new ObjectInputStream (fi);
        ScoreBoard scoreBoardBackUp = (ScoreBoard) ois.readObject ();
        ois.close ();
        System.out.println ("Serialized figures read");
        scoreBoard = scoreBoardBackUp.getScoreBoard ();
        return scoreBoardBackUp.getScoreBoard ();
    }

    public static void saveList() throws IOException, ClassNotFoundException {

        ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream ("scoreBoard.ser"));
        ScoreBoard temp = new ScoreBoard ();
        oos.writeObject (temp);
        oos.close ();
        System.out.println ("figures serialized");
    }

    public static void sort() {
        Collections.sort (scoreBoard, new ScoreBoardComparator ());
    }
}
