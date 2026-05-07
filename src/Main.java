import enums.Horaire;
import enums.Jour;
import enums.Session;
import enums.Sexe;
import models.*;

public class Main {

    public static void main(String[] args) {

        // =========================
        // 1. LOCAUX
        // =========================
        Local l1 = new Local("A1", "Salle A");
        Local l2 = new Local("B1", "Salle B");
        Local l3 = new Local("C1", "Salle C");

        // =========================
        // 2. PROFESSEURS
        // =========================
        Professeur p1 = new Professeur("Dupont", "Jean", Sexe.MASCULINE);
        Professeur p2 = new Professeur("Martin", "Claire", Sexe.FEMININE);
        Professeur p3 = new Professeur("Diallo", "Amadou", Sexe.MASCULINE);

        // =========================
        // 3. COURS (5 cours minimum)
        // =========================
        Cours java = new Cours("C1", "Java", 3, l1);
        Cours bd = new Cours("C2", "Base de données", 4, l2);
        Cours algo = new Cours("C3", "Algorithmique", 5, l1);
        Cours reseau = new Cours("C4", "Réseaux", 3, l3);
        Cours uml = new Cours("C5", "UML", 2, l2);

        // affectation profs
        java.ajouterProf(p1);
        bd.ajouterProf(p2);
        algo.ajouterProf(p3);
        reseau.ajouterProf(p1);
        uml.ajouterProf(p2);

        // =========================
        // 4. CURSUS
        // =========================
        Cursus cursus = new Cursus("DEV2026", "Développement Application");

        cursus.ajouterCours(java);
        cursus.ajouterCours(bd);
        cursus.ajouterCours(algo);
        cursus.ajouterCours(reseau);
        cursus.ajouterCours(uml);

        // =========================
        // 5. ÉTUDIANT
        // =========================
        Etudiant e1 = new Etudiant("Said", "Ali", Sexe.MASCULINE);
        e1.setCursus(cursus);

        // =========================
        // 6. SÉANCES (PLANNING CHARGÉ)
        // =========================
        Seance s1 = new Seance(Jour.LUNDI, Horaire.H08_10, java, p1, l1);
        Seance s2 = new Seance(Jour.LUNDI, Horaire.H10_12, bd, p2, l2);
        Seance s3 = new Seance(Jour.MARDI, Horaire.H08_10, algo, p3, l1);
        Seance s4 = new Seance(Jour.MERCREDI, Horaire.H13_15, reseau, p1, l3);
        Seance s5 = new Seance(Jour.JEUDI, Horaire.H15_17, uml, p2, l2);

        // séance reportée
        Seance s6 = new Seance(Jour.VENDREDI, Horaire.H08_10, java, p1, l1);
        // on simule report
        // (si tu as setter)
        // s6.setAnnulee(true);

        // ajout planning
        cursus.ajouterSeance(s1);
        cursus.ajouterSeance(s2);
        cursus.ajouterSeance(s3);
        cursus.ajouterSeance(s4);
        cursus.ajouterSeance(s5);
        cursus.ajouterSeance(s6);

        // =========================
        // 7. CERTIFICAT MÉDICAL
        // =========================
        CertificatMedical certif = new CertificatMedical("Grippe", "Dr Martin");

        // =========================
        // 8. ABSENCES
        // =========================
        e1.ajouterAbsence(new Absence(e1, s1, null));
        e1.ajouterAbsence(new Absence(e1, s3,  certif));

        // =========================
        // 9. NOTES (JUIN + AOÛT)
        // =========================
        e1.getBulletin().ajouterNote(new Note(14, java, Session.JUIN));
        e1.getBulletin().ajouterNote(new Note(16, java, Session.AOUT)); // rattrapage

        e1.getBulletin().ajouterNote(new Note(9, bd, Session.JUIN));
        e1.getBulletin().ajouterNote(new Note(9.5, bd, Session.AOUT));

        e1.getBulletin().ajouterNote(new Note(11, algo, Session.JUIN));
        e1.getBulletin().ajouterNote(new Note(13, reseau, Session.JUIN));
        e1.getBulletin().ajouterNote(new Note(10, uml, Session.JUIN));

        // =========================
        // 10. AFFICHAGES
        // =========================

        cursus.afficherPlanningSemaine();


        e1.getBulletin().afficherBulletin();

        PlanningPDF.generer(cursus, "planning.pdf");

        // =========================
        // 10. GENERER PDF
        // =========================

        BulletinPDF.generer(
                e1.getBulletin(),
                e1,
                "bulletin.pdf"
        );

        System.out.println("\n================ STATS ================");
        System.out.println("Absences non justifiées : " + e1.getAbsencesNonJustifiees(java));
        System.out.println("Taux présence global : " + e1.calculerTauxPresence(reseau) + "%");
    }
}