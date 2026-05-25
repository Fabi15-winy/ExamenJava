/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import enums.Session;
import factory.RegistreFactory;

import java.util.Objects;

/**
 * Représente une note attribuée à un étudiant pour un cours et par session.
 *
 * Une note contient :
 * – une valeur (0 à 20)
 * – un cours associé
 * – une session (JUIN ou AOUT).
 *
 */
public class Note {

    private final double valeur;
    private final Cours cours;
    private final Session session;
    // REGISTRE (anti-doublon)
    private static final RegistreFactory<Note> registre = new RegistreFactory<>();

    /**
     * Constructeur de Note
     *
     * @param valeur note (0 - 20)
     * @param cours cours concerné
     * @param session session d'examen (JUIN / AOUT)
     */
    private Note(double valeur, Cours cours, Session session) {
        this.valeur = valeur;
        this.cours = cours;
        this.session = session;
    }
    public static Note creer(double valeur, Cours cours, Session session) {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("La note doit être comprise entre 0 et 20");
        }
        return registre.validerEtEnregistrer(new Note(valeur, cours, session)
        );
    }


    // GETTERS && SETTERS


    public double getValeur() {
        return valeur;
    }

    public Cours getCours() {
        return cours;
    }

    public Session getSession() {
        return session;
    }


    // EQUALS / HASHCODE


    /**
     * Deux notes sont identiques si elles concernent :
     * – le même cours
     * – la même session
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        return Double.compare(note.valeur, valeur) == 0
                && Objects.equals(cours, note.cours)
                && session == note.session;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur, cours, session);
    }

    // AFFICHAGE

    @Override
    public String toString() {
        return cours.getLibelle() + " | " + valeur + " | " + session;
    }
}