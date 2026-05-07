package models;

import enums.Sexe;

import java.util.*;

public abstract class Personne {

    protected String nom;
    protected String prenom;
    protected Sexe sexe;
    protected String matricule;
    protected List<Absence> absences;


    public Personne(String nom, String prenom, Sexe sexe, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.matricule = matricule;
        this.absences = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public String  getMatricule() {
        return matricule;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void ajouterAbsence(Absence a) {
        absences.add(a);
    }
    // Nombre total d’absences
    public int getTotalAbsences() {
        return absences.size();
    }

}
