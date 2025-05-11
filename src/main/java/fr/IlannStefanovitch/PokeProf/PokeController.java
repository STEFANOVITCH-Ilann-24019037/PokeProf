package fr.IlannStefanovitch.PokeProf;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

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
    private Etat etatNull = new Etat("null");



    public void initialize() {
        allAttacks = AttackLoader.loadAttacks("src/main/resources/attacks.txt");
        allPokemons = PokemonLoader.loadPokemons(allAttacks);

        Pokemon pBot = allPokemons.getFirst();
        PorfeStopController.equipeJ = equipeJ1;
        PorfeStopController.inventaire =inventaire;
        equipeJ2.add(pBot);
        pokemonJ1 = equipeJ1.get(indexJ1);
        pokemonJ2 = equipeJ2.get(indexJ2);

        for ( Pokemon pokemon : equipeJ1){
            pokemon.setEtat(etatNull);
        }
        for (Pokemon pokemon : equipeJ2){
            pokemon.setEtat(etatNull);
        }



        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/" + equipeJ1.get(indexJ1).getNom() + ".png")));
        imageJ1.setImage(img);
        Image imgBot = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/" + equipeJ2.get(indexJ2).getNom() + ".png")));
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
                    equipeJ2.get(indexJ2).setEtat(attacks[finalI].getEffect());
                    updateDisplay();
                    LogLabel(attacks[finalI].getDesc());
                    finTour();


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
                finTour();


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
                inventaire.clear();
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
                indexJ1 = 0;
                inventaire.clear();
                MainPokeProf.switchFxml("/ProfeStop.fxml");

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

        if (equipeJ1.get(indexJ1).getEtat().getEtat() != "null"){
            pokemon1Label.setText(pokemon1Label.getText()+ "\r "+ equipeJ1.get(indexJ1).getEtat().getEtat());
        }
        if (equipeJ2.get(indexJ2).getEtat().getEtat() != "null"){
            pokemon2Label.setText(pokemon2Label.getText()+ "\r "+ equipeJ2.get(indexJ2).getEtat().getEtat());
        }



    }

    public void botAttack(){
        attacksBot = equipeJ2.get(indexJ2).getAttack();

        int[] vieTempo = new int[3];
        int iFinale = -1;
        int VieFinale = 1000000000;
        String etatFinale = "null";

        for (int i =0;i <vieTempo.length;i++)
        {
            vieTempo[i] = (equipeJ1.get(indexJ1).getVie())-attacksBot[i].getVieEnMoins();
            if (vieTempo[i] <= VieFinale)
            {
                etatFinale = attacksBot[i].getEffect().getEtat();
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
            if (etatFinale != "null") {
                equipeJ1.get(indexJ1).setEtat(new Etat(etatFinale));
            }
            PrendDeg( equipeJ1.get(indexJ1).getVie() -VieFinale,true);
            LogLabel(equipeJ2.get(indexJ2).getAttack()[iFinale].getDesc());
            updateDisplay();
        }
    }

    public void PrendDeg (int lesDega,Boolean joueur) {
        Image imgVide = new Image(getClass().getResourceAsStream("/img/Vide.png"));
        if (joueur )
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
        else if (! equipeJ2.isEmpty())
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
                finTour();
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

    public void EtatPoke(String etat,Boolean joueur){
        if (etat != "null") switch (etat) {
            case "Brule":
                Brule(joueur);
                LogLabel("Du feu");
                PrendDeg(10,true);

        }
    }

    public void Brule(Boolean joueur){

        Image imgRouge = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Rouge.png")));
        Timer timer = new Timer();
        if (joueur && ! equipeJ1.isEmpty())
        {
            for( int i = 0 ; i<3 ; i++)
            {
                imageJ1.setImage(imgRouge);

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Image imgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/" + equipeJ1.get(indexJ1).getNom() + ".png")));
                        imageJ1.setImage(imgImage);
                    }
                }, 60);
            }
        }
        else if (! equipeJ2.isEmpty())
        {
            for( int i = 0 ; i<3 ; i++)
            {
                imageJ2.setImage(imgRouge);
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Image imgImage = new Image(getClass().getResourceAsStream("/img/" + equipeJ2.get(indexJ2).getNom() + ".png"));
                        imageJ2.setImage(imgImage);
                    }
                }, 50);
            }
        }
    }

    public void finTour(){
        closeMenu();
        Timer timer1 = new Timer();
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> botAttack());
            }
        },700);

        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                if (equipeJ1.get(indexJ1).getEtat().getEtat() != "null" && ! equipeJ1.isEmpty()) {
                    Platform.runLater(() -> EtatPoke(equipeJ1.get(indexJ1).getEtat().getEtat(), true));
                }
                if (equipeJ2.get(indexJ2).getEtat().getEtat() != "null" && ! equipeJ2.isEmpty() ) {
                    Platform.runLater(() -> EtatPoke(equipeJ2.get(indexJ2).getEtat().getEtat(),false));
                }

            }
        },700);

    }

}