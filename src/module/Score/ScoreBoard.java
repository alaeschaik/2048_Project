package module.Score;

import module.Server.Server;
import module.Server.ServerException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreBoard implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    public static ArrayList<Score> scoreBoard;


    public ArrayList<Score> getScoreBoard() {
        return scoreBoard;
    }

    public static void add(Score score) throws IOException, ClassNotFoundException {
        if (ScoreBoard.scoreBoard == null) {
            scoreBoard = new ArrayList<>();
            if (new File("scoreBoard.ser").length() != 0) readList();
        }
        scoreBoard.add(score);
        ScoreBoard.saveList();
        Server.setHTML(score);
    }

    public static void setScoreBoard(ArrayList<Score> scoreBoard) {
        ScoreBoard.scoreBoard = scoreBoard;
    }

    public static void printScoreBoard() {
        if (scoreBoard.size() == 0) System.out.println("no entry");
        for (Score score : scoreBoard) {
            System.out.println(score.toString());

        }
    }

    public static void initializeLocal() throws IOException, ClassNotFoundException, ScoreBoardEmptyException {
        if (scoreBoard == null) {
            scoreBoard = new ArrayList<>();
            scoreBoard = readList();

        }
        if (scoreBoard.size() == 0) throw new ScoreBoardEmptyException("no Entry in the scoreboard");

        System.out.println("initialize complete");
    }

    public static void initializeGlobal(int size) throws IOException, ClassNotFoundException,ServerException {
        if (scoreBoard == null) {
            scoreBoard = new ArrayList<>();
        }
        scoreBoard = Server.getHTML(size);

        System.out.println("initialize complete");
    }


    public static ArrayList<Score> readList() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("scoreBoard.ser"));
        Object scoreBoardBackUp = ois.readObject();
        ArrayList<Score> arr = null;
        if (scoreBoardBackUp instanceof ArrayList) {
            arr = ((ArrayList<Score>) scoreBoardBackUp);
            System.out.println(arr.toString());
        }
        ois.close();
        scoreBoard = arr;
        return arr;
    }


    public static void saveList() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("scoreBoard.ser"));
        oos.writeObject(scoreBoard);
        oos.close();
        System.out.println("figures serialized");
    }

    public static void sort() {
        Collections.sort(scoreBoard, new ScoreBoardComparator());
    }
}
