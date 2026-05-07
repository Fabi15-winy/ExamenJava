package models;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import enums.Jour;

import java.io.FileOutputStream;

public class PlanningPDF {

    public static void generer(Cursus cursus, String fichier) {

        try {

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(fichier));

            doc.open();

            doc.add(new Paragraph("PLANNING DE LA SEMAINE"));
            doc.add(new Paragraph(" "));

            for (Jour j : Jour.values()) {

                doc.add(new Paragraph("=== " + j + " ==="));

                for (Seance s : cursus.getPlanning()) {

                    if (s.getJour() == j) {

                        String ligne = s.getHoraire().getLibelle()
                                + " | " + s.getCours().getLibelle()
                                + " | " + s.getProf().getNom()
                                + " | " + s.getLocal().getNomLocal();

                        if (s.estAnnulee()) {
                            ligne += " | REPORTÉE";
                        }

                        doc.add(new Paragraph(ligne));
                    }
                }

                doc.add(new Paragraph(" "));
            }

            doc.close();

            System.out.println("✅ PDF planning généré");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
