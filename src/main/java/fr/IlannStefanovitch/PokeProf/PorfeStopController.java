package fr.IlannStefanovitch.PokeProf;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.layout.VBox;

public class PorfeStopController {
    @FXML private BorderPane root;

    private List<Pokemon> allPokemons ;
    private List<Attack> allAttacks ;

    public void initialize(){

        allAttacks = AttackLoader.loadAttacks("src/main/resources/attacks.txt");
        allPokemons = PokemonLoader.loadPokemons(allAttacks);
        PorfeStop();

    }
    public void PorfeStop() {
        AtomicInteger FinalIndex = new AtomicInteger(0);
        for (int i = 0; i < allPokemons.size(); i++) {
            Button pokeButton = new Button();
            Image imgPoke = new Image(getClass().getResourceAsStream("/smallImg/" + allPokemons.get(i).getNom() + ".png"));
            pokeButton.setGraphic(new ImageView(imgPoke));
            pokeButton.setMaxHeight(100);
            pokeButton.setMaxWidth(100);
            int FinalI = i;

            pokeButton.setOnMouseClicked(e -> {
                PokeController.equipeJ1.set(FinalIndex.get(),allPokemons.get(FinalI));
                FinalIndex.set(FinalIndex.get() + 1);

            });

            HBox tempoHboxPaire = new HBox();
            HBox tempoHbosImpaire = new HBox();

            if (i % 2 == 0) {

                tempoHboxPaire.getChildren().add(pokeButton);
            } else {

                tempoHbosImpaire.getChildren().add(pokeButton);

            }
            VBox vbox = new VBox(tempoHboxPaire,tempoHbosImpaire);
            root.setCenter(vbox);


        }

    }
}
