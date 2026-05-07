package models;

import enums.Jour;

import java.util.*;

public class Cursus {
    private String codeCursus;
    private String nomCursus;
    private List<Cours> cours = new ArrayList<>();
    private List<Seance> planning = new ArrayList<>();;

    public Cursus(String codeCursus,String nomCursus) {
        this.codeCursus = codeCursus;
        this.nomCursus = nomCursus;

    }


    //methodes



    public void ajouterCours(Cours c) {
        cours.add(c);
    }
    public void ajouterSeance(Seance nouvelleSeance) {

        for (Seance s : planning) {

            boolean memeCreneau =
                    s.getJour() == nouvelleSeance.getJour()
                            && s.getHoraire() == nouvelleSeance.getHoraire();

            if (memeCreneau) {

                // 🔴 conflit local
                if (s.getLocal().getNomLocal()
                        .equals(nouvelleSeance.getLocal().getNomLocal())) {

                    System.out.println("❌ Conflit local : "
                            + s.getLocal().getNomLocal());

                    return;
                }

                // 🔴 conflit professeur
                if (s.getProf().getMatricule()
                        .equals(nouvelleSeance.getProf().getMatricule())) {

                    System.out.println("❌ Conflit professeur : "
                            + s.getProf().getNom());

                    return;
                }
            }
        }

        planning.add(nouvelleSeance);

        System.out.println("✅ Séance ajoutée avec succès");
    }

    public List<Cours> getCours() {
        return cours;
    }
    public String getNomCursus() {
        return nomCursus;
    }

    public List<Seance> getPlanning() {
        return planning;
    }

    public void afficherPlanningSemaine() {

        for (Jour j : Jour.values()) {

            System.out.println("\n=== " + j + " ===");

            for (Seance s : planning) {

                if (s.getJour() == j) {

                    System.out.println(s);
                }
            }
        }
    }

}
