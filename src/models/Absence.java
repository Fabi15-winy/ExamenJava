package models;

import factory.RegistreFactory;
/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */
import java.time.LocalDate;
import java.util.Objects;

/**
 * Représente l'absence d'une personne à une séance.
 * Une absence peut être justifiée si un certificat médical est fourni.
 */
public class Absence {

    private LocalDate dateAbsence;
    private Personne personne;
    private Seance seance;
    private CertificatMedical certificat;

    private static final RegistreFactory<Absence> registre = new RegistreFactory<>();

    /**
     * Constructeur privé.
     */
    private Absence(Personne personne, Seance seance, CertificatMedical certificat, LocalDate dateAbsence) {
        this.personne = personne;
        this.seance = seance;
        this.certificat = certificat;
        this.dateAbsence = dateAbsence;
    }

    /**
     * Crée une absence unique.
     *
     * @param personne personne absente
     * @param seance séance concernée
     * @param certificat certificat médical éventuel
     * @return absence existante ou nouvellement créée
     */
    public static Absence creer(Personne personne, Seance seance,
                                CertificatMedical certificat, LocalDate dateAbsence) {

        if (dateAbsence.getDayOfWeek() != seance.getJour().toDayOfWeek()) {

            throw new IllegalArgumentException("La date ne correspond pas au jour de la séance");
        }

        if (personne == null || seance == null ) {
            throw new IllegalArgumentException("Absence invalide");
        }

        return registre.validerEtEnregistrer(new Absence(personne, seance, certificat,dateAbsence));
    }

    // GETTERS && SETTERS

    /**
     * @return la personne concernée
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @return la séance concernée
     */
    public Seance getSeance() {
        return seance;
    }

    /**
     * @return le certificat médical ou null si absence non justifier
     */
    public CertificatMedical getCertificat() {
        return certificat;
    }

    public LocalDate getDateAbsence() {
        return dateAbsence;
    }

    /**
     * Permet de modifier le certificat medical ultérieurement
     * @param certificat certificat médical à associer à l'absence, ou null pour supprimer le justificatif
     */
    public void setCertificat(CertificatMedical certificat) {
        this.certificat = certificat;
    }

    /**
     * @return true si l'absence est justifiée (certificat medical present)
     */
    public boolean estJustifiee() {
        return certificat != null;
    }


    /**
     * Deux absences sont identiques si elles concernent
     * la même personne et la même séance.
     */
    @Override

    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Absence)) {
            return false;
        }

        Absence absence = (Absence) o;

        if (!personne.equals(absence.personne)) {
            return false;
        }

        if (!seance.equals(absence.seance)) {
            return false;
        }

        if (!dateAbsence.equals(absence.dateAbsence)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personne, seance);
    }

    @Override
    public String toString() {
        return personne + " - " + seance;
    }
}