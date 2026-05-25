/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la documentation.
 */
import enums.*;

import models.*;
import pdf.BulletinPDF;
import pdf.PlanningPDF;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {


        // LOCAUX

        Local l1 = Local.creer("A1", "Salle A");
        Local l2 = Local.creer("B1", "Salle B");
        Local l3 = Local.creer("C1", "Salle C");
        Local l4 = Local.creer("D1", "Salle D");


        // PROFESSEURS

        Professeur p1 = Professeur.creer("CLAISSE", "Bernard", Sexe.MASCULIN);
        Professeur p2 = Professeur.creer("ERNAELSTEN", "Gérard", Sexe.MASCULIN);
        Professeur p3 = Professeur.creer("MALHERBE", "Geoffroy", Sexe.MASCULIN);
        Professeur p4 = Professeur.creer("BOURDEAUX", "Catherine", Sexe.FEMININ);
        Professeur p5 = Professeur.creer("GUILLAUME", "Arnaud", Sexe.MASCULIN);
        Professeur p6 = Professeur.creer("LEONARDI", "Lorenzo", Sexe.MASCULIN);
        Professeur p7 = Professeur.creer("FALLA", "Michele", Sexe.FEMININ);


        // COURS

        Cours IBDO = Cours.creer("867", "Initiation aux bases de données", 5,60);
        Cours GEBD = Cours.creer("1290", "Gestion et exploitation de base de données", 5,50);
        Cours POO = Cours.creer("1286", "Programmation orientée objet", 9,100);
        Cours WEBCS = Cours.creer("1291", "Projet de développement Web", 10,100);
        Cours ICOM = Cours.creer("1068", "Information et communication pro", 3,30);
        Cours TGPR = Cours.creer("1294", "Techniques de gestion de projets", 3,30);
        Cours ANAL = Cours.creer("1287", "Analyse informatique", 4,30);
        Cours ERP = Cours.creer("1289", "Labo logiciels de gestion intégrés", 6,60);
        Cours NCPT = Cours.creer("1288", "Notions de comptabilité", 3,30);


        // CURSUS

        Cursus cursus = Cursus.creer("Bac2_DEV", "Bachelier 2 Développement Application");

        cursus.ajouterCours(IBDO);
        cursus.ajouterCours(GEBD);
        cursus.ajouterCours(POO);
        cursus.ajouterCours(WEBCS);
        cursus.ajouterCours(ICOM);
        cursus.ajouterCours(TGPR);
        cursus.ajouterCours(ANAL);
        cursus.ajouterCours(ERP);
        cursus.ajouterCours(NCPT);


        // ETUDIANT

        Etudiant e1 = Etudiant.creer("TAKAM", "Winy", Sexe.FEMININ);
        e1.setCursus(cursus);


        // SEANCES

        Seance s1 = Seance.creer(Jour.LUNDI, Horaire.H08_10, IBDO, p1, l1);
        Seance s2 = Seance.creer(Jour.LUNDI, Horaire.H10_12, ERP, p6, l2);
        Seance s3 = Seance.creer(Jour.MARDI, Horaire.H08_10, GEBD, p3, l1);
        Seance s4 = Seance.creer(Jour.MERCREDI, Horaire.H13_15, POO, p3, l3);
        Seance s5 = Seance.creer(Jour.JEUDI, Horaire.H15_17, WEBCS, p2, l2);
        Seance s6 = Seance.creer(Jour.VENDREDI, Horaire.H08_10, TGPR, p5, l1);
        Seance s7 = Seance.creer(Jour.VENDREDI, Horaire.H15_17, ICOM, p4, l4);
        Seance s8 = Seance.creer(Jour.MERCREDI, Horaire.H08_10, ANAL, p5, l1);
        Seance s9 = Seance.creer(Jour.VENDREDI, Horaire.H18_20, NCPT, p7, l1);


        // ABSENCE PROF

        s4.appliquerAbsenceProf(Absence.creer(p3, s4, null,LocalDate.of(2026, 5, 20)));


        // PLANNING

        cursus.ajouterSeance(s1);
        cursus.ajouterSeance(s2);
        cursus.ajouterSeance(s3);
        cursus.ajouterSeance(s4);
        cursus.ajouterSeance(s5);
        cursus.ajouterSeance(s6);
        cursus.ajouterSeance(s7);
        cursus.ajouterSeance(s8);
        cursus.ajouterSeance(s9);


        // CERTIFICAT

        CertificatMedical certif = new CertificatMedical("Grippe", "Dr Martin");


        // ABSENCES ETUDIANT

        e1.ajouterAbsence(Absence.creer(e1, s1, null, LocalDate.of(2026, 5, 18)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 3, 3)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 3, 10)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 3, 17)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 3, 24)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 3, 31)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 4, 7)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 4, 14)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 4, 21)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 5, 5)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 5, 19)));
        e1.ajouterAbsence(Absence.creer(e1, s3, null,LocalDate.of(2026, 5, 12)));


        // NOTES
        try {
            e1.getBulletin().ajouterNote(Note.creer(16, NCPT, Session.AOUT));
            e1.getBulletin().ajouterNote(Note.creer(9, ICOM, Session.JUIN));
            e1.getBulletin().ajouterNote(Note.creer(9.5, ICOM, Session.AOUT));
            e1.getBulletin().ajouterNote(Note.creer(11, ERP, Session.JUIN));
            e1.getBulletin().ajouterNote(Note.creer(13, WEBCS, Session.JUIN));
            e1.getBulletin().ajouterNote(Note.creer(10, POO, Session.JUIN));
            e1.getBulletin().ajouterNote(Note.creer(18, ANAL, Session.JUIN));
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        try {
            e1.getBulletin().ajouterNote(Note.creer(14, GEBD, Session.JUIN));
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }


        // PDF

        PlanningPDF.generer(cursus, "src/output/Planning_" + System.currentTimeMillis() + ".pdf");
        BulletinPDF.generer(e1.getBulletin(), e1, "src/output/Bulletin_" + System.currentTimeMillis() + ".pdf");


        // RAPPORT DES ABSENCES

        System.out.println("\n======= RAPPORT DES ABSENCES DE : "+e1.getPrenom()+" AU COURS DE: "+GEBD.getLibelle()+" ======= \n");
        System.out.println("Absences non justifiées : " + e1.getAbsencesNonJustifiees(GEBD));
        System.out.println("Taux présence : " + e1.calculerTauxPresence(GEBD) + "%");
    }
}