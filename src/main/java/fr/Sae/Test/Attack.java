package fr.Sae.Test;

public class Attack {

    private int VieEnMoins;
    private String NomAttack;
    private String Type;
    private int energieAttack;
    private Etat effect;

    public Attack(int VieEnMoins,String NomAttack,String Type,int energieAttack){
        this.VieEnMoins = VieEnMoins;
        this.NomAttack = NomAttack;
        this.Type = Type;
        this.energieAttack = energieAttack;
        effect = new Etat(" ");
    }

    public Attack(int VieEnMoins,String NomAttack,String Type,int energieAttack,Etat effect){
        this.VieEnMoins = VieEnMoins;
        this.NomAttack = NomAttack;
        this.Type = Type;
        this.energieAttack = energieAttack;
        this.effect = effect;
    }

    public Etat getEffect() {
        return effect;
    }

    public int getVieEnMoins() {
        return VieEnMoins;
    }

    public int getEnergie() {
        return energieAttack;
    }

    public String getNomAttack() {
        return NomAttack;
    }

    public String getType() {
        return Type;
    }
}

