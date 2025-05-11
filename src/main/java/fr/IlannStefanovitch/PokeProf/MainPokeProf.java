package fr.IlannStefanovitch.PokeProf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPokeProf extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainPokeProf.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainPokeProf.class.getResource("/ProfeStop.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void switchFxml(String fxml) throws IOException {
        if (primaryStage != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(MainPokeProf.class.getResource(fxml));
            Scene newScene = new Scene(fxmlLoader.load(), 600, 700);
            primaryStage.setScene(newScene);
        }
    }
}
