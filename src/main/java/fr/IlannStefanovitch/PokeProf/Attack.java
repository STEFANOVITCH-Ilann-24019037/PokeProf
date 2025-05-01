package fr.IlannStefanovitch.PokeProf;

public class Attack {
    private int VieEnMoins;
    private String NomAttack;
    private String Type;
    private String desc;
    public Attack(int VieEnMoins,String NomAttack,String Type,String desc){
        this.VieEnMoins = VieEnMoins;
        this.NomAttack = NomAttack;
        this.Type = Type;
        this.desc = desc;
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
