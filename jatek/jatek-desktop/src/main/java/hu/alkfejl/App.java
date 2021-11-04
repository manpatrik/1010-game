package hu.alkfejl;

import hu.alkfejl.dao.EredmenyDAO;
import hu.alkfejl.dao.EredmenyDAOinpl;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage mainWindow;

    public static Stage getStage() {
        return mainWindow;
    }

    public static void setMainWindow(Scene scene) {
        App.mainWindow.setScene(scene);
    }

    @Override
    public void start(Stage stage) {
        App.mainWindow = stage;
        mainWindow.setResizable(false);
        setMainWindow(FXMLLoader.loadFXML("/fxml/main_window.fxml"));
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}