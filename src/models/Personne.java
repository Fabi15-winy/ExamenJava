/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import enums.Sexe;

import java.util.*;

/**
 * Classe abstraite représentant une personne du système.
 * Peut-être un étudiant ou un professeur.
 * Gère les absences de la personne.
 */
public abstract class Personne {

    protected String nom;
    protected String prenom;
    protected Sexe sexe;
    protected String matricule;

    protected List<Absence> absences;

    /**
     * Constructeur d'une personne
     */
    public Personne(String nom, String prenom, Sexe sexe, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.matricule = matricule;
        this.absences = new ArrayList<>();
    }


    // GETTERS && SETTERS


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public Sexe getSexe() {
        return sexe;
    }

    /**
     * Retourne une copie des absences
     */
    public List<Absence> getAbsences() {
        return Collections.unmodifiableList(absences);
    }


    // GESTION DES ABSENCES


    /**
     * Ajoute une absence à la personne
     */
    public void ajouterAbsence(Absence a) {
        if (a != null && !absences.contains(a)) {
            absences.add(a);
        }
    }

    /**
     * Nombre total d'absences
     */
    public int getTotalAbsences() {
        return absences.size();
    }

    /**
     * Calcule le nombre d'absences non justifiées.
     *
     * Une absence est considérée comme non justifiée lorsqu'elle
     * ne possède aucun certificat médical.
     *
     * @return nombre total d'absences non justifiées
     */
    public long getAbsencesNonJustifiees() {

        long compteur = 0;

        for (Absence absence : absences) {

            if (!absence.estJustifiee()) {
                compteur++;
            }
        }
        return compteur;
    }

    /**
     * Vérifie si la personne possède un certificat médical
     * pour une séance donnée.
     * @param seance séance à vérifier
     * @return true si la personne possède un certificat médical pour cette séance, false sinon
     */
    public boolean aCertificatPour(Seance seance) {

        // Parcours de toutes les absences
        for (Absence absence : absences) {

            // Vérifie si l'absence concerne la séance demandée
            if (absence.getSeance().equals(seance)) {

                // Vérifie si l'absence est justifiée
                if (absence.estJustifiee()) {
                    return true;
                }
            }
        }
        return false;
    }


    // AFFICHAGE


    @Override
    public String toString() {
        return nom + " " + prenom + " (" + matricule + ")";
    }
}