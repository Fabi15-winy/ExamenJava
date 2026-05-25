/**
 * @auteur: Takam winy
 *
 */

package models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalTest {

    @Test
    void testCreationLocal() {

        Local l = Local.creer("B101", "Salle Info");

        assertEquals("B101", l.getNumLocal());
        assertEquals("Salle Info", l.getNomLocal());
    }

    @Test
    void testToString() {

        Local l = Local.creer("B102", "Salle Réseau");

        assertEquals("Salle Réseau", l.toString());
    }

    @Test
    void testAntiDoublon() {

        Local l1 = Local.creer("B103", "Salle B");
        Local l2 = Local.creer("B103", "Salle B");

        // même objet mémoire grâce au Registry
        assertSame(l1, l2);
    }

}