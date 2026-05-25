/**
 * @auteur: Takam winy
 *
 */

// =========================
// FICHIER : src/test/java/models/CursusTest.java
// =========================
package models;

import enums.Horaire;
import enums.Jour;
import enums.Sexe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursusTest {

    @Test
    void testCreationCursus() {

        Cursus cursus = Cursus.creer("DEV", "Développement d'applications");

        assertEquals("DEV", cursus.getCodeCursus());
        assertEquals("Développement d'applications", cursus.getNomCursus());
    }

    @Test
    void testAjoutCours() {

        Cursus cursus = Cursus.creer("TEST1", "Cursus Test 1");

        Cours java = Cours.creer("INF101", "Java", 5,30);

        cursus.ajouterCours(java);

        assertEquals(1, cursus.getCours().size());
        assertTrue(cursus.getCours().contains(java));
    }

    @Test
    void testAjoutCoursSansDoublon() {

        Cursus cursus = Cursus.creer("TEST2", "Cursus Test 2");

        Cours java = Cours.creer("INF102", "Java Avancé", 5,30);

        cursus.ajouterCours(java);
        cursus.ajouterCours(java);

        assertEquals(1, cursus.getCours().size());
    }

    @Test
    void testAjoutSeanceSansConflit() {

        Cursus cursus = Cursus.creer("TEST3", "Cursus Test 3");

        Local local = Local.creer("103","Salle 103");

        Cours cours = Cours.creer("INF103", "Réseaux", 4,30);

        Professeur prof = Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);

        Seance seance = Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);

        assertTrue(cursus.ajouterSeance(seance));
        assertEquals(1, cursus.getPlanning().size());
    }

    @Test
    void testConflitLocal() {

        Cursus cursus = Cursus.creer("TEST4", "Cursus Test 4");

        Local local = Local.creer("104","Salle 104");

        Cours c1 = Cours.creer("INF104", "Java", 5,30);
        Cours c2 = Cours.creer("INF105", "BD", 4,30);

        Professeur p1 = Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);

        Professeur p2 = Professeur.creer("Martin", "Paul", Sexe.MASCULIN);

        Seance s1 = Seance.creer(Jour.MARDI, Horaire.H10_12, c1, p1, local);

        Seance s2 = Seance.creer(Jour.MARDI, Horaire.H10_12, c2, p2, local);

        assertTrue(cursus.ajouterSeance(s1));
        assertFalse(cursus.ajouterSeance(s2));
    }

    @Test
    void testConflitProfesseur() {

        Cursus cursus = Cursus.creer("TEST5", "Cursus Test 5");

        Local local1 = Local.creer("105","Salle 102");
        Local local2 = Local.creer("106","Salle 106");

        Cours c1 = Cours.creer("INF106", "OS", 4,30);
        Cours c2 = Cours.creer("INF107", "Math", 3,30);

        Professeur prof = Professeur.creer("Durand", "Luc", Sexe.MASCULIN);

        Seance s1 = Seance.creer(Jour.MERCREDI, Horaire.H13_15, c1, prof, local1);

        Seance s2 = Seance.creer(Jour.MERCREDI, Horaire.H13_15, c2, prof, local2);

        assertTrue(cursus.ajouterSeance(s1));
        assertFalse(cursus.ajouterSeance(s2));
    }

    @Test
    void testCreationSansDoublon() {

        Cursus c1 = Cursus.creer("DEV2", "Développement");

        Cursus c2 = Cursus.creer("DEV2", "Développement");

        assertSame(c1, c2);
    }

    @Test
    void testToString() {

        Cursus cursus = Cursus.creer("DEV3", "Informatique");

        assertEquals("Informatique", cursus.toString());
    }
}