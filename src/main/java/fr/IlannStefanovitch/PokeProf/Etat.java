package fr.IlannStefanovitch.PokeProf;

import java.util.Objects;

public class Etat {
    private final String etat;

    public Etat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Etat other = (Etat) obj;
        return Objects.equals(etat, other.etat);
    }

    @Override
    public int hashCode() {
        return etat != null ? etat.hashCode() : 0;
    }
}
