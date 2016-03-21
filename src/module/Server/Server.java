package module.Server;

import module.Score.Score;
import module.Score.ScoreBoard;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by Biko on 19.03.2016.
 */
public class Server {


    public static ArrayList<Score> getHTML(int size) throws IOException, ServerException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://biko.wolko.at/2048/?size="+size);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
            result.append("\n");
        }

        rd.close();
        String[] Score = result.toString().split("\n");
        ArrayList<Score> serverArray = new ArrayList<>();
        for (int i = 0; i < Score.length; i++) {
            String[] serverValues = Score[i].toString().split(";|\n");
            if (serverValues.length < 5) throw new ServerException("no entrys in the scoreboard for this category yet");

            serverArray.add(new Score(serverValues[0], Integer.parseInt(serverValues[2]), Integer.parseInt(serverValues[1]), serverValues[3]));

        }

        ScoreBoard.scoreBoard = serverArray;
        System.out.println(serverArray);
        return serverArray;
    }

    public static void setHTML(Score score) throws IOException {
        URLConnection connection = new URL("http://biko.wolko.at/2048/index.php?"+"score=" + score.getScore() + "&size=" + score.getTableSize() + "&name=" + score.getName1()).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        InputStream response = connection.getInputStream();
        System.out.println(response);

    }

}

