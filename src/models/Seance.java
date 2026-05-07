package models;

import enums.Horaire;
import enums.Jour;

public class Seance {

    private Jour jour;
    private Horaire horaire;

    private Cours cours;
    private Professeur prof;
    private Local local;

    private boolean annulee;

    public Seance(Jour jour, Horaire horaire, Cours cours, Professeur prof, Local local) {

        this.jour = jour;
        this.horaire = horaire;
        this.cours = cours;
        this.prof = prof;
        this.local = local;
        this.annulee = false;
    }

    // GETTERS

    public Jour getJour() {
        return jour;
    }

    public Horaire getHoraire() {
        return horaire;
    }

    public Cours getCours() {
        return cours;
    }

    public Professeur getProf() {
        return prof;
    }

    public Local getLocal() {
        return local;
    }

    public boolean estAnnulee() {
        return annulee;
    }

    // METHODES


    @Override
    public String toString() {

        String statut = annulee ? "Séance reportée" : prof.getNom();

        return " | "
                + horaire.getLibelle()
                + " | "
                + cours.getLibelle()
                + " | "
                + statut
                + " | "
                + local.getNomLocal();
    }
}