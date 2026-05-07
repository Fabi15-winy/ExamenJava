package models;

public class GenerateurMatricule {

    private static int compteurEtudiant = 1;
    private static int compteurProf = 1;

    public static String genererMatriculeEtudiant() {
        return "ETU-26-" + String.format("%04d", compteurEtudiant++);
    }

    public static String genererMatriculeProf() {
        return "PROF-26-" + String.format("%04d", compteurProf++);
    }
}