package model;

import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class criaDoc_DAO {
        
        static String url = "jdbc:mysql://localhost/salonplus";
        static String username = "root";
        static String password = "";
    
        public static void cria() throws SQLException, DocumentException{
        
        Document doc = null;
        OutputStream os = null;

        try {

            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            try {
                //cria a stream de saída
                os = new FileOutputStream("logs.pdf");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(criaDoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //associa a stream de saída ao
                PdfWriter.getInstance(doc, os);
            } catch (DocumentException ex) {
                Logger.getLogger(criaDoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            //abre o documento
            doc.open();

            //adiciona o texto ao PDF
            Paragraph par = new Paragraph("Relatório de reservas do Salon+:");
            
            PdfPTable table = new PdfPTable(5);
            table.addCell("Responsável");
            table.addCell("Sala");
            table.addCell("Hora Inicial");
            table.addCell("Data");
            table.addCell("Tempo");
            
            String SQL = "select * from reservas";
            
            Connection con = (Connection) DriverManager.getConnection(url, username, password);
            
            try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                
                PdfPCell cell1 = new PdfPCell();
                PdfPCell cell2 = new PdfPCell();
                PdfPCell cell3 = new PdfPCell();
                PdfPCell cell4 = new PdfPCell();
                PdfPCell cell5 = new PdfPCell();
                
                cell1.addElement(new Paragraph(rs.getString("reservador")));
                cell2.addElement(new Paragraph(rs.getString("sala")));
                cell3.addElement(new Paragraph(rs.getString("hora")));
                cell4.addElement(new Paragraph(rs.getString("data")));
                cell5.addElement(new Paragraph(rs.getString("tempo")));
                
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
            }
        }catch (Exception e){
        }
            
            try {
                doc.add(par);
                doc.add(table);
            } catch (DocumentException ex) {
                Logger.getLogger(criaDoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {

            if (doc != null) {

                //fechamento do documento
                doc.close();
            }

            if (os != null) {
                try {
                    //fechamento da stream de saída
                    os.close();
                } catch (IOException ex) {
                    Logger.getLogger(criaDoc_DAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}    

