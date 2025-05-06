package fr.IlannStefanovitch.PokeProf;

import java.util.Objects;

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

    public Pokeobject(Pokeobject pokeobject){
        this.Desc = pokeobject.Desc;
        this.nb = pokeobject.nb;
        this.NomObjet = pokeobject.NomObjet;
        this.VieEnPlus = pokeobject.VieEnPlus;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokeobject that = (Pokeobject) o;
        return this.NomObjet.equals(that.NomObjet); // ou un autre champ identifiant
    }

    @Override
    public int hashCode() {
        return Objects.hash(NomObjet); // ou le même champ que dans equals
    }
}
