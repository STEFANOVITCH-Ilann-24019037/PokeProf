package fr.Sae.Test;

import fr.IlannStefanovitch.PokeProf.MainPokeProf;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PokeClassMain extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {

       FXMLLoader fxmlLoader = new FXMLLoader(MainPokeProf.class.getResource("/PokeClass.fxml"));
       Scene scene = new Scene(fxmlLoader.load(), 600, 700);
      primaryStage.setScene(scene);
       primaryStage.show();
    }
}
