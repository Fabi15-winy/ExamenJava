/**
 * @auteur: Takam winy
 * @IA: GPT-5 pour la comprehension la bibliotheque et l'organisation de mon pdf.
 */
package pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import models.Bulletin;
import models.Etudiant;
import models.Note;
import models.Cours;
import enums.Session;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Génère un fichier PDF représentant le bulletin de notes d’un étudiant.
 *
 * Ce document contient :
 * – les informations de l’école (header avec logo)
 * – les informations de l’étudiant
 * – un tableau des notes avec statut et calcul du total
 * – la moyenne générale.
 *
 * Le PDF est généré avec la bibliothèque iText (lowagie).
 */
public class BulletinPDF {

    /**
     * Génère le PDF du bulletin de l’étudiant.
     *
     * @param bulletin contient les notes de l’étudiant
     * @param etudiant étudiant concerné
     * @param fichier chemin de sortie du PDF
     */
    public static void generer(Bulletin bulletin, Etudiant etudiant, String fichier) {

        try {


            // DOCUMENT

            Document doc = new Document(PageSize.A4, 30, 30, 30, 30);
            PdfWriter.getInstance(doc, new FileOutputStream(fichier));
            doc.open();


            // POLICES UTILISÉES

            Font schoolFont = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font infoFont = new Font(Font.HELVETICA, 8, Font.NORMAL);
            Font titleFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font tableFont = new Font(Font.HELVETICA, 12, Font.NORMAL);


            // HEADER (ÉCOLE)

            PdfPTable header = new PdfPTable(3);
            header.setWidthPercentage(100);
            header.setWidths(new float[]{2, 4, 3});

            // LOGO
            try {
                Image logo = Image.getInstance("src/images/logo.png");
                logo.scaleToFit(60, 60);

                PdfPCell logoCell = new PdfPCell(logo);
                logoCell.setBorder(Rectangle.NO_BORDER);
                header.addCell(logoCell);

            } catch (Exception e) {
                PdfPCell empty = new PdfPCell(new Phrase(""));
                empty.setBorder(Rectangle.NO_BORDER);
                header.addCell(empty);
            }

            // CENTRE (INFOS ÉCOLE)
            PdfPCell center = new PdfPCell();
            center.setBorder(Rectangle.NO_BORDER);
            center.setHorizontalAlignment(Element.ALIGN_CENTER);

            center.addElement(new Paragraph("ÉCOLE SUPÉRIEURE DES AFFAIRES DE NAMUR", schoolFont));
            center.addElement(new Paragraph("Rue Du college 8 - 5000 Namur", infoFont));
            center.addElement(new Paragraph("Année académique : " + bulletin.getAnneeAcademique(), infoFont));

            header.addCell(center);

            // DROITE (CONTACT)
            PdfPCell right = new PdfPCell();
            right.setBorder(Rectangle.NO_BORDER);
            right.setHorizontalAlignment(Element.ALIGN_RIGHT);

            right.addElement(new Paragraph("www.esa-namur.be", infoFont));
            right.addElement(new Paragraph("Email: secretariat@esa-namur.be", infoFont));
            right.addElement(new Paragraph("Tel: +32 81 00 00 00", infoFont));

            header.addCell(right);

            doc.add(header);

            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));


            // TITRE DU DOCUMENT

            Paragraph title = new Paragraph("BULLETIN DE NOTES", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            doc.add(title);

            doc.add(new Paragraph("\n"));
            doc.add(new Paragraph("\n"));


            // INFORMATIONS ÉTUDIANT

            doc.add(new Paragraph("Matricule : " + etudiant.getMatricule()));
            doc.add(new Paragraph("Nom : " + etudiant.getNom()));
            doc.add(new Paragraph("Prénom : " + etudiant.getPrenom()));
            doc.add(new Paragraph("Cursus : " + etudiant.getCursus().getNomCursus()));
            doc.add(new Paragraph("\n"));


            // TABLEAU DES NOTES

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{5, 2, 2, 3, 3});

            addHeaderCell(table, "Cours");
            addHeaderCell(table, "Note");
            addHeaderCell(table, "Coeff");
            addHeaderCell(table, "Total");
            addHeaderCell(table, "Statut");

            // =========================
            // SÉLECTION DES MEILLEURES NOTES
            // =========================
            Map<Cours, Note> meilleures = new HashMap<>();

            for (Note n : bulletin.getNotes()) {

                Cours c = n.getCours();

                if (!meilleures.containsKey(c)) {
                    meilleures.put(c, n);
                } else {
                    if (n.getSession() == Session.AOUT) {
                        meilleures.put(c, n);
                    }
                }
            }


            // REMPLISSAGE DU TABLEAU

            boolean bleu = false;

            for (Note n : meilleures.values()) {

                Color bg = bleu ? new Color(220, 235, 255) : Color.WHITE;
                bleu = !bleu;

                Cours cours = n.getCours();

                double note = n.getValeur();
                int coeff = cours.getCoefficient();
                double total = note * coeff;

                String statut;
                Font statutFont;

                if (note < 10) {
                    statut = "AJOURNÉ";
                    statutFont = new Font(Font.HELVETICA, 12, Font.BOLD, Color.RED);
                } else {
                    statut = "VALIDÉ";
                    statutFont = new Font(Font.HELVETICA, 12, Font.BOLD, new Color(0, 150, 0));
                }

                addRowCell(table, cours.getLibelle(), tableFont, bg);
                addRowCell(table, String.valueOf(note), tableFont, bg);
                addRowCell(table, String.valueOf(coeff), tableFont, bg);
                addRowCell(table, String.valueOf(total), tableFont, bg);
                addRowCell(table, statut, statutFont, bg);
            }

            doc.add(table);

            // =========================
            // MOYENNE GÉNÉRALE
            // =========================
            Paragraph moy = new Paragraph(
                    "MOYENNE : " + bulletin.calculerMoyenne(),
                    new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLUE)
            );
            moy.setAlignment(Element.ALIGN_RIGHT);
            moy.setSpacingBefore(15);

            doc.add(moy);

            doc.close();

            System.out.println("Bulletin PDF généré : " + fichier);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // CELLULE HEADER

    private static void addHeaderCell(PdfPTable table, String text) {

        Font font = new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE);

        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(new Color(0, 102, 204));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(6);

        table.addCell(cell);
    }


    // CELLULE LIGNE

    private static void addRowCell(PdfPTable table, String text, Font font, Color bg) {

        PdfPCell cell = new PdfPCell(new Phrase(text, font));

        cell.setBackgroundColor(bg);
        cell.setPadding(5);
        cell.setBorderColor(Color.GRAY);
        cell.setBorderWidth(0.5f);

        table.addCell(cell);
    }
}