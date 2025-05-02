package fr.IlannStefanovitch.PokeProf;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PokeController {
    @FXML private Label pokemon1Label, pokemon2Label,LabelLogDroite,LabelVideGauche;
    @FXML private Button attackButton, testButton,PokeButton;
    @FXML private VBox leftColumn, rightColumn;
    @FXML private HBox optionBottom;
    @FXML private GridPane arena;
    @FXML private Stage stage;
    @FXML private Scene scene;
    @FXML private ImageView  imageJ1, imageJ2;

    private Attack[] attacks = new Attack[3];
    private Attack[] attacksBot = new Attack[3];
    protected static ArrayList<Pokemon> equipeJ1 = new ArrayList<>();
    private final ArrayList<Pokemon> equipeJ2 = new ArrayList<>();
    private Pokemon pokemonJ1, pokemonJ2;
    protected static int indexJ1 = 0, indexJ2 = 0;
    protected static Button returnButton = new Button("Return");
    private List<Attack> allAttacks ;
    private List<Pokemon> allPokemons ;
    protected static List<Pokeobject> inventaire = new ArrayList<>() ;



    public void initialize() {
        allAttacks = AttackLoader.loadAttacks("src/main/resources/attacks.txt");
        allPokemons = PokemonLoader.loadPokemons(allAttacks);

        Pokemon pBot = allPokemons.get(0);
        PorfeStopController.equipeJ = equipeJ1;
        PorfeStopController.inventaire =inventaire;

        equipeJ2.add(pBot);
        pokemonJ1 = equipeJ1.get(indexJ1);
        pokemonJ2 = equipeJ2.get(indexJ2);

        Image img = new Image(getClass().getResourceAsStream("/img/"+equipeJ1.get(indexJ1).getNom()+".png"));
        imageJ1.setImage(img);
        Image imgBot = new Image(getClass().getResourceAsStream("/img/"+equipeJ2.get(indexJ2).getNom()+".png"));
        imageJ2.setImage(imgBot);

        updateDisplay();

        attackButton.setOnAction(e -> openAttackMenu());
        testButton.setOnAction(e -> openObjectMenu());
        returnButton.setOnAction(e -> closeMenu());
        PokeButton.setOnAction(e->pokchoix());
    }

    private void openAttackMenu() {
        leftColumn.getChildren().clear();
        rightColumn.getChildren().clear();

        attacks =equipeJ1.get(indexJ1).getAttack();
        for (int i = 0; i < attacks.length; i++) {

            int finalI = i;
            Button b = new Button(attacks[i].getNomAttack());
            b.setOnAction(e -> {
                if (pokemonJ2.getVie() >= attacks[finalI].getVieEnMoins()) {
                    PrendDeg(attacks[finalI].getVieEnMoins(),false);
                    updateDisplay();
                    LogLabel(attacks[finalI].getDesc());
                    closeMenu();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> botAttack());
                        }
                    },700);

                } else {
                    pokemonJ2.setVie(0);
                    updateDisplay();
                    try {
                        endMatch(true);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            rightColumn.getChildren().add(b);
        }

        leftColumn.getChildren().add(returnButton);
    }

    public void pokchoix(){
        leftColumn.getChildren().clear();
        rightColumn.getChildren().clear();
        for (int i =0 ;i <equipeJ1.size();i++){
            Button pokeButton = new Button();
            Image img = new Image(getClass().getResourceAsStream("/smallImg/"+equipeJ1.get(i).getNom()+".png"));
            pokeButton.setGraphic( new ImageView(img));
            if (equipeJ1.get(i).getVie() <=0){
                pokeButton.setDisable(true);
            }
            pokeButton.setMaxHeight(100);
            pokeButton.setMaxWidth(100);
            int FinalI = i;
            pokeButton.setOnMouseClicked(e ->{
                Pokemon tmp = new Pokemon() ;
                tmp = equipeJ1.get(indexJ1);
                equipeJ1.set(indexJ1 ,equipeJ1.get(FinalI));
                equipeJ1.set(FinalI,tmp);
                imageJ1.setImage(img);
                updateDisplay();
                LogLabel(equipeJ1.get(indexJ1).getNom() +" a l'ataque" );
                closeMenu();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> botAttack());
                    }
                },700);


            });

            HBox tempoVbox = new HBox();
            tempoVbox.getChildren().add(pokeButton);
            rightColumn.getChildren().add(tempoVbox);


        }
        leftColumn.getChildren().add(returnButton);
    }

    protected void closeMenu() {
        leftColumn.getChildren().clear();
        rightColumn.getChildren().clear();
        leftColumn.getChildren().add(attackButton);
        rightColumn.getChildren().add(testButton);
        rightColumn.getChildren().add(PokeButton);
    }

    private void endMatch(Boolean GG) throws IOException { //si true c'est le joueur Qui gagne si false c'est le bot
        if (GG)
        {
            indexJ2++;
            if (indexJ2 >= equipeJ2.size()) {
                indexJ2 = 0;
                MainPokeProf.switchFxml("/ProfeStop.fxml");
            } else {
                pokemonJ2 = equipeJ2.get(indexJ2);
                updateDisplay();
                Image imgBot = new Image(getClass().getResourceAsStream("/img/"+equipeJ2.get(indexJ2).getNom()+".png"));
                imageJ2.setImage(imgBot);
            }
        }
        else
        {
            indexJ1++;
            if (indexJ1 >= equipeJ1.size()) {
                MainPokeProf.switchFxml("/ProfeStop.fxml");
                indexJ1 = 0;
            } else {
                pokemonJ1 = equipeJ1.get(indexJ1);
                updateDisplay();
                Image img = new Image(getClass().getResourceAsStream("/img/"+equipeJ1.get(indexJ1).getNom()+".png"));
                imageJ1.setImage(img);

            }
        }

    }

    private void updateDisplay() {
        pokemon1Label.setText(equipeJ1.get(indexJ1).getNom() + "\r Moyenne : " + equipeJ1.get(indexJ1).getVie());
        pokemon2Label.setText(equipeJ2.get(indexJ2).getNom() + "\r Moyenne : " + equipeJ2.get(indexJ2).getVie());



    }

    public void botAttack(){
        attacksBot = equipeJ2.get(indexJ2).getAttack();

        int[] vieTempo = new int[3];
        int iFinale = -1;
        int VieFinale = 1000000000;

        for (int i =0;i <vieTempo.length;i++)
        {
            vieTempo[i] = (equipeJ1.get(indexJ1).getVie())-attacksBot[i].getVieEnMoins();
            if (vieTempo[i] <= VieFinale)
            {
                VieFinale = vieTempo[i];
                iFinale = i;
            }
        }

        if (VieFinale <= 0)
        {
            equipeJ1.get(indexJ1).setVie(0);
            LogLabel(equipeJ2.get(indexJ2).getAttack()[iFinale].getDesc());
            updateDisplay();
            try {
                endMatch(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {

            PrendDeg( equipeJ1.get(indexJ1).getVie() -VieFinale,true);
            LogLabel(equipeJ2.get(indexJ2).getAttack()[iFinale].getDesc());
            updateDisplay();
        }
    }

    public void PrendDeg (int lesDega,Boolean joueur) {
        Image imgVide = new Image(getClass().getResourceAsStream("/img/Vide.png"));
        if (joueur)
        {
            for( int i = 0 ; i<3 ; i++)
            {
                imageJ1.setImage(imgVide);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Image imgImage = new Image(getClass().getResourceAsStream("/img/" + equipeJ1.get(indexJ1).getNom() + ".png"));
                        imageJ1.setImage(imgImage);
                    }
                }, 50);
            }
            equipeJ1.get(indexJ1).setVie(equipeJ1.get(indexJ1).getVie()-lesDega);
        }
        else
        {
            for( int i = 0 ; i<3 ; i++)
            {
                imageJ2.setImage(imgVide);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Image imgImage = new Image(getClass().getResourceAsStream("/img/" + equipeJ2.get(indexJ2).getNom() + ".png"));
                        imageJ2.setImage(imgImage);
                    }
                }, 50);
            }
            equipeJ2.get(indexJ2).setVie(equipeJ2.get(indexJ2).getVie()-lesDega);
        }

    }

    public void openObjectMenu(){
        leftColumn.getChildren().clear();
        rightColumn.getChildren().clear();

        for (int i =0;i<inventaire.size();i++)
        {
            Button b = new Button(inventaire.get(i).getNomObjet()+ " x " +(inventaire.get(i).getNb()+1));
            int finalI = i;
            b.setOnAction(e -> {
                inventaire.get(finalI).useObejct(equipeJ1.get(indexJ1));
                openObjectMenu();
                LabelLogDroite.setText(LabelLogDroite.getText()+"\r \r"+inventaire.get(finalI).getDesc());
                updateDisplay();

            });
            if (inventaire.get(finalI).getNb()>=0){
                rightColumn.getChildren().add(b);
            }
        }
        leftColumn.getChildren().add(returnButton);
    }

    public void LogLabel(String text){
        LabelLogDroite.setText(LabelLogDroite.getText()+"\r \r"+text);
        if(LabelLogDroite.getHeight() > 500){
            LabelLogDroite.setText(text);
        }
    }

}