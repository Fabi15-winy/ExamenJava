/**
 * @auteur: Takam winy
 *
 */
package models;

import enums.Sexe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Cours.
 * Vérifie :
 * – création via Factory
 * — ajout de professeurs
 * — suppression des doublons
 * — comportement equals/hashCode (Registry)
 */
class CoursTest {

    @Test
    void testCreationCours() {

        Cours cours = Cours.creer("INF101", "Programmation Java", 5,30);

        assertEquals("INF101", cours.getCodeCours());
        assertEquals("Programmation Java", cours.getLibelle());
        assertEquals(5, cours.getCoefficient());

    }

    @Test
    void testAjoutProfesseur() {

        Cours cours = Cours.creer("INF102", "Base de données", 4,30);

        Professeur prof = Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);

        cours.ajouterProf(prof);

        assertEquals(1, cours.getProfs().size());
        assertTrue(cours.getProfs().contains(prof));
    }

    @Test
    void testAjoutProfesseurSansDoublon() {

        Cours cours = Cours.creer("INF103", "Réseaux", 3,30);

        Professeur prof = Professeur.creer("Martin", "Paul", Sexe.MASCULIN);

        cours.ajouterProf(prof);
        cours.ajouterProf(prof);

        assertEquals(1, cours.getProfs().size());
    }

    @Test
    void testToString() {

        Cours cours = Cours.creer("INF104", "Mathématiques", 2,30);

        assertEquals("Mathématiques", cours.toString());
    }

    @Test
    void testCreationSansDoublon() {

        Cours c1 = Cours.creer("INF105", "Algorithmique", 5,30);

        Cours c2 = Cours.creer("INF105", "Algorithmique", 5,30);

        assertSame(c1, c2);
    }
}