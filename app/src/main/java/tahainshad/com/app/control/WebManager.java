package tahainshad.com.app.control;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Saeed on 21/03/2017.
 */

public class WebManager {
    private static WebManager instance = new WebManager();

    private WebManager() {
    }

    static WebManager getInstance() {
        return instance;
    }

    String callWebMethod(String url) {
        try {
            URL               methodURl     = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) methodURl.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder  sb     = new StringBuilder();
                String         line   = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                inputStream.close();
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
