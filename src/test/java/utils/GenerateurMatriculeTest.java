/**
 * @auteur: Takam winy
 *
 */

package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour GenerateurMatricule.
 *
 * Objectifs :
 * – vérifier le format
 * – vérifier l'incrémentation
 * – vérifier séparation étudiant / professeur
 */
class GenerateurMatriculeTest {

    @BeforeEach
    void setUp() {
        // Réinitialise les compteurs avant chaque test
        GenerateurMatricule.reset();
    }

    @Test
    void testMatriculeEtudiant_format() {
        String matricule = GenerateurMatricule.genererMatriculeEtudiant();

        assertTrue(matricule.startsWith("ETU-26-"));
        assertEquals("ETU-26-0001", matricule);
    }

    @Test
    void testMatriculeProf_format() {
        String matricule = GenerateurMatricule.genererMatriculeProf();

        assertTrue(matricule.startsWith("PROF-26-"));
        assertEquals("PROF-26-0001", matricule);
    }

    @Test
    void testIncrementEtudiant() {
        String m1 = GenerateurMatricule.genererMatriculeEtudiant();
        String m2 = GenerateurMatricule.genererMatriculeEtudiant();

        assertEquals("ETU-26-0001", m1);
        assertEquals("ETU-26-0002", m2);
    }

    @Test
    void testIncrementProf() {
        String m1 = GenerateurMatricule.genererMatriculeProf();
        String m2 = GenerateurMatricule.genererMatriculeProf();

        assertEquals("PROF-26-0001", m1);
        assertEquals("PROF-26-0002", m2);
    }

    @Test
    void testIndependanceEtudiantProf() {
        String etu = GenerateurMatricule.genererMatriculeEtudiant();
        String prof = GenerateurMatricule.genererMatriculeProf();

        assertTrue(etu.startsWith("ETU"));
        assertTrue(prof.startsWith("PROF"));
        assertNotEquals(etu, prof);
    }

    @Test
    void testFormatLongueur() {
        String matricule = GenerateurMatricule.genererMatriculeEtudiant();

        // ETU-26-0001 → longueur = 11
        assertEquals(11, matricule.length());
    }
}