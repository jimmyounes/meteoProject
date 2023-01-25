package app.appmeteo.view;

import app.appmeteo.controller.BaseController;
import app.appmeteo.controller.MainWindowController;
import app.appmeteo.model.DataManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    DataManager dataManager;

    public ViewFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(dataManager, this, "MainWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController baseController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
