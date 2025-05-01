package fr.IlannStefanovitch.PokeProf;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.Timer;
import java.util.TimerTask;
import  fr.IlannStefanovitch.PokeProf.PokeController.*;
import javafx.scene.layout.VBox;

public class PorfeStopController {
    @FXML private BorderPane borderPane;

    public void initialize(){
        PorfeStop();

    }
    public void PorfeStop() {

        for (int i = 0; i < PokeController.equipeJ1.size(); i++) {
            Button pokeButton = new Button();
            Image img = new Image(getClass().getResourceAsStream("/smallImg/" + PokeController.equipeJ1.get(i).getNom() + ".png"));
            pokeButton.setGraphic(new ImageView(img));
            pokeButton.setMaxHeight(100);
            pokeButton.setMaxWidth(100);
            int FinalI = i;
            pokeButton.setOnMouseClicked(e -> {
                Pokemon tmp = new Pokemon();
                tmp = PokeController.equipeJ1.get(PokeController.indexJ1);
                PokeController.equipeJ1.set(PokeController.indexJ1, PokeController.equipeJ1.get(FinalI));
                PokeController.equipeJ1.set(FinalI, tmp);

            });

            HBox tempoHboxPaire = new HBox();
            HBox tempoHbosImpaire = new HBox();

            if (i % 2 == 0) {

                tempoHboxPaire.getChildren().add(pokeButton);
            } else {

                tempoHbosImpaire.getChildren().add(pokeButton);

            }
            VBox vbox = new VBox(tempoHboxPaire,tempoHbosImpaire);
            borderPane.setCenter(vbox);


        }

    }
}
