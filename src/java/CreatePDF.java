
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mohak Chavan
 */
public class CreatePDF {

    public static void main(String args[]) {
        Document document = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("examplePDF.pdf"));
            document.open();
            document.add(new Paragraph("EXAMPLE PDF CREATED"));

            /* PdfPTable table=new PdfPTable(3);
            table.setWidthPercentage(75);
            table.setSpacingBefore(11f);
            table.setSpacingAfter(11f);
            
            float[] colWidth={2f,2f,2f};
            table.setWidths(colWidth);
            PdfPCell cell=new PdfPCell(new Paragraph("COL_1"));
            PdfPCell cell2=new PdfPCell(new Paragraph("COL_2"));
            PdfPCell cell3=new PdfPCell(new Paragraph("COL_3"));
            table.addCell(cell);table.addCell(cell2);table.addCell(cell3);
            document.add(table);
            
            List orderedList=new List(List.ORDERED);
            orderedList.add(new ListItem("listitem 1"));
            orderedList.add(new ListItem("listitem 2"));
            orderedList.add(new ListItem("listitem 3"));
            document.add(orderedList);
            
            List unorderedList=new List(List.UNORDERED);
            unorderedList.add(new ListItem("unlistitem 1"));
            unorderedList.add(new ListItem("unlistitem 2"));
            unorderedList.add(new ListItem("unlistitem 3"));
            document.add(unorderedList);*/
            Connection con;
            Statement st;
            ResultSet rs;

            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
            st = (Statement) con.createStatement();
            String sql = "SELECT `Que_ID`, `Question`, `Option_1`, `Option_2`, `Option_3`, `Option_4`, `Answer`, `Is_Question_Image`, `image` FROM `questions` WHERE `Que_ID` = 9";
            rs = st.executeQuery(sql);
            rs.next();

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(95);
            float[] colwidths = {1f, 0.5f, 5f, 0.5f, 5f};
            table.setWidths(colwidths);
            Font boldfont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
            Font normalfont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            PdfPCell cell2 = new PdfPCell(new Phrase("Gandhinagar Institute of Technology", boldfont));
            cell2.setBorder(0);
            cell2.setHorizontalAlignment(1);
            cell2.setPadding(5);
            cell2.setPaddingBottom(0);
            cell2.setColspan(5);
            cell2.setVerticalAlignment(1);
            table.addCell(cell2);
            cell2 = new PdfPCell(new Phrase("Computer Engineering Department", boldfont));
            cell2.setBorder(0);
            cell2.setHorizontalAlignment(1);
            cell2.setPadding(5);
            cell2.setPaddingTop(0);
            cell2.setColspan(5);
            cell2.setVerticalAlignment(1);
            cell2.setMinimumHeight(30);
            table.addCell(cell2);

            PdfPCell cell = new PdfPCell(new Phrase(" Q-1 "));
            if (rs.getString(8).contentEquals("YES")) {
                cell.setRowspan(4);
            } else {
                cell.setRowspan(3);
            }
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(rs.getString(2), normalfont));
            cell.setColspan(4);
            
            if (rs.getString(8).contentEquals("YES")) {
                cell.setBorderWidthBottom(0);
                table.addCell(cell);
                //Keep here path to the image from file questiondisplay and not the next line
                Image image = Image.getInstance("2170710_9.jpg");
                image.scaleAbsolute(384, 192);
                cell = new PdfPCell(image);
                cell.setColspan(4);
                cell.setBorderWidthTop(0);
                cell.setPadding(5);

            }
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("(A)"));
            cell.setVerticalAlignment(1);
            cell.setPaddingBottom(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(rs.getString(3), normalfont));
            cell.setVerticalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("(B)"));
            cell.setVerticalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(rs.getString(4), normalfont));
            cell.setVerticalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("(C)"));
            cell.setVerticalAlignment(1);
            cell.setPaddingBottom(5);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(rs.getString(5), normalfont));
            cell.setVerticalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("(D)"));
            cell.setVerticalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(rs.getString(6), normalfont));
            cell.setVerticalAlignment(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(" 2,2 "));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("2,3"));
            cell.setColspan(2);
            table.addCell(cell);

            document.add(table);

            document.close();
            writer.close();

        } catch (SQLException | ClassNotFoundException | FileNotFoundException | DocumentException ex) {
//                Logger.getLogger(CreatePDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
//            Logger.getLogger(CreatePDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
