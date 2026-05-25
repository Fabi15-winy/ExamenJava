/**
 * @auteur: Takam winy
 *
 */
package models;

import enums.Horaire;
import enums.Jour;
import enums.Session;
import enums.Sexe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Bulletin
 */
class BulletinTest {

    /**
     * Vérifie qu'une note est correctement ajoutée
     * au bulletin.
     */
    @Test
    void testAjouterNote() {

        Local local = Local.creer("A1", "Salle A");

        Professeur prof = Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);

        Cours java = Cours.creer("INF101", "Java", 3,30);

        java.ajouterProf(prof);

        Cursus cursus = Cursus.creer("DEV2026", "Développement");

        cursus.ajouterCours(java);
        Etudiant etudiant = Etudiant.creer("Takam", "Winy", Sexe.FEMININ);

        etudiant.setCursus(cursus);

        // Séance
        Seance seance = Seance.creer(Jour.LUNDI, Horaire.H08_10, java, prof, local);

        cursus.ajouterSeance(seance);


        Note note = Note.creer(15, java, Session.JUIN);

        // Ajout
        etudiant.getBulletin().ajouterNote(note);

        // Vérification
        assertEquals(1, etudiant.getBulletin().getNotes().size());
    }

    /**
     * Vérifie qu'une exception est lancée
     * si la note est null.
     */
    @Test
    void testAjoutNoteNull() {

        Etudiant etudiant = Etudiant.creer("Ali", "Said", Sexe.MASCULIN);

        Bulletin bulletin = new Bulletin("2025-2026", etudiant);

        assertThrows(IllegalArgumentException.class, () -> bulletin.ajouterNote(null));
    }

    /**
     * Vérifie le calcul correct de la moyenne.
     */
    @Test
    void testCalculMoyenne() {

        // Local
        Local local = Local.creer("B1", "Salle B");

        // Prof
        Professeur prof = Professeur.creer("Martin", "Claire", Sexe.FEMININ);

        // Cours
        Cours java = Cours.creer("INF201", "Java", 2,30);

        Cours bd = Cours.creer("INF202", "Base de données", 4,30);

        java.ajouterProf(prof);
        bd.ajouterProf(prof);

        // Cursus
        Cursus cursus = Cursus.creer("DEV", "Développement");

        cursus.ajouterCours(java);
        cursus.ajouterCours(bd);

        // Étudiant
        Etudiant etudiant = Etudiant.creer("Takam", "Winy", Sexe.FEMININ);

        etudiant.setCursus(cursus);

        // Séances
        Seance s1 = Seance.creer(Jour.LUNDI, Horaire.H08_10, java, prof, local);

        Seance s2 = Seance.creer(Jour.MARDI, Horaire.H10_12, bd, prof, local);

        cursus.ajouterSeance(s1);
        cursus.ajouterSeance(s2);

        // Notes
        Note n1 = Note.creer(10, java, Session.JUIN);

        Note n2 = Note.creer(20, bd, Session.JUIN);

        etudiant.getBulletin().ajouterNote(n1);
        etudiant.getBulletin().ajouterNote(n2);

        // Moyenne pondérée
        // (10×2 + 20×4) / 6 = 16.66

        double moyenne = etudiant.getBulletin().calculerMoyenne();

        assertEquals(16.66, moyenne, 0.1);
    }

    /**
     * Vérifie que la session d'août
     * remplace celle de juin.
     */
    @Test
    void testPrioriteSessionAout() {

        Local local = Local.creer("C1", "Salle C");

        Professeur prof = Professeur.creer("Dupont", "Marc", Sexe.MASCULIN);

        Cours cours = Cours.creer("INF300", "Algorithmique", 3,30);

        cours.ajouterProf(prof);

        Cursus cursus = Cursus.creer("INFO", "Informatique");

        cursus.ajouterCours(cours);

        Etudiant etudiant = Etudiant.creer("Takam", "Winy", Sexe.FEMININ);

        etudiant.setCursus(cursus);

        Seance seance = Seance.creer(Jour.JEUDI, Horaire.H13_15, cours, prof, local);

        cursus.ajouterSeance(seance);

        // Juin
        Note juin = Note.creer(8, cours, Session.JUIN);

        // Août
        Note aout = Note.creer(15, cours, Session.AOUT);

        etudiant.getBulletin().ajouterNote(juin);
        etudiant.getBulletin().ajouterNote(aout);

        double moyenne = etudiant.getBulletin().calculerMoyenne();

        // La note d'août doit être utilisée
        assertEquals(15, moyenne);
    }
}