package models;

import enums.Sexe;

import java.util.*;

public class Etudiant extends Personne {

    private Cursus cursus;
    private List<Note> notes;
    private Bulletin bulletin;

    // constructeur
    public Etudiant(String nom, String prenom, Sexe sexe) {
        super(nom, prenom,sexe,GenerateurMatricule.genererMatriculeEtudiant());
        this.notes = new ArrayList<>();
        this.bulletin = new Bulletin(2026, this);

    }
    // methodes

    public Cursus getCursus() {
        return cursus;
    }
    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }
    public Bulletin getBulletin() {
        return bulletin;
    }

    public List<Cours> getCours() {
        if (cursus == null) return new ArrayList<>();
        return cursus.getCours();
    }

    // absences non justifiées
    public int getAbsencesNonJustifiees(Cours cours) {

        int count = 0;

        for (Absence a : absences) {

            // absence liée au cours
            if (a.getSeance().getCours().equals(cours)) {

                // absence non justifiée
                if (!a.estJustifiee()) {

                    count++;
                }
            }
        }

        return count;
    }

    // version simple (à affiner avec séances)
    public double calculerTauxPresence(Cours cours) {

        int total = 0;
        int absencesCours = 0;

        for (Seance s : cursus.getPlanning()) {

            if (s.getCours() == cours) {
                total++;

                for (Absence a : getAbsences()) {
                    if (a.getSeance() == s) {
                        absencesCours++;
                    }
                }
            }
        }

        if (total == 0) return 0;

        return ((total - absencesCours) * 100.0) / total;
    }

    public boolean peutPresenterExamen(Cours cours) {

        int absences = getAbsencesNonJustifiees(cours);

        double taux = calculerTauxPresence(cours);

        return absences <= 10 || taux >= 60;
    }



}

