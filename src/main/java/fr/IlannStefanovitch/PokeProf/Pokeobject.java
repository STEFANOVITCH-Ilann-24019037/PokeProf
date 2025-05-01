package fr.IlannStefanovitch.PokeProf;

public class Pokeobject {
    private String NomObjet;
    private String Desc;
    private int nb;
    private int VieEnPlus;

    public Pokeobject (String nomObjet,String Desc,int VieEnPlus ,int nb)
    {
        this.Desc=Desc;
        this.nb = nb;
        this.NomObjet = nomObjet;
        this.VieEnPlus = VieEnPlus;
    }
    public Pokeobject(String nomObjet, String Desc, int VieEnPlus)
    {
        this.Desc=Desc;
        nb = 0;
        this.NomObjet = nomObjet;
        this.VieEnPlus = VieEnPlus;
    }

    public String getDesc() {
        return Desc;
    }

    public int getNb() {
        return nb;
    }

    public String getNomObjet() {
        return NomObjet;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    public void useObejct (Pokemon pokemon)
    {
        pokemon.setVie(pokemon.getVie()+VieEnPlus);
        nb -=1;
    }

    public int getVieEnPlus() {
        return VieEnPlus;
    }
}
