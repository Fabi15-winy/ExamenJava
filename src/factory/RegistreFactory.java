/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */

package factory;
import java.util.ArrayList;
import java.util.List;

/**
 * Registre générique permettant d'éviter la création de doublons.
 * Si un objet équivalent existe déjà,
 * il est retourné au lieu d'enregistrer un nouvel objet.
 *
 * @param <T> type d'objet géré par le registre
 */
public class RegistreFactory<T> {

    /**
     * Liste des objets déjà enregistrés.
     */
    private final List<T> listeExistante = new ArrayList<>();

    /**
     * Vérifie si l'objet existe déjà.
     * - Si oui : retourne l'objet existant.
     * - Sinon : enregistre le nouvel objet et le retourne.
     *
     * @param nouvelObjet objet à enregistrer
     * @return objet existant ou nouvel objet enregistré
     */
    public T validerEtEnregistrer(T nouvelObjet) {

        if (listeExistante.contains(nouvelObjet)) {
            int index = listeExistante.indexOf(nouvelObjet);
            return listeExistante.get(index);
        }

        listeExistante.add(nouvelObjet);
        return nouvelObjet;
    }

    /**
     * Retourne le nombre d'objets enregistrés.
     *
     * @return taille du registre
     */
    public int size() {
        return listeExistante.size();
    }

}
