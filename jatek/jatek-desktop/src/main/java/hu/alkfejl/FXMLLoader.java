package hu.alkfejl;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FXMLLoader{
    public static Scene loadFXML(String fxml){
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(App.class.getResource(fxml));
        Scene scene = null;
        try {
            Parent root = loader.load();
            scene = new Scene(root, 600, 440);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }
}
