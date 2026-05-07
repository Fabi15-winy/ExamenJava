package models;

import enums.Session;

import java.util.*;

public class Bulletin {
    private int anneeAcademique;
    private Etudiant etudiant;
    private List<Note> notes;

    public Bulletin(int anneeAcademique, Etudiant etudiant) {
        this.anneeAcademique = anneeAcademique;
        this.etudiant = etudiant;
        this.notes = new ArrayList<>();
    }

    // ajouter une note
    public void ajouterNote(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return notes;
    }

    boolean estMeilleureNote(Note nouvelle, Note ancienne) {

        if (nouvelle.getSession() == Session.AOUT) {
            return true;
        }

        return ancienne.getSession() != Session.AOUT;
    }

    public double calculerMoyenne() {

        Map<Cours, Note> meilleuresNotes = new HashMap<>();

        for (Note n : notes) {

            Cours cours = n.getCours();

            if (!meilleuresNotes.containsKey(cours)) {

                meilleuresNotes.put(cours, n);

            } else {

                Note existante = meilleuresNotes.get(cours);

                if (estMeilleureNote(n, existante)) {

                    meilleuresNotes.put(cours, n);
                }
            }
        }

        double total = 0;
        int coeffTotal = 0;

        for (Note n : meilleuresNotes.values()) {
            total += n.getValeur() * n.getCours().getCoefficient();
            coeffTotal += n.getCours().getCoefficient();
        }

        if (coeffTotal == 0) return 0;

        return total / coeffTotal;
    }

    private Map<Cours, Note> getMeilleuresNotesParCours() {

        Map<Cours, Note> meilleures = new HashMap<>();

        for (Note n : notes) {

            Cours cours = n.getCours();

            if (!meilleures.containsKey(cours)) {
                meilleures.put(cours, n);
            } else {

                Note existante = meilleures.get(cours);

                if (n.getSession() == Session.AOUT
                        || (n.getSession() == Session.JUIN
                        && existante.getSession() != Session.AOUT)) {

                    meilleures.put(cours, n);
                }
            }
        }

        return meilleures;
    }
    public void afficherBulletin() {

        System.out.println("===== BULLETIN =====");
        System.out.println("Étudiant : " + etudiant.getNom() + " " + etudiant.getPrenom());
        System.out.println("Année : " + anneeAcademique);
        System.out.println("--------------------------------");

        Map<Cours, Note> map = getMeilleuresNotesParCours();

        for (Map.Entry<Cours, Note> entry : map.entrySet()) {

            Cours c = entry.getKey();
            Note n = entry.getValue();

            String statut = (n.getValeur() >= 10) ? "VALIDÉ" : "AJOURNÉ";

            System.out.println(
                    c.getLibelle()
                            + " | models.Note : " + n.getValeur()
                            + " | enums.Session : " + n.getSession()
                            + " | " + statut
            );
        }
    }
}
