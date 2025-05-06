package fr.IlannStefanovitch.PokeProf;

public class Pokemon {
    private String Nom;
    private int vie;
    private Attack[] attack;
    private String type;
    private Etat etat;

    public Pokemon(String Nom,int Vie,Attack[] attack,String type){
        this.Nom = Nom;
        this.attack = attack;
        this.vie = Vie;
        this.type = type;
    }
    public Pokemon(){
        Nom = "Vide";
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

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Etat getEtat() {
        return etat;
    }
}
