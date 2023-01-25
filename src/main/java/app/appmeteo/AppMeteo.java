package app.appmeteo;

import app.appmeteo.model.DataManager;
import app.appmeteo.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;


public class AppMeteo extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = new ViewFactory(new DataManager(new File("src/main/resources/test.json")));
        viewFactory.showMainWindow();
    }
}