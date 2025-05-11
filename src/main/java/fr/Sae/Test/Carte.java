package fr.Sae.Test;


import java.lang.reflect.Array;

public class Carte {
    private String img; //lien Vers l'image de la carte
    private int vie; // vie -1 de la Carte
    private int niveau; // si la carte est une carte de base alor 0 si la carte et une evolutio alors 1 ou 2 si c'est une evolutio d'evolusion
    private int id ; // pour avoir un moyen de trouver les carte facilement vas de 1 pour le plus petit a je sais pas
    private int idPere; // id de la carte de la sous evo si pokemon de base alors 0
    private int type; // cheque nb aura une def ici mais pas encor j'ai pas le temps
    private int etat; // chaque etat aura un nb mais j'ai pas d'inspi donc pas encore mais si etat 0 alors nikel
    private String nom;
    private Attack[] attcksCarte;
    private int retraite ; // nb enregie qu'il faut pour faire passe le pokemon du poste Actife au banc
    private int energie;
    private int idDeck; //genere aleatoirement pour que chaque carte soit unique

    /**
     * Constructor de la class Carte
     *
     */
    public Carte ( String img ,int vie, int niveau,int id , int idPere,int type,String nom, Attack[] attacksCarts ,int retraite,int idDeck ) {
        this.id = id;
        this.idPere = idPere;
        this.img = img;
        this.niveau = niveau;
        this.vie = vie;
        this.type = type;
        this.nom = nom;
        this.attcksCarte = attacksCarts;
        this.retraite = retraite;
        this.idDeck = idDeck;
        etat = 0;
        energie = 0;
    }

    public Carte(String img , int vie , int niveau, int id, int type , String nom , Attack[] attacksCarts){
        this.img = img;
        this.vie = vie;
        this.niveau = niveau;
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.attcksCarte = attacksCarts;
        idPere = 0;
        etat = 0;
        energie = 0;
    }

    public Carte( ){
        idPere = 0;
        etat = 0;
        energie = 0;
    }



    /**
     * Getter et Setter
     *
     */
    public int getId() {
        return id;
    }

    public int getIdPere() {
        return idPere;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getType() {
        return type;
    }

    /**
     * en fonction du type
     * @return le type en String de la carte
     */
    public String getTypeEnString(){
        String s ;
        switch (type) {
            case 1 :
                 s = new String("feu");
                break;
            case 2 :
                s = new String("Triche");
                break;
            default:
                s = new String("Pas De Type");
        }
        return s;
    }

    public String getNom() {
        return nom;
    }

    public int getEnergie() {
        return energie;
    }

    public Attack[] getAttcksCarte() {
        return attcksCarte;
    }

    public int getVie() {
        return vie;
    }

    public String getImg() {
        return img;
    }

    public int getEtat() {
        return etat;
    }

    public int getRetraite() {
        return retraite;
    }

    public void setRetraite(int retraite) {
        this.retraite = retraite;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }
}
