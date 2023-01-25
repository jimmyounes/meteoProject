package app.appmeteo.model;

import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataManagerDaily extends DataManager {

    private JsonParser jsonParse;

    public DataManagerDaily(File file) {
        this.jsonParse = new JsonParser();
        try (FileReader reader = new FileReader(file)) {
            this.obj = jsonParse.parse(reader).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
