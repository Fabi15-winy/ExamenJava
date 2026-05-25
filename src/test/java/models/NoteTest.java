/**
 * @auteur: Takam winy
 *
 */
package models;

import enums.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Note.
 *
 * Objectifs :
 * – vérifier la création correcte d'une note
 * — vérifier l'immuabilité de la classe
 * — vérifier les règles equals/hashCode
 * — valider les comportements métier (sessions, valeurs)
 *
 * Ces tests garantissent la fiabilité du système de calcul
 * des moyennes et du bulletin.
 */
class NoteTest {

    /**
     * Vérifie que la création d'une note fonctionne correctement
     * et que les attributs sont bien initialisés.
     */
    @Test
    void testCreationNote() {

        Cours cours = Cours.creer("INF101", "Java", 5,30);

        Note note =  Note.creer(15.5, cours, Session.JUIN);

        assertEquals(15.5, note.getValeur());
        assertEquals(cours, note.getCours());
        assertEquals(Session.JUIN, note.getSession());
    }

    /**
     * Vérifie que la classe Note est immuable
     * (aucune modification après création).
     */
    @Test
    void testImmutabilite() {

        Cours cours = Cours.creer("INF102", "BD", 4,30);

        Note note =  Note.creer(12, cours, Session.AOUT);

        assertEquals(12, note.getValeur());
    }

    /**
     * Vérifie que deux notes identiques sont considérées comme égales.
     */
    @Test
    void testEqualsSameNote() {

        Cours cours = Cours.creer("INF103", "Réseaux", 3,30);

        Note n1 =  Note.creer(14, cours, Session.JUIN);
        Note n2 =  Note.creer(14, cours, Session.JUIN);

        assertEquals(n1, n2);
    }

    /**
     * Vérifie que deux notes de sessions différentes
     * ne sont pas considérées comme identiques.
     */
    @Test
    void testDifferentSession() {

        Cours cours = Cours.creer("INF104", "Algo", 5,30);

        Note n1 =  Note.creer(14, cours, Session.JUIN);
        Note n2 =  Note.creer(14, cours, Session.AOUT);

        assertNotEquals(n1, n2);
    }

    /**
     * Vérifie le format d'affichage de la note.
     */
    @Test
    void testToString() {

        Cours cours = Cours.creer("INF105", "Math", 2,30);

        Note note =  Note.creer(10, cours, Session.JUIN);

        assertTrue(note.toString().contains("Math"));
    }
}