package fr.IlannStefanovitch.PokeProf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokeobjectLoader {

    private static List<Pokeobject> iteme=  new ArrayList<>();

    public static List<Pokeobject> load(){
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/object.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String NomObject = parts[0];
                    String Desc = parts[1];
                    int VieEnPlus = Integer.parseInt(parts[2]);
                    int Nb = Integer.parseInt(parts[3]);
                    iteme.add(new Pokeobject(NomObject,Desc,VieEnPlus,Nb));
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des attaques : " + e.getMessage());
        }
        return iteme;
    }
}
