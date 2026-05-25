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
 * Représente un étudiant.
 *
 * Un étudiant :
 * — hérite de la classe Personne ;
 * — possède un matricule généré automatiquement ;
 * — appartient à un cursus ;
 * — possède une liste de notes ;
 * — possède un bulletin.
 *
 */
public class Etudiant extends Personne {

    private Cursus cursus;
    private List<Note> notes;
    private Bulletin bulletin;

    private static final RegistreFactory<Etudiant> registre = new RegistreFactory<>();

    /**
     * Constructeur privé.
     * Utiliser Etudiant.creer(...) pour créer un étudiant.
     *
     * @param nom nom de l'étudiant
     * @param prenom prénom de l'étudiant
     * @param sexe sexe de l'étudiant
     */
    private Etudiant(String nom, String prenom, Sexe sexe) {
        super(nom, prenom, sexe, GenerateurMatricule.genererMatriculeEtudiant());

        this.notes = new ArrayList<>();
        this.bulletin = new Bulletin("2025 - 2026", this);
    }

    /**
     * Crée un nouvel étudiant.
     *
     * @param nom nom
     * @param prenom prénom
     * @param sexe sexe
     * @return étudiant créé
     */
    public static Etudiant creer(String nom, String prenom, Sexe sexe) {
        return registre.validerEtEnregistrer(new Etudiant(nom, prenom, sexe));
    }


    // GESTION DU CURSUS


    /**
     * Définit le cursus de l'étudiant.
     *
     * @param cursus cursus à associer
     */
    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }

    /**
     * @return cursus de l'étudiant
     */
    public Cursus getCursus() {
        return cursus;
    }


    // GESTION DES NOTES

    /**
     * @return liste non modifiable des notes
     */
    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    /**
     * @return bulletin de l'étudiant
     */
    public Bulletin getBulletin() {
        return bulletin;
    }

    /**
     * Retourne les cours suivis par l'étudiant.
     *
     * @return liste des cours du cursus
     */
    public List<Cours> getCours() {
        if (cursus == null) {
            return new ArrayList<>();
        }
        return cursus.getCours();
    }


    // GESTION DES ABSENCES


    /**
     * Calcule le nombre d'absences non justifiées
     * pour un cours donné.
     *
     * @param cours cours concerné
     * @return nombre d'absences non justifiées
     */
    public int getAbsencesNonJustifiees(Cours cours) {

        int count = 0;

        for (Absence a : absences) {

            if (a.getSeance().getCours().equals(cours) && !a.estJustifiee()) {
                count++;
            }
        }

        return count;
    }

    /**
     * Calcule le taux de présence pour un cours.
     *
     * @param cours cours concerné
     * @return taux de présence en pourcentage
     */
    public double calculerTauxPresence(Cours cours) {

        int totalSeances = cours.getNombreSeance();

        if (totalSeances == 0) {
            return 0;
        }

        int absencesCours = 0;

        for (Absence a : absences) {

            if (a.getSeance().getCours().equals(cours)) {
                absencesCours++;
            }
        }

        return ((totalSeances - absencesCours) * 100.0)
                / totalSeances;
    }

    /**
     * Vérifie si l'étudiant peut présenter l'examen.
     *
     * Conditions :
     * — maximum 10 absences non justifiées ;
     * — au moins 60 % de présence.
     *
     * @param cours cours concerné
     * @return true si l'étudiant est autorisé
     */
    public boolean peutPresenterExamen(Cours cours) {

        int absences = getAbsencesNonJustifiees(cours);
        double taux = calculerTauxPresence(cours);

        return absences <= 10 && taux >= 60;
    }


    // EQUALS / HASHCODE


    /**
     * Deux étudiants sont identiques s'ils possèdent
     * le même matricule.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etudiant)) return false;
        Etudiant etudiant = (Etudiant) o;

        return Objects.equals(getMatricule(),
                etudiant.getMatricule());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatricule());
    }


    // AFFICHAGE


    @Override
    public String toString() {
        return getNom() + " " + getPrenom()+ " " + getSexe()+ " " + getMatricule();
    }
}