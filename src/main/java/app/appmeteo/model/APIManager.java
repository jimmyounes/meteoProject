

package app.appmeteo.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class APIManager {
    private static HttpURLConnection connection;

    public static void HttpResponse(String city) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ city +"&units=metric&lang=fr&appid=759448245d16a9a1ef67cfd2e02aebe6");

        connection = (HttpURLConnection) url.openConnection();

        //Request Set up

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();

        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        }
        connection.disconnect();
        FileOperation.replaceJsonFile(responseContent);

    }
}

