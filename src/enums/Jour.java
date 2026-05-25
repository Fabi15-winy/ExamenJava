/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package enums;

import java.time.DayOfWeek;

/**
 * Représente les jours de la semaine utilisés dans l'emploi du temps.
 *
 * Cet enum est utilisé pour planifier les séances.
 */
public enum Jour {

    LUNDI,
    MARDI,
    MERCREDI,
    JEUDI,
    VENDREDI;

/**
 * Cette méthode permet de faire le lien entre
 * les jours personnalisés et les jours standards de Java utilisés avec LocalDate.
 *
 * @return le jour correspondant en DayOfWeek
 */
    public DayOfWeek toDayOfWeek() {

        switch (this) {

            case LUNDI:
                return DayOfWeek.MONDAY;

            case MARDI:
                return DayOfWeek.TUESDAY;

            case MERCREDI:
                return DayOfWeek.WEDNESDAY;

            case JEUDI:
                return DayOfWeek.THURSDAY;

            case VENDREDI:
                return DayOfWeek.FRIDAY;

            default:
                return null;
        }
    }

    /**
     * Retourne une représentation du jour.
     *
     * @return nom du jour en majuscule (par défaut)
     */
    @Override
    public String toString() {
        return name();
    }
}