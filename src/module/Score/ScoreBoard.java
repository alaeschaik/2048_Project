package module.Score;

import module.Server.Server;
import module.Server.ServerException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 */
public abstract class ScoreBoard implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    /**
     * scoreBoard is the Arraylist that contains all the necessary Scores. it is declared in the static block as it does not
     * vary in difference instances of Scoreboard. Scoreboard is basically completely static and you are not supposed to create instances of it.
     */
    public static ArrayList<Score> scoreBoard;


    static {
        if (ScoreBoard.scoreBoard == null) {
            scoreBoard = new ArrayList<> ();
        }
    }

    public static ArrayList<Score> getScoreBoard() {
        return scoreBoard;
    }

    public static void add(Score score) throws IOException, ClassNotFoundException {

        if (new File ("scoreBoard.ser").length () != 0) readList ();
        scoreBoard.add (score);
        ScoreBoard.saveList ();
        Server.setHTML (score);
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

    public static void initializeLocal() throws IOException, ClassNotFoundException, ScoreBoardEmptyException {
        scoreBoard = readList ();
        sort ();
        if (scoreBoard.size () == 0) throw new ScoreBoardEmptyException ("no Entry in the scoreboard");


    }

    public static void initializeGlobal(int size) throws IOException, ClassNotFoundException, ServerException {
       scoreBoard=new ArrayList<> ();
        scoreBoard = Server.getHTML (size);
        System.out.println ("initialize complete");
    }


    public static ArrayList<Score> readList() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream (new FileInputStream ("scoreBoard.ser"));
        Object scoreBoardBackUp = ois.readObject ();
        ArrayList<Score> arr = null;
        if (scoreBoardBackUp instanceof ArrayList) {
            arr = (ArrayList<Score>) scoreBoardBackUp;
        }

        ois.close ();
        scoreBoard = arr;
        return arr;
    }


    private static void saveList() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream ("scoreBoard.ser"));
        oos.writeObject (scoreBoard);
        oos.close ();
        System.out.println ("figures serialized");
    }

    public static void sort() {
        Collections.sort (scoreBoard, new ScoreBoardComparator ());
    }
}
