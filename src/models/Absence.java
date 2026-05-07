package models;

public class Absence {

    private Personne personne;
    private Seance seance;
    private CertificatMedical certificat;

    public Absence(Personne personne, Seance seance, CertificatMedical certificat) {
        this.personne = personne;
        this.seance = seance;
        this.certificat = certificat;
    }
    public Personne getPersonne() {
        return personne;
    }

    public Seance getSeance() {
        return seance;
    }
    public boolean estJustifiee() {
        return certificat != null;
    }


}