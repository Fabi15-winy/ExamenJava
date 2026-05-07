package models;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileOutputStream;

public class BulletinPDF {

    public static void generer(Bulletin bulletin, Etudiant etudiant, String fichier) {

        try {

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(fichier));

            doc.open();

            doc.add(new Paragraph("BULLETIN DE NOTES"));
            doc.add(new Paragraph("models.Etudiant : " + etudiant.getNom()));
            doc.add(new Paragraph(" "));

            for (Note n : bulletin.getNotes()) {

                String ligne = n.getCours().getLibelle()
                        + " | models.Note : " + n.getValeur()
                        + " | enums.Session : " + n.getSession();

                if (n.getValeur() < 10) {
                    ligne += " | AJOURNÉ";
                } else {
                    ligne += " | VALIDÉ";
                }

                doc.add(new Paragraph(ligne));
            }

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("MOYENNE : " + bulletin.calculerMoyenne()));

            doc.close();

            System.out.println("✅ PDF bulletin généré");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
