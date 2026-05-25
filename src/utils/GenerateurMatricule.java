/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package utils;

/**
 * Générateur de matricules pour étudiants et professeurs.
 *

 * Cette classe permet de générer des identifiants uniques
 * pour les personnes du système.
 */
public class GenerateurMatricule {

    private static int compteurEtudiant = 1;
    private static int compteurProf = 1;

    /**
     * Génère un matricule étudiant au format :
     * ETU-26-0001
     */
    public static String genererMatriculeEtudiant() {
        return "ETU-26-" + String.format("%04d", compteurEtudiant++);
    }

    /**
     * Génère un matricule professeur au format :
     * PROF-26-0001
     */
    public static String genererMatriculeProf() {
        return "PROF-26-" + String.format("%04d", compteurProf++);
    }

    /**
     * Reset des compteurs (utile pour tests unitaires)
     */
    public static void reset() {
        compteurEtudiant = 1;
        compteurProf = 1;
    }
}