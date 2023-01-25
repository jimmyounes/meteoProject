package app.appmeteo.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(ApplicationExtension.class)
public class TestInterfaceGraphique extends GuiTest {
    /*
    @Start
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
    }*/

/*
    @Test
    void testButtonText(FxRobot robot) {
        Button button = robot.lookup("#chercher").queryButton();


        //    Assertions.assertThat(button).hasText("TexteDuBouton");
// vérifie ce qu'il est écrit sur le bouton
    }*/


    @Test
    public void setBothnamesAndCheckEnabledSearchButton() {


        Button search = find("#chercher");
     System.out.println( search.getText());
    }

    @Override
    protected Parent getRootNode() {
        Parent  parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }
}
