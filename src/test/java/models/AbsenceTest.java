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

class AbsenceTest {

    @Test
    void testCreerAbsence() {

        // Arrange
        Local local =  Local.creer("A1", "Salle A1");
        Professeur prof =  Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);
        Cours cours = Cours.creer("C1", "Java", 3,30);
        Seance seance =  Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);
        Etudiant etudiant =  Etudiant.creer("Ali", "Said", Sexe.MASCULIN);

        // Act
        Absence absence = Absence.creer(etudiant, seance, null,LocalDate.of(2026, 5, 18));

        // Assert
        assertNotNull(absence);
        assertEquals(etudiant, absence.getPersonne());
        assertEquals(seance, absence.getSeance());
        assertFalse(absence.estJustifiee());
    }

    @Test
    void testAbsenceJustifiee() {

        Local local =  Local.creer("A1", "Salle A1");
        Professeur prof =  Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);
        Cours cours =  Cours.creer("C1", "Java", 3,30);
        Seance seance =  Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);
        Etudiant etudiant =  Etudiant.creer("Ali", "Said", Sexe.MASCULIN);

        CertificatMedical certif = new CertificatMedical("Grippe", "Dr Martin");

        Absence absence = Absence.creer(etudiant, seance, certif,LocalDate.of(2026, 5, 18));

        assertTrue(absence.estJustifiee());
        assertEquals(certif, absence.getCertificat());
    }

    @Test
    void testSingletonRegistryAbsence() {

        Local local =  Local.creer("A1", "Salle A1");
        Professeur prof =  Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);
        Cours cours =  Cours.creer("C1", "Java", 3,30);
        Seance seance =  Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);
        Etudiant etudiant =  Etudiant.creer("Ali", "Said", Sexe.MASCULIN);

        Absence a1 = Absence.creer(etudiant, seance, null,LocalDate.of(2026, 5, 18));
        Absence a2 = Absence.creer(etudiant, seance, null,LocalDate.of(2026, 5, 18));

        assertSame(a1, a2);
    }

    @Test
    void testEqualsAbsence() {

        Local local =  Local.creer("A1", "Salle A");
        Professeur prof =  Professeur.creer("Dupont", "Jean", Sexe.MASCULIN);
        Cours cours =  Cours.creer("C1", "Java", 3,30);
        Seance seance =  Seance.creer(Jour.LUNDI, Horaire.H08_10, cours, prof, local);
        Etudiant etudiant =  Etudiant.creer("Ali", "Said", Sexe.MASCULIN);

        Absence a1 = Absence.creer(etudiant, seance, null, LocalDate.of(2026, 5, 18));
        Absence a2 = Absence.creer(etudiant, seance, null,LocalDate.of(2026, 5, 18));

        assertEquals(a1, a2);
    }
}