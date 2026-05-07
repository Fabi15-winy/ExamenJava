package models;

import java.util.*;

public class Cours {

    private String codeCours;
    private String libelle;
    private int coefficient;
    private Local local;
    private List<Professeur> profs;
    private List<Seance> seances;


    public Cours(String codeCours, String libelle, int coefficient, Local local) {
        this.codeCours = codeCours;
        this.libelle = libelle;
        this.coefficient = coefficient;
        this.local = local;
        this.profs = new ArrayList<>();
        this.seances = new ArrayList<>();
    }

    public void ajouterProf(Professeur p) {
        profs.add(p);
    }

    public int getCoefficient() {
        return coefficient;
    }

    public Local getLocal() {
        return local;
    }

    public String getLibelle() {
        return libelle;
    }
    public List<Seance>  getSeances() {
        return seances;
    }

}

