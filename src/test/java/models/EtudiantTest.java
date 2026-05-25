/**
 * @auteur : Takam Winy
 */

package models;

import enums.Horaire;
import enums.Jour;
import enums.Sexe;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EtudiantTest {

    @Test
    void testCreationEtudiant() {

        Etudiant e = Etudiant.creer("Takam", "Fabiola", Sexe.FEMININ);

        assertEquals("Takam", e.getNom());
        assertEquals("Fabiola", e.getPrenom());

        assertNotNull(e.getMatricule());
        assertNotNull(e.getBulletin());
    }

    @Test
    void testAffectationCursus() {

        Etudiant e = Etudiant.creer("Doe", "Jane", Sexe.FEMININ);

        Cursus cursus = Cursus.creer("DEV", "Développement");

        e.setCursus(cursus);

        assertEquals(cursus, e.getCursus());
    }

    @Test
    void testAbsencesNonJustifiees() {

        Etudiant e = Etudiant.creer("Martin", "Paul", Sexe.AUTRE);

        Cursus cursus = Cursus.creer("TEST1", "Test");

        e.setCursus(cursus);

        Local local = Local.creer("101", "Salle 101");

        Cours cours = Cours.creer("INF101", "Java", 5, 30);

        Professeur prof = Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);

        Seance seance = Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);

        cursus.ajouterSeance(seance);

        Absence absence = Absence.creer(e, seance, null, LocalDate.of(2026, 5, 18));

        e.ajouterAbsence(absence);

        assertEquals(1, e.getAbsencesNonJustifiees(cours));
    }

    @Test
    void testCalculTauxPresence() {

        Etudiant e = Etudiant.creer("Durand", "Luc", Sexe.MASCULIN);

        Cursus cursus = Cursus.creer("TEST2", "Test");

        e.setCursus(cursus);

        Local local = Local.creer("102", "Salle 102");

        Cours cours = Cours.creer("INF102", "Réseaux", 4, 30);

        Professeur prof = Professeur.creer("Martin", "Paul", Sexe.MASCULIN);

        Seance s1 = Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);

        cursus.ajouterSeance(s1);

        e.ajouterAbsence(Absence.creer(e, s1, null, LocalDate.of(2026, 5, 18)));

        double taux = e.calculerTauxPresence(cours);

        // 30 séances - 1 absence = 96.66 %
        assertEquals(96.66, taux, 0.1);
    }

    @Test
    void testPeutPresenterExamenTrue() {

        Etudiant e = Etudiant.creer("Ngono", "Alice", Sexe.FEMININ);

        Cursus cursus = Cursus.creer("TEST3", "Test");

        e.setCursus(cursus);

        Local local = Local.creer("103", "Salle 103");

        Cours cours = Cours.creer("INF103", "Base de données", 5, 30);

        Professeur prof = Professeur.creer("Diallo", "Amadou", Sexe.MASCULIN);

        LocalDate[] dates = {
                LocalDate.of(2026, 5, 18), // lundi
                LocalDate.of(2026, 5, 19), // mardi
                LocalDate.of(2026, 5, 20), // mercredi
                LocalDate.of(2026, 5, 21), // jeudi
                LocalDate.of(2026, 5, 22)  // vendredi
        };

        for (int i = 0; i < 5; i++) {

            Seance s = Seance.creer(Jour.values()[i], Horaire.H08_10, cours, prof, local);

            cursus.ajouterSeance(s);

            // Une seule absence
            if (i == 0) {

                e.ajouterAbsence(Absence.creer(e, s, null, dates[i]));
            }
        }

        assertTrue(e.peutPresenterExamen(cours));
    }

    @Test
    void testPeutPresenterExamenFalse() {

        Etudiant e = Etudiant.creer("Kouam", "Eric", Sexe.MASCULIN);

        Cursus cursus = Cursus.creer("TEST4", "Test");

        e.setCursus(cursus);

        Local local = Local.creer("104", "Salle 104");

        Cours cours = Cours.creer("INF104", "Mathématiques", 3, 5);

        Professeur prof = Professeur.creer("Ndiaye", "Moussa", Sexe.MASCULIN);

        LocalDate[] dates = {
                LocalDate.of(2026, 5, 18),
                LocalDate.of(2026, 5, 19),
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 21),
                LocalDate.of(2026, 5, 22)
        };

        for (int i = 0; i < 5; i++) {

            Seance s = Seance.creer(Jour.values()[i], Horaire.H10_12, cours, prof, local);

            cursus.ajouterSeance(s);

            // 3 absences sur 5 = 40 %
            if (i < 3) {

                e.ajouterAbsence(Absence.creer(e, s, null, dates[i]));
            }
        }

        assertFalse(e.peutPresenterExamen(cours));
    }

    @Test
    void testToString() {

        Etudiant e = Etudiant.creer("Takam", "Fabiola", Sexe.FEMININ);

        assertTrue(e.toString().contains("Takam"));
        assertTrue(e.toString().contains("Fabiola"));
    }
}