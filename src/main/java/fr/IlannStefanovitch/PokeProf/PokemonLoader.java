package fr.IlannStefanovitch.PokeProf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokemonLoader {

    public static List<Pokemon> loadPokemons(List<Attack> attackList) {
        List<Pokemon> pokemons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(PokemonLoader.class.getResourceAsStream("/pokemon.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String nom = parts[0];
                    int vie = Integer.parseInt(parts[1]);
                    String type = parts[2];

                    String[] indices = parts[3].split(",");
                    Attack[] attacks = new Attack[indices.length];
                    for (int i = 0; i < indices.length; i++) {
                        int idx = Integer.parseInt(indices[i]);
                        attacks[i] = attackList.get(idx);
                    }

                    pokemons.add(new Pokemon(nom, vie, attacks, type));
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des pokémons : " + e.getMessage());
        }

        return pokemons;
    }
}
