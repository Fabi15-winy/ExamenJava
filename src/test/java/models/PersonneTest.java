/**
 * @auteur: Takam winy
 *
 */

package models;

import enums.Sexe;
import enums.Jour;
import enums.Horaire;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires de la classe abstraite Personne.
 * On utilise une classe concrète interne pour les tests.
 */
class PersonneTest {


    // CLASSE MOCK POUR TESTER

    static class FakePersonne extends Personne {
        public FakePersonne(String nom, String prenom, Sexe sexe, String matricule) {
            super(nom, prenom, sexe, matricule);
        }
    }

    private FakePersonne personne;
    private Seance seance;

    @BeforeEach
    void setUp() {

        personne = new FakePersonne("Dupont", "Jean", Sexe.MASCULIN, "P001");

        Local local = Local.creer("A", "Salle A");

        Cours cours =  Cours.creer("POO", "Java", 3,30);

        Professeur prof =  Professeur.creer("Martin", "Claire", Sexe.FEMININ);

        seance =  Seance.creer(Jour.VENDREDI, Horaire.H08_10, cours, prof, local);
    }


    // TEST CONSTRUCTEUR

    @Test
    void testCreationPersonne() {

        assertEquals("Dupont", personne.getNom());
        assertEquals("Jean", personne.getPrenom());
        assertEquals("P001", personne.getMatricule());
        assertEquals(Sexe.MASCULIN, personne.getSexe());
    }


    // TEST ABSENCES

    @Test
    void testAjoutAbsence() {

        Absence absence =  Absence.creer(personne, seance, null, LocalDate.of(2026, 5, 15));

        personne.ajouterAbsence(absence);

        assertEquals(1, personne.getTotalAbsences());
        assertEquals(1, personne.getAbsencesNonJustifiees());
    }

    @Test
    void testAbsenceJustifiee() {

        CertificatMedical certif = new CertificatMedical("Grippe", "Dr Martin");

        Absence absence =  Absence.creer(personne, seance, certif,LocalDate.of(2026, 5, 15));

        personne.ajouterAbsence(absence);

        assertEquals(0, personne.getAbsencesNonJustifiees());
        assertTrue(personne.aCertificatPour(seance));
    }

    @Test
    void testDoublonAbsenceNonAjoute() {

        Absence a1 =  Absence.creer(personne, seance, null,LocalDate.of(2026, 5, 15));
        Absence a2 =  Absence.creer(personne, seance, null,LocalDate.of(2026, 5, 15));

        personne.ajouterAbsence(a1);
        personne.ajouterAbsence(a2);

        assertEquals(1, personne.getTotalAbsences());
    }


    // TEST IMMUTABILITÉ LISTE

    @Test
    void testListeAbsencesImmuable() {

        Absence absence =  Absence.creer(personne, seance, null,LocalDate.of(2026, 5, 15));

        personne.ajouterAbsence(absence);

        assertThrows(
                UnsupportedOperationException.class,
                () -> personne.getAbsences().add(absence)
        );
    }
}