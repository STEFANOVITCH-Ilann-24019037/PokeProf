package fr.Sae.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CarteLoader {

    private static List<Carte> cartes = new ArrayList<>();
    private static List<Integer> listId = new ArrayList<>();

    /**
     * Charge toutes les cartes depuis un fichier.
     * À appeler avant chaque démarrage du jeu.
     *
     * @param filepath   le chemin du fichier de cartes
     * @param attackList la liste des attaques disponibles
     * @return la liste des cartes chargées
     */
    public static List<Carte> load(String filepath, List<Attack> attackList) {
        cartes.clear();
        listId.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 10) {
                    String img = parts[0];
                    int vie = Integer.parseInt(parts[1]);
                    int niveau = Integer.parseInt(parts[2]);
                    int id = Integer.parseInt(parts[3]);
                    int idPere = Integer.parseInt(parts[4]);
                    int type = Integer.parseInt(parts[5]);
                    String nom = parts[6];
                    int retraite = Integer.parseInt(parts[7]);

                    // Correction ici : lecture des indices d'attaques depuis parts[8]
                    String[] indices = parts[8].split(",");
                    Attack[] attacks = new Attack[indices.length];
                    for (int i = 0; i < indices.length; i++) {
                        int idx = Integer.parseInt(indices[i]);
                        if (idx >= 0 && idx < attackList.size()) {
                            attacks[i] = attackList.get(idx);
                        } else {
                            System.out.println("Index d'attaque invalide : " + idx);
                        }
                    }

                    // Ajout de la carte avec un ID unique aléatoire
                    cartes.add(new Carte(img, vie, niveau, id, idPere, type, nom, attacks, retraite, getRandom(0, 70)));
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des Cartes : " + e.getMessage());
        }

        return cartes;
    }

    public static int getRandom(int min, int max) {
        int range = (max - min) + 1;
        int attempts = 0;
        while (attempts < range * 2) {  // pour éviter une boucle infinie
            int random = (int) (Math.random() * range + min);
            if (!listId.contains(random)) {
                listId.add(random);
                return random;
            }
            attempts++;
        }
        throw new RuntimeException("Plus d'IDs uniques disponibles dans la plage !");
    }
}
