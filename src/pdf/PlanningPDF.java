/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la comprehension la bibliotheque et l'organisation de mon pdf.
 */

package pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import enums.Jour;
import models.Cursus;
import models.Seance;
import java.io.FileOutputStream;

/**
 * Génère un planning hebdomadaire au format PDF mode paysage.
 *
 * Ce document contient :
 * – Un en-tête avec logo, nom de l’école et contact
 * – Un titre centré "PLANNING DE LA SEMAINE"
 * – Un tableau regroupant les séances par jour.
 *
 */
public class PlanningPDF {

    /**
     * Génère le PDF du planning du cursus.
     *
     * @param cursus objet contenant les cours et le planning des séances
     * @param fichier chemin du fichier PDF à générer
     */
    public static void generer(Cursus cursus, String fichier) {

        try {


            // DOCUMENT EN MODE PAYSAGE

            Document doc = new Document(PageSize.A4.rotate(), 30, 30, 30, 30);
            PdfWriter.getInstance(doc, new FileOutputStream(fichier));

            doc.open();


            // POLICES UTILISÉES

            Font schoolFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font infoFont = new Font(Font.HELVETICA, 10, Font.NORMAL);
            Font titleFont = new Font(Font.HELVETICA, 13, Font.BOLD);
            Font titreFont = new Font(Font.HELVETICA, 20, Font.BOLD);
            Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE);
            Font normalFont = new Font(Font.HELVETICA, 11, Font.NORMAL);
            Font reporteeFont = new Font(Font.HELVETICA, 11, Font.BOLD, Color.RED);


            // EN-TÊTE DU DOCUMENT

            PdfPTable header = new PdfPTable(3);
            header.setWidthPercentage(100);
            header.setWidths(new float[]{2, 4, 3});

            Image logo = Image.getInstance("src/images/logo.png");
            logo.scaleToFit(100, 100);

            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setRowspan(2);
            logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.addCell(logoCell);

            PdfPCell centerCell = new PdfPCell();
            centerCell.setBorder(Rectangle.NO_BORDER);
            centerCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            centerCell.addElement(new Paragraph("ÉCOLE SUPÉRIEURE DES AFFAIRES DE NAMUR", schoolFont));
            centerCell.addElement(new Paragraph("Rue Du college 8 - 5000 Namur", infoFont));
            centerCell.addElement(new Paragraph("Année académique : 2025 - 2026", infoFont));

            header.addCell(centerCell);

            PdfPCell rightCell = new PdfPCell();
            rightCell.setBorder(Rectangle.NO_BORDER);
            rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            rightCell.addElement(new Paragraph("www.esa-namur.be", infoFont));
            rightCell.addElement(new Paragraph("Email: secretariat@esa-namur.be", infoFont));
            rightCell.addElement(new Paragraph("Tel: +32 81 00 00 00", infoFont));

            header.addCell(rightCell);

            doc.add(header);


            // TITRE DU PLANNING

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            Paragraph title = new Paragraph("PLANNING DE LA SEMAINE ", titreFont );
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            doc.add(title);

            Paragraph cursusNom = new Paragraph(cursus.getNomCursus() , infoFont);
            cursusNom.setAlignment(Element.ALIGN_CENTER);
            cursusNom.setSpacingAfter(20);
            doc.add(cursusNom);


            // TABLEAU PRINCIPAL

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 3, 4, 4, 3});

            ajouterCelluleEntete(table, "Jour", headerFont);
            ajouterCelluleEntete(table, "Horaire", headerFont);
            ajouterCelluleEntete(table, "Cours", headerFont);
            ajouterCelluleEntete(table, "Prof / Statut", headerFont);
            ajouterCelluleEntete(table, "Local", headerFont);


            // AFFICHAGE DES SÉANCES

            for (Jour jour : Jour.values()) {

                int nbSeancesJour = 0;

                for (Seance s : cursus.getPlanning()) {
                    if (s.getJour() == jour) {
                        nbSeancesJour++;
                    }
                }

                if (nbSeancesJour == 0) continue;

                boolean first = true;

                for (Seance s : cursus.getPlanning()) {

                    if (s.getJour() != jour) continue;

                    boolean reportee = s.estReportee();
                    Font font = reportee ? reporteeFont : normalFont;

                    if (first) {

                        PdfPCell jourCell = new PdfPCell(
                                new Phrase(jour.toString(), normalFont)
                        );

                        jourCell.setRowspan(nbSeancesJour);
                        jourCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        jourCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        jourCell.setPadding(6);

                        table.addCell(jourCell);
                        first = false;
                    }

                    ajouterCellule(table, s.getHoraire().getLibelle(), font);
                    ajouterCellule(table, s.getCours().getLibelle(), font);

                    if (reportee) {
                        ajouterCellule(table,
                                s.getProf().toString() + " (REPORTÉE)",
                                reporteeFont);
                    } else {
                        ajouterCellule(table, s.getProf().toString(), normalFont);
                    }

                    ajouterCellule(table, s.getLocal().getNomLocal(), font);
                }
            }

            doc.add(table);
            doc.close();

            System.out.println("Planning PDF généré : " + fichier + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // CELLULE EN-TÊTE

    private static void ajouterCelluleEntete(PdfPTable table, String texte, Font font) {

        PdfPCell cell = new PdfPCell(new Phrase(texte, font));
        cell.setBackgroundColor(new Color(0, 102, 204));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(8);

        table.addCell(cell);
    }


    // CELLULE STANDARD

    private static void ajouterCellule(PdfPTable table, String texte, Font font) {

        PdfPCell cell = new PdfPCell(new Phrase(texte, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(6);

        table.addCell(cell);
    }
}