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

    private List<Pokemon> allPokemons  ;
    private List<Attack> allAttacks;
    private List<Pokeobject> allObjects;
    protected static List<Pokeobject> inventaire = new ArrayList<>();
    protected static ArrayList<Pokemon> equipeJ = new ArrayList<>();
    private VBox Vteam =new VBox();

    public void initialize() {
        equipeJ.clear();
        inventaire.clear();
        allAttacks = AttackLoader.loadAttacks("src/main/resources/attacks.txt");
        allPokemons = PokemonLoader.loadPokemons(allAttacks);
        allObjects = PokeobjectLoader.load();
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
        VBox vboxObject = new VBox();


        for (int i = 0; i < allPokemons.size(); i++) {
            if(! equipeJ.contains(allPokemons.get(i))) {
                Button pokeButton = new Button();

                pokeButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/smallImg/" + allPokemons.get(i).getNom() + ".png"))));
                pokeButton.setMaxHeight(100);
                pokeButton.setMaxWidth(100);

                Label labelImg = new Label("Vide");
                labelImg.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/smallImg/" + allPokemons.get(i).getNom() + ".png"))));

                int FinalI = i;

                pokeButton.setOnMouseClicked(e -> {
                    if (equipeJ.size() < 4) {
                        equipeJ.add(allPokemons.get(FinalI));
                        UpdateTeam();
                        pokeButton.setDisable(true);
                        Pokemon pokeTempo = (allPokemons.get(FinalI));
                        PorfeStop();


                    }
                });

                HBox tempoHbox = new HBox(pokeButton);
                vbox.getChildren().add(tempoHbox);
            }

        }

        //Object

        for (int o = 0; o < allObjects.size(); o++) {
            Button ObButton = new Button();
            ObButton.setText(allObjects.get(o).getNomObjet());
            ObButton.setMaxHeight(100);
            int FinalIOb = o;

            ObButton.setOnMouseClicked(e -> {
                if (inventaire.contains(allObjects.get(FinalIOb))){
                    int index =inventaire.indexOf(allObjects.get(FinalIOb));
                    inventaire.get(index).setNb(inventaire.get(index).getNb() + 1);
                }else {
                    inventaire.add(allObjects.get(FinalIOb));
                }
                updateInventaire();
            });

            HBox tempoHbox = new HBox(ObButton);
            vboxObject.getChildren().add(tempoHbox);
            root.setBottom(PokeController.returnButton);
        }

        HBox Totale = new HBox();
        Totale.getChildren().add(vbox);
        Totale.getChildren().add(vboxObject);
        root.setLeft(Totale);
    }

    public void closeMenu() throws IOException {
        if (equipeJ.size() != 0){
            PokeController.equipeJ1 = equipeJ;
            PokeController.inventaire = inventaire;
            MainPokeProf.switchFxml("/PokeProf.fxml");
        }

    }

    public void UpdateTeam ( ){
        Vteam.getChildren().clear();
        int Size = equipeJ.size() ;
        for (int i = 0 ; i< Size;i++){
            Button BtTeam = new Button();
            BtTeam.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/smallImg/" + equipeJ.get(i).getNom() + ".png"))));
            BtTeam.setMaxHeight(100);
            BtTeam.setMaxWidth(100);
            Vteam.getChildren().add(BtTeam);
            int FinalI = i;
            BtTeam.setOnMouseClicked(e->{
                equipeJ.remove(equipeJ.get(FinalI));
                UpdateTeam();
                PorfeStop();
            });
        }
        root.setRight(Vteam);
    }

   public void updateInventaire (){
        VBox vBox = new VBox();
        for (int i =0 ;i< inventaire.size();i++){
            Button btObject = new Button();
            btObject.setText(inventaire.get(i).getNomObjet() + " x" +(inventaire.get(i).getNb()+1));
            int FinalI = i;
            vBox.getChildren().add(btObject);
            btObject.setOnMouseClicked(e ->{
                if(inventaire.get(FinalI).getNb() >0){
                    inventaire.get(FinalI).setNb(inventaire.get(FinalI).getNb() - 1);
                }
                else {
                    inventaire.remove(FinalI);

                }
                updateInventaire();
            });
        }
        root.setCenter(vBox);

    }

}