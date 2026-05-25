/**
 * @auteur: Takam winy
 *
 */
package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe CertificatMedical
 */
class CertificatMedicalTest {

    /**
     * Vérifie que les données sont correctement stockées
     */
    @Test
    void testCreationCertificat() {

        CertificatMedical certif = new CertificatMedical("Grippe", "Dr Martin");

        assertEquals("Grippe", certif.getDescription());
        assertEquals("Dr Martin", certif.getNomMedecin());
    }

    /**
     * Vérifie le format du toString
     */
    @Test
    void testToString() {

        CertificatMedical certif = new CertificatMedical("Covid", "Dr House");

        String result = certif.toString();

        assertTrue(result.contains("Dr House"));
        assertTrue(result.contains("Covid"));
    }
}