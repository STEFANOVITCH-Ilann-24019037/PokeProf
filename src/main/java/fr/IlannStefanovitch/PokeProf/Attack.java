package fr.IlannStefanovitch.PokeProf;

public class Attack {

    private int VieEnMoins;
    private String NomAttack;
    private String Type;
    private String desc;
    private Etat effect;

    public Attack(int VieEnMoins,String NomAttack,String Type,String desc){
        this.VieEnMoins = VieEnMoins;
        this.NomAttack = NomAttack;
        this.Type = Type;
        this.desc = desc;
        effect = new Etat(" ");
    }
    public Attack(int VieEnMoins,String NomAttack,String Type,String desc,Etat effect){
        this.VieEnMoins = VieEnMoins;
        this.NomAttack = NomAttack;
        this.Type = Type;
        this.desc = desc;
        this.effect = effect;
    }

    public Etat getEffect() {
        return effect;
    }

    public int getVieEnMoins() {
        return VieEnMoins;
    }

    public String getDesc() {
        return desc;
    }

    public String getNomAttack() {
        return NomAttack;
    }

    public String getType() {
        return Type;
    }
}
