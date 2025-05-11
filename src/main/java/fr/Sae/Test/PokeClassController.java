package fr.Sae.Test;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class PokeClassController {

    private List<Attack> touteLesAttacks = new ArrayList<>();
    private List<Carte> touteLesCartes = new ArrayList<>();
    private List<Carte> deckJoueur = new ArrayList<>();
    private List<Carte> deckBot = new ArrayList<>();
    private List<Carte> mainJoueurListe = new ArrayList<>();
    private List<Carte> mainBotListe = new ArrayList<>();
    private List<Carte> bancJoueurListe = new ArrayList<>();
    private List<Carte> bancBotListe = new ArrayList<>();
    private List<Carte> posJoueur = new ArrayList<>();
    private List<Carte> posBot = new ArrayList<>();
    private int scoreJoueur;
    private int scoreBot;
    private int energieDonnerCeTour = 0;
    private int tour = 0;

    @FXML ImageView dosCarteBot;
    @FXML ImageView dosCarteJoueur;
    @FXML Button energie;
    @FXML HBox mainJoueur;
    @FXML HBox mainBot;
    @FXML HBox bancJoueur;
    @FXML HBox bancBot;
    @FXML HBox carteBot;
    @FXML HBox carteJoueur;
    @FXML private Label labelTour;


    @FXML
    void initialize() {
        scoreBot = 0;
        scoreJoueur = 0;
        touteLesAttacks = AttackLoader.loadAttacks("src/main/resources/attacks.txt");
        touteLesCartes = CarteLoader.load("src/main/resources/cartes/cartes.txt", touteLesAttacks);
        deckBot = new ArrayList<>(touteLesCartes);
        deckJoueur = new ArrayList<>(touteLesCartes);

        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Rouge.png"))); // image dos carte
        dosCarteBot.setImage(img);
        dosCarteJoueur.setImage(img);

        energie.setOnAction(e -> openEnergieMenu());

        for (int i = 0; i < 5; i++) {
            pioche(true);
            pioche(false);
            afficherLesCartes();
        }

    }

    private void afficherLesCartes() {
        mainBot.getChildren().clear();
        mainJoueur.getChildren().clear();
        carteJoueur.getChildren().clear();
        carteBot.getChildren().clear();
        bancJoueur.getChildren().clear();
        bancBot.getChildren().clear();


        for (int i = 0; i < mainJoueurListe.size(); i++) {
            Button buttonCarte = new Button(mainJoueurListe.get(i).getNom());
            int iutiliser = i;
            buttonCarte.setOnAction(e -> carteSellectioner(mainJoueurListe.get(iutiliser)));
            mainJoueur.getChildren().add(buttonCarte);
        }

        for (int o = 0; o < mainBotListe.size(); o++) {
            Label labelCarteBot = new Label();
            labelCarteBot.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Rouge.png"))))); // pour le Bot on ne voit pas c'est cartes donc juste des labels avec comme image le dos Carte
            mainBot.getChildren().add(labelCarteBot);
        }

        mainJoueur.getChildren().add(energie);
        energie.setOnAction(e->{openEnergieMenu();});


        for (int p = 0; p < bancJoueurListe.size(); p++) {
            VBox vbCarte = new VBox(new Label(bancJoueurListe.get(p).getVie() + ""));
            Button buttonCarte = new Button(bancJoueurListe.get(p).getNom());
            vbCarte.getChildren().add(buttonCarte);
            vbCarte.getChildren().add(new Label("" + bancJoueurListe.get(p).getEnergie()));
            int putiliser = p;
            buttonCarte.setOnAction(e -> carteSellectioner(bancJoueurListe.get(putiliser)));
            bancJoueur.getChildren().add(vbCarte);
        }

        for (int j = 0; j < bancBotListe.size(); j++) {
            VBox vbCarte = new VBox(new Label(bancBotListe.get(j).getVie() + ""));
            Label labelCarteBot = new Label();
            labelCarteBot.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/Rouge.png")))));
            vbCarte.getChildren().add(labelCarteBot);
            vbCarte.getChildren().add(new Label("" + bancBotListe.get(j).getEnergie()));
            bancBot.getChildren().add(vbCarte);
        }



        if (! posJoueur.isEmpty()) {
            Button cartePosAc = new Button(posJoueur.getLast().getNom());
            VBox carteVb = new VBox(new Label(String.valueOf(posJoueur.getLast().getVie())), cartePosAc, new Label(String.valueOf(posJoueur.getLast().getEnergie())));
            cartePosAc.setOnAction(e -> openAttck());
            carteJoueur.getChildren().add(carteVb);
        }

        if (!posBot.isEmpty()) {
            Button cartePosAc = new Button(posBot.getLast().getNom());
            VBox carteVb = new VBox(new Label(String.valueOf(posBot.getLast().getEnergie())), cartePosAc, new Label(String.valueOf(posBot.getLast().getVie())));
            carteBot.getChildren().add(carteVb);
        }
    }

    private void carteSellectioner(Carte carteJouer) {
        afficherLesCartes();
        if (bancJoueurListe.size() < 3) {
            if (!bancJoueurListe.contains(carteJouer)) {
                Button buPos = new Button("Ajouter au banc");
                buPos.setOnAction(e -> {

                    bancJoueurListe.add(carteJouer);
                    mainJoueurListe.remove(carteJouer);
                    afficherLesCartes();
                });
                bancJoueur.getChildren().add(buPos);
            }
        }
        if ((carteJouer.getIdPere() == 0 || bancJoueurListe.contains(carteJouer)) && (posJoueur.isEmpty() || posJoueur.getLast().getVie() <= 0)) {
            Button buPos = new Button("Jouer cette carte");
            buPos.setOnAction(e -> {
                if (bancJoueurListe.contains(carteJouer)) {
                    bancJoueurListe.remove(carteJouer);
                } else {
                    mainJoueurListe.remove(carteJouer);
                }
                posJoueur.add(carteJouer);
                afficherLesCartes();
            });
            carteJoueur.getChildren().add(buPos);
        }
    }

    private void openAttck() {

        VBox vbAtt = new VBox();
        carteJoueur.getChildren().clear();
        Carte carte = posJoueur.getLast();
        Button carteBu = new Button(carte.getNom());
        VBox carteVb = new VBox(new Label(String.valueOf(carte.getVie())), carteBu, new Label(String.valueOf(carte.getEnergie())));
        carteBu.setOnAction(e -> afficherLesCartes());
        carteJoueur.getChildren().add(carteVb);

        for (int i = 0; i < carte.getAttcksCarte().length; i++) {
            Attack attack = carte.getAttcksCarte()[i];
            Button buAttack = new Button(attack.getNomAttack() + " " + attack.getEnergie());
            buAttack.setOnAction(e -> {
                if (carte.getEnergie() >= attack.getEnergie()) {
                    carte.setEnergie(carte.getEnergie() - attack.getEnergie());
                    carteAttackAvec(attack, false);
                    finTour();
                }
            });
            vbAtt.getChildren().add(buAttack);
        }

        Button switchBtn = new Button("Retraite");
        switchBtn.setOnAction(e -> openSwitchMenu());
        vbAtt.getChildren().add(switchBtn);
        vbAtt.setPadding(new Insets(10));
        vbAtt.setSpacing(10);
        carteJoueur.getChildren().add(vbAtt);
    }

    private void openSwitchMenu() {
       Button carteBu =  new Button(posJoueur.getLast().getNom());
        VBox carteVb = new VBox(new Label(String.valueOf(posJoueur.getLast().getVie())), carteBu, new Label(String.valueOf(posJoueur.getLast().getEnergie())));
        carteBu.setOnAction(e->{afficherLesCartes();});
        carteJoueur.getChildren().clear();
        carteJoueur.getChildren().add(carteVb);
        carteJoueur.getChildren().add(new Label("Choisissez une carte du banc :"));
        bancJoueur.getChildren().clear();
        for (int i = 0; i < bancJoueurListe.size(); i++) {
            Carte candidate = bancJoueurListe.get(i);
            Button btn = new Button( candidate.getNom() + " (coût: " + posJoueur.getLast().getRetraite() + ")");
            int index = i;
            btn.setOnAction(e -> {
                System.out.println("Coucou");
                Carte active = posJoueur.getLast();
                if (active.getEnergie() >= active.getRetraite()) {
                    active.setEnergie(active.getEnergie() - active.getRetraite());
                    posJoueur.add(bancJoueurListe.get(index));
                    bancJoueurListe.remove(bancJoueurListe.get(index));
                    bancJoueurListe.add(active);
                    afficherLesCartes();
                }
            });
            bancJoueur.getChildren().add(btn);
        }
    }

    void carteAttackAvec(Attack attack, boolean joueur) {
        int dega = attack.getVieEnMoins();
        if (joueur) {
            if (dega >= posJoueur.getLast().getVie()) finGame(true);
            else posJoueur.getLast().setVie(posJoueur.getLast().getVie() - dega);
        } else {
            if (dega >= posBot.getLast().getVie()) finGame(false);
            else posBot.getLast().setVie(posBot.getLast().getVie() - dega);
        }
        energieDonnerCeTour = 0;
        afficherLesCartes();
    }

    private void finGame(boolean joueur) {
        deckBot.clear();
        deckJoueur.clear();
        System.out.println((joueur ? "Le joueur" : "Le bot") + " a perdu !");
    }

    private void openEnergieMenu() {
        bancJoueur.getChildren().clear();
        carteJoueur.getChildren().clear();
        energie.setOnAction(e->{
            afficherLesCartes();
        });
        Button buEn = new Button(posJoueur.getLast().getNom());
        buEn.setOnAction( e->{
            if (energieDonnerCeTour  == 0) {
                posJoueur.getLast().setEnergie(posJoueur.getLast().getEnergie() + 1);
                energieDonnerCeTour += 1;
                afficherLesCartes();
            }
        });
        carteJoueur.getChildren().add(buEn);
        for (int i = 0; i < bancJoueurListe.size(); i++) {
            Button buEnergie = new Button(bancJoueurListe.get(i).getNom());
            int finalI = i;
            buEnergie.setOnAction(e -> {
                if (energieDonnerCeTour  == 0 ) {
                    bancJoueurListe.get(finalI).setEnergie(bancJoueurListe.get(finalI).getEnergie() + 1);
                    energieDonnerCeTour  += 1;
                    afficherLesCartes();
                }
            });
            bancJoueur.getChildren().add(buEnergie);
        }
    }

    @FXML
    void finTour() {
        afficherLesCartes();
        if (posJoueur.isEmpty() || posJoueur.getLast().getVie() <= 0) carteSwitch();
        jouerTour();
        tour++;
        TourAnimator.animer(labelTour, tour); // petite anime de ma part je sais c'est moche mais sa marche
        System.out.println("Tour " + tour);
    }



    private void carteSwitch() {
        bancJoueur.getChildren().clear();
        carteJoueur.getChildren().clear();
        for (int i = 0; i < bancJoueurListe.size(); i++) {
            Button buSwitch = new Button(bancJoueurListe.get(i).getNom());
            int ifinale = i;
            buSwitch.setOnAction(e -> {
                posJoueur.add(bancJoueurListe.get(ifinale));
                bancJoueurListe.remove(ifinale);
                afficherLesCartes();
            });
            bancJoueur.getChildren().add(buSwitch);
        }
    }

    protected void pioche(boolean joueur) {
        if (joueur && !deckJoueur.isEmpty()) {
            int index = getRandom(0, deckJoueur.size() - 1);
            mainJoueurListe.add(deckJoueur.remove(index));
        } else if (!joueur && !deckBot.isEmpty()) {
            int index = getRandom(0, deckBot.size() - 1);
            mainBotListe.add(deckBot.remove(index));
        }
    }

    private int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    private void jouerTour() {
        pioche(false);
        if ((posBot.isEmpty() || posBot.getLast().getVie() <= 0) && !mainBotListe.isEmpty()) {
            Carte carteAJouer = mainBotListe.remove(0);
            posBot.add(carteAJouer);
        }
        if (!posBot.isEmpty() && !posJoueur.isEmpty()) {
            Carte attaquant = posBot.getLast();
            for (Attack attaque : attaquant.getAttcksCarte()) {
                if (attaquant.getEnergie() >= attaque.getEnergie()) {
                    attaquant.setEnergie(attaquant.getEnergie() - attaque.getEnergie());
                    carteAttackAvec(attaque, true);
                    break;
                }
            }
        }
        while (bancBotListe.size() < 3 && !mainBotListe.isEmpty()) {
            bancBotListe.add(mainBotListe.remove(0));
        }
        if (!posBot.isEmpty()) {
            posBot.getLast().setEnergie(posBot.getLast().getEnergie() + 1);
        }
    }

}