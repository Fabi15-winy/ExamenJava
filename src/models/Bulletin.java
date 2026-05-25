/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import enums.Session;

import java.util.*;

/**
 * Représente le bulletin d'un étudiant.
 *
 * Un bulletin contient :
 * – une liste de notes
 * – une logique de sélection des meilleures notes
 * – un calcul de moyenne pondérée.
 */
public class Bulletin {

    private String anneeAcademique;
    private Etudiant etudiant;
    private List<Note> notes;

    /**
     * Constructeur du bulletin
     */
    public Bulletin(String anneeAcademique, Etudiant etudiant) {
        this.anneeAcademique = anneeAcademique;
        this.etudiant = etudiant;
        this.notes = new ArrayList<>();
    }


    // GETTERS

    public String getAnneeAcademique() {
        return anneeAcademique;
    }

    /**
     * @return toutes les notes du bulletin
     */
    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }


    // GESTION DES NOTES

    /**
     * Ajoute une note au bulletin
     * Une note est acceptée uniquement si l'étudiant
     * peut présenter l'examen du cours.
     */
    public void ajouterNote(Note note) {

        if (note == null) {
            throw new IllegalArgumentException("La note ne peut pas être null");
        }

        if (!etudiant.peutPresenterExamen(note.getCours())) {

            throw new IllegalStateException(etudiant.getPrenom() + " ne peut pas présenter l'examen de "
                            + note.getCours().getLibelle() + " en raison de ses absences!\n");
        }

        notes.add(note);
    }

    /**
     * Détermine si une nouvelle note est meilleure qu'une ancienne
     * (priorité à la session AOUT)
     */
    boolean estMeilleureNote(Note nouvelle, Note ancienne) {
        if (nouvelle.getSession() == Session.AOUT) {
            return true;
        }
        return ancienne.getSession() != Session.AOUT;
    }

    /**
     * Détermine si une nouvelle note est meilleure qu'une ancienne
     *  (priorité à la session AOUT)
     *
     *  Calcule la moyenne pondérée du bulletin
     *
     */
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

        double moyenne;

        if (coeffTotal == 0) {
            moyenne = 0;
        } else {
            moyenne = total / coeffTotal;
        }

        return Math.round(moyenne * 100.0) / 100.0;
    }

}