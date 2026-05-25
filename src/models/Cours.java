/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import factory.RegistreFactory;

import java.util.*;

/**
 * Représente un cours dans le système académique.
 * Un cours possède :
 * – un code unique
 * – un libellé
 * – un coefficient
 * – un local principal
 * – une liste de professeurs
 * – une liste de séances.

 */
public class Cours {

    private String codeCours;
    private String libelle;
    private int coefficient;
    private int nombreSeance;

    private List<Professeur> profs;
    private List<Seance> seances; //semaine

    private static final RegistreFactory<Cours> registre = new RegistreFactory<>();

    /**
     * Constructeur privé.
     * Utiliser Cours.creer(...) pour créer un cours.
     */
    private Cours(String codeCours, String libelle, int coefficient, int nombreSeance) {
        this.codeCours = codeCours;
        this.libelle = libelle;
        this.coefficient = coefficient;
        this.nombreSeance = nombreSeance;
        this.profs = new ArrayList<>();
        this.seances = new ArrayList<>();
    }

    /**
     * Crée un cours unique.
     *
     * @param codeCours code unique du cours
     * @param libelle nom du cours
     * @param coefficient poids du cours
     * @return cours existant ou le cours nouvellement créé
     */
    public static Cours creer(String codeCours, String libelle, int coefficient, int nombreSeance) {

        if (codeCours == null || libelle == null ) throw new IllegalArgumentException("Cours invalide");

        if (coefficient <= 0 || nombreSeance <= 0)
            throw new IllegalArgumentException("Cours invalide : coefficient," +
                    " nombreSeance doivent être positif");


        return registre.validerEtEnregistrer(new Cours(codeCours, libelle, coefficient, nombreSeance ));
    }

    /**
     * Ajoute un professeur au cours.
     * @param p  professeur
     */
    public void ajouterProf(Professeur p) {
        if (p != null && !profs.contains(p)) {
            profs.add(p);
        }
    }

    // GETTERS && SETTERS

    public String getLibelle() {
        return libelle;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public String getCodeCours() {
        return codeCours;
    }

    public int getNombreSeance() {return nombreSeance;}

    public List<Professeur> getProfs() {
        return Collections.unmodifiableList(profs);
    }

    public List<Seance> getSeances() {
        return Collections.unmodifiableList(seances);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cours)) return false;
        Cours cours = (Cours) o;
        return Objects.equals(codeCours, cours.codeCours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeCours);
    }

    @Override
    public String toString() {
        return libelle;
    }
}