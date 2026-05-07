package models;

import enums.Sexe;

import java.util.List;
import java.util.ArrayList;

public class Professeur extends Personne {

    private List<Cours> cours;

    public Professeur(String nom, String prenom, Sexe sexe) {
        super(nom, prenom,sexe,GenerateurMatricule.genererMatriculeProf());
        this.cours = new ArrayList<>();
    }
    public List<Cours> getCours() {
        return cours;
    }
    public void ajouterCours(Cours c) {
        cours.add(c);
    }


}
