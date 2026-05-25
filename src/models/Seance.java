/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import enums.Horaire;
import enums.Jour;
import java.util.Objects;
import factory.RegistreFactory;

/**
 * Représente une séance de cours dans le planning.
 *
 * Une séance est définie par :
 * – un jour
 * – un horaire
 * – un cours
 * – un professeur
 * – un local
 * – un etat.
 */
public class Seance {

    private Jour jour;
    private Horaire horaire;
    private Cours cours;
    private Professeur prof;
    private Local local;

    // état de la séance
    private boolean reportee;

    // REGISTRE pour éviter les doublons
    private static final RegistreFactory<Seance> registre = new RegistreFactory<>();

    /**
     * Constructeur privé de Seance
     */
    private Seance(Jour jour, Horaire horaire, Cours cours, Professeur prof, Local local) {
        this.jour = jour;
        this.horaire = horaire;
        this.cours = cours;
        this.prof = prof;
        this.local = local;
        this.reportee = false;
    }

    /**
     * Crée une séance unique à l'aide du registre.
     *
     * @param jour jour de la séance
     * @param horaire créneau horaire de la séance
     * @param cours cours concerné
     * @param prof professeur responsable
     * @param local local où se déroule la séance
     * @return  séance existante ou nouvelle séance enregistrée.
     */

    public static Seance creer(Jour jour, Horaire horaire, Cours cours, Professeur prof, Local local) {

        if (jour == null || horaire == null || cours == null || prof == null || local == null)
            throw new IllegalArgumentException("La Seance ne peut pas être null");

        return registre.validerEtEnregistrer(new Seance(jour, horaire, cours, prof, local)
        );
    }


    // GETTERS && SETTERS


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

    public boolean estReportee() {
        return reportee;
    }


    // GESTION ABSENCE DU PROFESSEUR


    /**
     * Applique une absence professeur.
     *
     * Si le professeur absent correspond à celui de la séance,
     * alors la séance devient "reportée".
     *
     * @param absence absence à traiter
     */
    public void appliquerAbsenceProf(Absence absence) {

       /* if (absence == null || absence.getPersonne() == null) {
            return;
        }*/

        if (absence.getPersonne() instanceof Professeur profAbsent) {

            if (this.prof.equals(profAbsent)) {
                this.reportee = true;
            }
            else {
                System.out.println("l'absence n'est pas cohérente");
            }
        }
    }

    // EQUALS / HASHCODE


    /**
     * Une séance est unique par :
     * jour + horaire + cours + professeur + local
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seance)) return false;

        Seance seance = (Seance) o;

        return jour == seance.jour &&
                horaire == seance.horaire &&
                Objects.equals(cours, seance.cours) &&
                Objects.equals(prof, seance.prof) &&
                Objects.equals(local, seance.local);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jour, horaire, cours, prof, local);
    }


    // AFFICHAGE


    @Override
    public String toString() {

        String statut = reportee ? "REPORTÉE" : prof.getNom();

        return "La Séance de :" + jour + " | " + horaire.getLibelle() + " | " + cours.getLibelle() +
                " | " + statut + " | " + local.getNomLocal();
    }
}