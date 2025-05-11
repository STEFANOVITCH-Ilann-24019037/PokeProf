package fr.Sae.Test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AttackLoader {

    private static  List<Attack> attacks = new ArrayList<>();

    public static List<Attack> loadAttacks(String filepath) {


        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    int vieEnMoins = Integer.parseInt(parts[0]);
                    String nomAttack = parts[1];
                    String type = parts[2];
                    int  energie = Integer.parseInt(parts[3]);
                    String effect = parts[4];
                    attacks.add(new Attack(vieEnMoins, nomAttack, type,energie,new Etat(effect)));
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des attaques : " + e.getMessage());
        }

        return attacks;
    }

}
