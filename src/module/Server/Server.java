package module.Server;

import module.Score.Score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Biko Kie&szlig; on 19.03.2016.
 */
public class Server {


    /**
     * getHTML is responsible for receiving the available scores from the server.
     * @param size
     * @return
     * @throws IOException
     * @throws ServerException
     */
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
            String[] serverValues = Score[i].toString().split(";");
            if (serverValues.length < 5) throw new ServerException("no entrys in the scoreboard for this category yet");

            serverArray.add(new Score(serverValues[0], Integer.parseInt(serverValues[2]), Integer.parseInt(serverValues[1]), serverValues[3]));

        }

        System.out.println(serverArray);
        return serverArray;
    }

    /**
     * method responsible for basically uploading the score to the server(biko.wolko.at/2048). An php file(index.php) does all the work,
     * including sorting, serializing and deserializing the information
     * @param score
     * @throws IOException
     */
    public static void setHTML(Score score) throws IOException {
        URLConnection connection = new URL("http://biko.wolko.at/2048/index.php?"+"score=" + score.getScore() + "&size=" + score.getTableSize() + "&name=" + score.getName1()).openConnection();
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        InputStream response = connection.getInputStream();
        System.out.println(response);

    }

}

