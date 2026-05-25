/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import enums.Sexe;
import factory.RegistreFactory;
import utils.GenerateurMatricule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente un professeur dans le système académique.
 *
 * Un professeur :
 * – hérite de Personne
 * – possède un matricule généré automatiquement
 * – peut enseigne plusieurs cours
 */
public class Professeur extends Personne {

    private List<Cours> cours;

    // REGISTRE pour éviter les doublons
    private static final RegistreFactory<Professeur> registre = new RegistreFactory<>();

    /**
     * Constructeur privé.
     * Utiliser Professeur.creer()
     */
    private Professeur(String nom, String prenom, Sexe sexe) {
        super(nom, prenom, sexe, GenerateurMatricule.genererMatriculeProf());

        this.cours = new ArrayList<>();
    }

    /**
     * Permet de creer un professeur unique
     *
     * @param nom nom du professeur
     * @param prenom prénom
     * @param sexe sexe
     * @return professeur unique (anti-doublon)
     */
    public static Professeur creer(String nom, String prenom, Sexe sexe) {

        if (nom == null || prenom == null || sexe == null)
            throw new IllegalArgumentException("Le Prof ne peut pas etre null");

        return registre.validerEtEnregistrer(new Professeur(nom, prenom, sexe));
    }


    // GESTION DES COURS


    /**
     * Ajoute un cours au professeur
     *
     * @param c cours à ajouter
     */
    public void ajouterCours(Cours c) {
        if (c != null && !cours.contains(c)) {
            cours.add(c);
        }
    }

    /**
     * @return liste non modifiable des cours
     */
    public List<Cours> getCours() {
        return Collections.unmodifiableList(cours);
    }


    // EQUALS / HASHCODE


    /**
     * Deux professeurs sont identiques si même matricule
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professeur)) return false;
        Professeur that = (Professeur) o;
        return Objects.equals(getMatricule(), that.getMatricule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricule());
    }


    // AFFICHAGE

    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }
}