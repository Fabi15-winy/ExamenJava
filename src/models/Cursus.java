/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import factory.RegistreFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Représente un cursus académique.
 *
 * Un cursus contient :
 * — un code unique
 * — un nom
 * — une liste de cours
 * — un planning de séances.
 */
public class Cursus {

    private String codeCursus;
    private String nomCursus;

    private List<Cours> cours;
    private List<Seance> planning;

    private static final RegistreFactory<Cursus> registre = new RegistreFactory<>();

    /**
     * Constructeur privé.
     * Utiliser Cursus.creer(...) pour créer un cursus.
     *
     * @param codeCursus code unique du cursus
     * @param nomCursus nom du cursus
     */
    private Cursus(String codeCursus, String nomCursus) {
        this.codeCursus = codeCursus;
        this.nomCursus = nomCursus;
        this.cours = new ArrayList<>();
        this.planning = new ArrayList<>();
    }

    /**
     * Crée un cursus unique.
     *
     * @param codeCursus code du cursus
     * @param nomCursus nom du cursus
     * @return cursus existant ou nouvellement créé
     */
    public static Cursus creer(String codeCursus, String nomCursus) {
        if (codeCursus == null || nomCursus == null) throw new IllegalArgumentException("Cursus invalide");

        return registre.validerEtEnregistrer(
                new Cursus(codeCursus, nomCursus)
        );
    }


    // GESTION DES COURS


    /**
     * Ajoute un cours au cursus.
     *
     * @param c cours à ajouter
     */
    public void ajouterCours(Cours c) {
        if (c != null && !cours.contains(c)) {
            cours.add(c);
        }
    }


    // GESTION DU PLANNING


    /**
     * Ajoute une séance au planning en vérifiant :
     * — les conflits de local ;
     * — les conflits de professeur.
     *
     * @param nouvelleSeance séance à ajouter
     * @return true si la séance est ajoutée, false sinon
     */
    public boolean ajouterSeance(Seance nouvelleSeance) {

        if (nouvelleSeance == null) {
            return false;
        }

        for (Seance s : planning) {

            boolean memeCreneau = s.getJour() == nouvelleSeance.getJour() && s.getHoraire() == nouvelleSeance.getHoraire();

            if (memeCreneau) {

                // Conflit de local
                if (s.getLocal().equals(nouvelleSeance.getLocal())) {
                    System.out.println("le local est déja occupé");
                    return false;
                }

                // Conflit de professeur
                if (s.getProf().equals(nouvelleSeance.getProf())) {
                    System.out.println("le prof est déja occupé");
                    return false;
                }
            }
        }

        planning.add(nouvelleSeance);
        return true;
    }


    // GETTERS && SETTERS


    public String getCodeCursus() {
        return codeCursus;
    }

    public String getNomCursus() {
        return nomCursus;
    }

    /**
     * @return liste non modifiable des cours
     */
    public List<Cours> getCours() {
        return Collections.unmodifiableList(cours);
    }

    /**
     * @return liste non modifiable des séances
     */
    public List<Seance> getPlanning() {
        return Collections.unmodifiableList(planning);
    }


    // EQUALS && HASHCODE


    /**
     * Deux cursus sont identiques s'ils possèdent
     * le même code.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cursus)) return false;
        Cursus cursus = (Cursus) o;
        return Objects.equals(codeCursus, cursus.codeCursus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeCursus);
    }


    // AFFICHAGE


    @Override
    public String toString() {
        return nomCursus;
    }
}