package fr.IlannStefanovitch.PokeProf;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PorfeStopController {
    @FXML private BorderPane root;

    private List<Pokemon> allPokemons;
    private List<Attack> allAttacks;
    private List<Pokeobject> allObjects;
    protected static ArrayList<Pokemon> equipeJ = new ArrayList<>();

    public void initialize() {
        allAttacks = AttackLoader.loadAttacks("src/main/resources/attacks.txt");
        allPokemons = PokemonLoader.loadPokemons(allAttacks);
        allObjects   = PokeobjectLoader.load();
        PorfeStop();
        PokeController.returnButton.setOnAction(e -> {
            try {
                closeMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void PorfeStop() {
        VBox vbox = new VBox();
        VBox BasBox = new VBox();
        for (int i = 0; i < allPokemons.size(); i++) {
            Button pokeButton = new Button();
            pokeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/smallImg/" + allPokemons.get(i).getNom() + ".png"))));
            pokeButton.setMaxHeight(100);
            pokeButton.setMaxWidth(100);

            Label labelImg = new Label("Vide");
            labelImg.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/smallImg/" + allPokemons.get(i).getNom() + ".png"))));

            int FinalI = i;

            pokeButton.setOnMouseClicked(e -> {
                if (equipeJ.size() < 4){
                    equipeJ.add(allPokemons.get(FinalI));

                }


            });

            HBox tempoHbox = new HBox(pokeButton);
            vbox.getChildren().add(tempoHbox);

        }
        BasBox.getChildren().add(PokeController.returnButton);
        root.setLeft(vbox);
        root.setBottom(BasBox);

        //Object
        VBox vboxObject = new VBox();

        for (int i = 0; i < allPokemons.size(); i++) {
            Button ObButton = new Button();
            ObButton.setText(allObjects.get(i).getNomObjet());
            ObButton.setMaxHeight(100);
            ObButton.setMaxWidth(100);
            int FinalIOb = i;

            ObButton.setOnMouseClicked(e -> {
                if (PokeController.inventaire.contains(allObjects.get(FinalIOb))){
                   int index = PokeController.inventaire.indexOf(allObjects.get(FinalIOb));
                    PokeController.inventaire.get(index).setNb(PokeController.inventaire.get(index).getNb() + 1);
                }
                PokeController.inventaire.add(allObjects.get(FinalIOb));
            });

            HBox tempoHbox = new HBox(ObButton);
            vboxObject.getChildren().add(tempoHbox);
        }
        root.setLeft(vbox);
        root.setBottom(PokeController.returnButton);
    }

    public void closeMenu() throws IOException {
        PokeController.equipeJ1 = equipeJ;
        MainPokeProf.switchFxml("/PokeProf.fxml");
    }
}
