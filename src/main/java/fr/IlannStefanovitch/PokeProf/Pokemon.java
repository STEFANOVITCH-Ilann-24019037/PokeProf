package fr.IlannStefanovitch.PokeProf;

public class Pokemon {
    private String Nom;
    private int vie;
    private Attack[] attack;
    private String type;

    public Pokemon(String Nom,int Vie,Attack[] attack,String type){
        this.Nom = Nom;
        this.attack = attack;
        this.vie = Vie;
        this.type = type;
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

}
