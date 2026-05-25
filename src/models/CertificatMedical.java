/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

/**
 * Représente un certificat médical justifiant une absence.
 * Contient une description et le nom du médecin qui l’a délivré.
 */
public class CertificatMedical {

    private String description;
    private String nomMedecin;

    /**
     * Constructeur du certificat médical
     *
     * @param description raison de l’absence
     * @param nomMedecin nom du médecin
     */
    public CertificatMedical(String description, String nomMedecin) {
        this.description = description;
        this.nomMedecin = nomMedecin;
    }

    // GETTERS && SETTERS

    /**
     * @return description du certificat
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return nom du médecin
     */
    public String getNomMedecin() {
        return nomMedecin;
    }

    @Override
    public String toString() {
        return "Certificat médical : Médecin=" + nomMedecin + ", Raison=" + description ;
    }
}