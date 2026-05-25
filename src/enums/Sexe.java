/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package enums;

/**
 * Représente le sexe d'une personne (les étudiants et les professeurs) dans le système académique.
 */
public enum Sexe {

    MASCULIN,
    FEMININ,
    AUTRE;

    /**
     * Retourne une représentation textuelle du sexe
     * @return nom du sexe
     */
    @Override
    public String toString() {
        return name();
    }
}