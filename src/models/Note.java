package models;

import enums.Session;

public class Note {

    private double valeur;
    private Cours cours;
    private Session session;

    public Note(double valeur,Cours cours, Session session) {
        this.valeur = valeur;
        this.cours = cours;
        this.session = session;
    }

    public double getValeur() {
        return valeur;
    }

    public Cours getCours() {
        return cours;
    }

    public Session getSession() {
        return session;
    }
}