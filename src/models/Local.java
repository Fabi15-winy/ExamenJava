/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package models;

import factory.RegistreFactory;

import java.util.Objects;

/**
 * Représente un local de cours (salle).
 */
public class Local {

    private String numLocal;
    private String nomLocal;

    // REGISTRE (anti-doublon)
    private static final RegistreFactory<Local> registre = new RegistreFactory<>();

    /**
     * Constructeur privé pour forcer l'utilisation du factory method.
     */
    private Local(String numLocal, String nomLocal) {
        this.numLocal = numLocal;
        this.nomLocal = nomLocal;
    }

    /**
     * Point de création unique.
     *
     * @param numLocal numéro unique du local
     * @param nomLocal nom du local
     * @return instance unique (anti-doublon)
     */
    public static Local creer(String numLocal, String nomLocal) {
        if (numLocal == null || nomLocal == null) {
            throw new IllegalArgumentException("Le Local ne doit pas être null");
        }
        return registre.validerEtEnregistrer(new Local(numLocal, nomLocal));
    }


    // GETTERS && SETTEURS


    public String getNumLocal() {
        return numLocal;
    }

    public String getNomLocal() {
        return nomLocal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Local)) return false;
        Local local = (Local) o;
        return Objects.equals(numLocal, local.numLocal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numLocal);
    }

    @Override
    public String toString() {
        return nomLocal;
    }
}