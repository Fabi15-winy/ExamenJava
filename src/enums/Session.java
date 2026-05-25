/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package enums;

/**+
 * Représente les sessions d'examen Possibles.
 *
 * Une note peut être associée soit à la session de juin,
 * soit à la session de seconde chance en août.
 */
public enum Session {

    JUIN,
    AOUT;

    /**
     * Retourne une représentation textuelle de la session.
     *
     * @return nom de la session
     */
    @Override
    public String toString() {
        return name();
    }
}