package app.appmeteo.model;

import javafx.scene.control.ChoiceBox;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileOperation {
    public static void replaceJsonFile(StringBuffer responseContent) throws IOException {
        Path path = Paths.get("src/main/resources/test.json");
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);
        content = responseContent.toString();
        Files.write(path, content.getBytes(charset));
    }

    public static void addCitytoFavorites(String city) throws IOException {
        String filePath = "src/main/resources/favoris.txt";
        FileWriter fw = new FileWriter(filePath, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(city);
        bw.newLine();
        bw.close();
    }

    public static void deleteCityfromFavorites(String city) throws IOException {
        Path path = Paths.get("src/main/resources/favoris.txt");
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);
        content = content.replaceAll(city, "");
        Files.write(path, content.getBytes(charset));
    }

    public static ChoiceBox<String> getFavorites() throws IOException {
        ChoiceBox<String> favorites = new ChoiceBox<>();
        String filePath = "src/main/resources/favoris.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line = "";
        while ( (line = bufferedReader.readLine()) != null ) {
            if (!line.equals("")) {favorites.getItems().add(line);}
        }
        return favorites;
    }
}
