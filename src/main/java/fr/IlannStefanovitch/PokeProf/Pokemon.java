package fr.IlannStefanovitch.PokeProf;

public class Pokemon {
    private String Nom;
    private int vie;
    private Attack[] attack;
    private String type;
    private String etat;

    public Pokemon(String Nom,int Vie,Attack[] attack,String type){
        this.Nom = Nom;
        this.attack = attack;
        this.vie = Vie;
        this.type = type;
        etat = null;
    }
    public Pokemon(String Nom,int Vie,Attack[] attack,String type,String etat){
        this.Nom = Nom;
        this.attack = attack;
        this.vie = Vie;
        this.type = type;
        this.etat = etat;
    }

    public Pokemon(){
        Nom = "Vide";
    }
    public void PokemonAttack(Attack attack,Pokemon cible){
        cible.setVie(cible.getVie()-attack.getVieEnMoins());
    }

    public String getNom() {
        return Nom;
    }

    public String getType() {
        return type;
    }

    public void setAttack(Attack[] attack) {
        this.attack = attack;
    }

    public Attack[] getAttack() {
        return attack;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getVie() {
        return vie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
