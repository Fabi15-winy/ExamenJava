/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package enums;

/**
 * Représente les créneaux horaires disponibles dans l'emploi du temps.
 *
 * Chaque horaire contient un libellé lisible
 * permettant un affichage clair dans les interfaces et documents PDF.
 */
public enum Horaire {

    H08_10("08:00 - 10:00"),
    H10_12("10:00 - 12:00"),
    H13_15("13:00 - 15:00"),
    H15_17("15:00 - 17:00"),
    H18_20("18:00 - 20:00");

    // Libellé affiché (format lisible)
    private final String libelle;

    /**
     * Constructeur de l'enum
     *
     * @param libelle représentation textuelle de l'horaire
     */
    Horaire(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Retourne le libellé lisible de l'horaire
     *
     * @return libellé de l'horaire
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Retourne une représentation textuelle de l'horaire
     *
     * @return libellé de l'horaire
     */
    @Override
    public String toString() {
        return libelle;
    }
}