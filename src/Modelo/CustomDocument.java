/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author brandonluismenesessolorzano
 */
public class CustomDocument extends Document {
    public CustomDocument() {
        super();
    }

    public void generatePDF(String contenido, String nombreArchivo) {
        try {
            PdfWriter.getInstance(this, new FileOutputStream("../Java-POO-UTP/Boletas/"+nombreArchivo+".pdf"));
            open();

            Paragraph paragraph = new Paragraph(contenido, new Font(Font.HELVETICA, 12));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            add(paragraph);

            close();

            JOptionPane.showMessageDialog(null, "El PDF se ha generado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF");
        }
    }
}