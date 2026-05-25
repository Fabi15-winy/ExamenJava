/**
 * @auteur: Takam winy
 *
 */

package models;

import enums.Horaire;
import enums.Jour;
import enums.Sexe;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe Seance.
 *
 * Objectifs :
 * – vérifier création correcte
 * — vérifier état reporté
 * — vérifier logique absence professeur
 * — vérifier equals / toString
 */
class SeanceTest {

    @Test
    void testCreationSeance() {

        Local local = Local.creer("101","Salle 10");

        Cours cours = Cours.creer("INF101", "Java", 5,30);

        Professeur prof = Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);

        Seance s =  Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);

        assertEquals(Jour.LUNDI, s.getJour());
        assertEquals(Horaire.H08_10, s.getHoraire());
        assertFalse(s.estReportee());
    }

    @Test
    void testAppliquerAbsenceProf() {

        Local local = Local.creer("102","Salle 102");

        Cours cours = Cours.creer("INF102", "BD", 4,30);

        Professeur prof = Professeur.creer("Martin", "Paul", Sexe.MASCULIN);

        Seance s =  Seance.creer(Jour.MARDI, Horaire.H10_12, cours, prof, local);

        Absence absence =  Absence.creer(prof, s, null, LocalDate.of(2026, 5, 19));

        s.appliquerAbsenceProf(absence);

        assertTrue(s.estReportee());
    }

    @Test
    void testAbsenceAutreProf() {

        Local local = Local.creer("103","Salle 101");

        Cours cours = Cours.creer("INF103", "Réseaux", 3,30);

        Professeur prof1 = Professeur.creer("A", "A", Sexe.MASCULIN);

        Professeur prof2 = Professeur.creer("B", "B", Sexe.MASCULIN);

        Seance s =  Seance.creer(Jour.MERCREDI, Horaire.H15_17, cours, prof1, local);

        Absence absence =  Absence.creer(prof2, s, null,LocalDate.of(2026, 5, 20));

        s.appliquerAbsenceProf(absence);

        assertFalse(s.estReportee());
    }

    @Test
    void testToString() {

        Local local = Local.creer("104","Salle 104");

        Cours cours = Cours.creer("INF104", "Algo", 5,30);

        Professeur prof = Professeur.creer("Test", "Prof", Sexe.MASCULIN);

        Seance s =  Seance.creer(Jour.JEUDI, Horaire.H15_17, cours, prof, local);

        assertTrue(s.toString().contains("Algo"));
    }
}