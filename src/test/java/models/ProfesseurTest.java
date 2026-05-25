/**
 * @auteur: Takam winy
 *
 */

package models;

import enums.Sexe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Professeur.
 *
 * Objectifs :
 * – vérifier la création du professeur
 * — vérifier l'ajout de cours
 * — vérifier la gestion des doublons
 * — vérifier equals / toString
 */
class ProfesseurTest {

    @Test
    void testCreationProfesseur() {

        Professeur p = Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);

        assertEquals("Dupont", p.getNom());
        assertEquals("Jean", p.getPrenom());
        assertNotNull(p.getMatricule());
    }

    @Test
    void testAjoutCours() {

        Professeur p = Professeur.creer("Martin", "Paul", Sexe.MASCULIN);

        Local local = Local.creer("101","Salle 101");

        Cours c = Cours.creer("INF101", "Java", 5,30);

        p.ajouterCours(c);

        assertEquals(1, p.getCours().size());
        assertTrue(p.getCours().contains(c));
    }

    @Test
    void testAntiDoublonCours() {

        Professeur p = Professeur.creer("Diallo", "Amadou", Sexe.MASCULIN);

        Local local = Local.creer("102","Salle 102");

        Cours c = Cours.creer("INF102", "BD", 4,30);

        p.ajouterCours(c);
        p.ajouterCours(c); // doublon

        assertEquals(1, p.getCours().size());
    }

    @Test
    void testToString() {

        Professeur p = Professeur.creer("Kouam", "Eric", Sexe.MASCULIN);

        assertTrue(p.toString().contains("Kouam"));
    }
}