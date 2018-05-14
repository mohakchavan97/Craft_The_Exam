/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohak Chavan
 */
public class Create_PDF extends HttpServlet {

    Document document = null;
    FileOutputStream fos = null;
    PdfWriter writer = null;
    ByteArrayOutputStream baos = null;
    String sql, subj, data;
    ResultSet rs;
    Statement st;
    ServletConfig config;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        if (!(session.isNew())) {
            //if (true) {
            sql = null;
            subj = session.getAttribute("subj").toString();
            data = (String) session.getAttribute("data");
            //String sql = null, subj = null, data = (String) session.getAttribute("data");
            config = getServletConfig();
            PrintWriter out = null;

            try {
                out = response.getWriter();
                /* TODO output your page here. You may use following sample code. */
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Create_PDF</title>");
//                out.println("</head>");
//                out.println("<body>");
                //out.println("<h1>Servlet Create_PDF at " + request.getContextPath() + "</h1>");
                if (!(subj.isEmpty())) {
                    //if (true) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
                        st = (Statement) con.createStatement();

                        //new File(config.getServletContext().getRealPath("/") + "/example/" + session.getId()).mkdir();

                        String filePath = config.getServletContext().getRealPath("/") + "example\\" 
                                //+ session.getId() + "\\"
                                + session.getId().trim()
                                + ".pdf";
                        File file = new File(filePath);
//                        if (file.exists()) {
//                            file.delete();
//                        }
//                        file.createNewFile();
                        fos = new FileOutputStream(file, false);
                        document = new Document();
                        writer = PdfWriter.getInstance(document, fos);
                        //PdfWriter.getInstance(document, os);
                        createpdffile(request);
                        //document.close();
                        rs.close();
//                        out.println("file not closed");

//                        response.setHeader("Content-Disposition", "inline; filename=set_a.pdf");
//                        response.setHeader("Content-Type", "application/pdf");
//                        FileInputStream fis = new FileInputStream(file);
//                        OutputStream os = response.getOutputStream();
//                        baos.writeTo(os);
//                        byte[] buf = new byte[1024];
//                        int len = -1;
//                        try {
//                            response.setContentLengthLong(file.length());
//                            int byteread = 0;
//
//                            while ((byteread = fis.read()) != -1) {
//                                os.write(buf);
//                            }
//                            os.flush();
//                        } catch (Exception ex) {
//                        } finally {
//                            fis.close();
//                            os.close();
//                        }
//                        writer.close();
//                        out.println("</body>");
//                        out.println("</html>");
//                        response.setContentType("application/pdf");
//                        response.setHeader("Content-Disposition", "attachment; filename=" + filePath + ";");
                    } catch (DocumentException | FileNotFoundException | SQLException | ClassNotFoundException ex) {
//            Logger.getLogger(Create_PDF.class.getName()).log(Level.SEVERE, null, ex);
                        out.println("Exception caught at document level<br/>");
                        ex.printStackTrace(out);
                    } finally {
                        document.close();
                        writer.close();
                        fos.close();

//                        out.println("file closed");
                    }

                    //request.getRequestDispatcher("temppdf").forward(request, response);
                    request.getRequestDispatcher("thanks.jsp").include(request, response);
                    /* out.println(""
                            //+ "<html><body>"
                            + "<a target=\"_blank\" href=\"http://localhost:8084/Sign_Log/example/" + session.getId() + ".pdf\">Print</a>"*/
                    //+ "</body></html>"
                    //);
                } else {
                    //if subj is empty
//                    out.println("Subject empty");
                }
            } catch (Exception e) {
                out.println("Exception at try <br/>");
                e.printStackTrace(out);
            } finally {
                out.close();
            }

//            try (OutputStream os = response.getOutputStream()) {
//                document = new Document();
//                PdfWriter.getInstance(document, os);
////                            createpdffile(request);
//                document.add(new Paragraph("HELLO"));
//                document.close();
//                os.close();
//            } catch (DocumentException ex) {
//                Logger.getLogger(Create_PDF.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            request.getRequestDispatcher("temppdf").forward(request, response);
        } else {
            //if session is not new
            request.setAttribute("data", "Something went wrong!<br/>Please try again.");
            request.getRequestDispatcher("/Log_IN").forward(request, response);
        }
    }

    void createpdffile(HttpServletRequest request) throws DocumentException, SQLException, BadElementException, IOException {

        document.open();

        //here is the starting of the table format and all the headers
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(95);
        float[] colwidths = {2f, 2f, 3f, 2f, 3f};
        table.setWidths(colwidths);
        Font boldfont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font normalfont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        PdfPCell cell = new PdfPCell(new Phrase("Gandhinagar Institute of Technology", boldfont));
        cell.setBorder(0);
        cell.setHorizontalAlignment(1);
        cell.setPadding(5);
        cell.setPaddingBottom(0);
        cell.setColspan(5);
        cell.setVerticalAlignment(1);
        table.addCell(cell);
        sql = "SELECT `Dept_ID` FROM `users` WHERE `Email`= '" + data + "'";
        rs = st.executeQuery(sql);
        rs.next();
        String dept = String.valueOf(rs.getInt(1));
        rs.close();
        sql = "SELECT `Name` FROM `departments` WHERE `Dept_ID`= " + dept;
        rs = st.executeQuery(sql);
        rs.next();
        dept = rs.getString(1);
        dept = dept.replace('_', ' ');
        cell = new PdfPCell(new Phrase(dept, boldfont));
        cell.setBorder(0);
        cell.setHorizontalAlignment(1);
        cell.setPadding(5);
        cell.setPaddingTop(0);
        cell.setColspan(5);
        cell.setVerticalAlignment(1);
        cell.setMinimumHeight(30);
        table.addCell(cell);
        String set = request.getParameter("set");
        cell = new PdfPCell(new Phrase("SET " + set, boldfont));
        cell.setHorizontalAlignment(1);
        cell.setPadding(5);
        cell.setRowspan(2);
        cell.setVerticalAlignment(1);
        table.addCell(cell);
        String ct = request.getParameter("ct");
        cell = new PdfPCell(new Phrase("CT - " + ct, boldfont));
        cell.setHorizontalAlignment(1);
        cell.setPadding(5);
        cell.setRowspan(2);
        cell.setVerticalAlignment(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Jr. Supervisor’s Name & Sign", boldfont));
        cell.setHorizontalAlignment(1);
        cell.setColspan(2);
        cell.setPadding(5);
        cell.setVerticalAlignment(1);
        table.addCell(cell);
        cell = new PdfPCell();
        cell.setHorizontalAlignment(1);
        cell.setPadding(5);
        cell.setVerticalAlignment(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Examiner’s Name & Sign", boldfont));
        cell.setHorizontalAlignment(1);
        cell.setColspan(2);
        cell.setPadding(5);
        cell.setVerticalAlignment(1);
        table.addCell(cell);
        colwidths = new float[]{2f, 2f, 1f, 3f, 2f};
        table.setWidths(colwidths);
        cell = new PdfPCell();
        cell.setHorizontalAlignment(1);
        cell.setPadding(5);
        cell.setVerticalAlignment(1);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Enrollment No. :", normalfont));
        cell.setHorizontalAlignment(0);
        cell.setColspan(3);
        table.addCell(cell);
        String dt = request.getParameter("date");
        String tm = request.getParameter("time");
        cell = new PdfPCell(new Phrase("Date: " + dt + "  Time: " + tm, normalfont));
        cell.setHorizontalAlignment(0);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Semester: " + subj.charAt(2), normalfont));
        cell.setHorizontalAlignment(0);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Subject Code: " + subj, normalfont));
        cell.setHorizontalAlignment(0);
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Division & Batch: ", normalfont));
        cell.setHorizontalAlignment(0);
        cell.setColspan(3);
        table.addCell(cell);
        rs.close();
        sql = "SELECT `Name` FROM `subjects` WHERE `Subj_ID`= '" + subj + "'";
        rs = st.executeQuery(sql);
        rs.next();
        String subnm = rs.getString(1);
        subnm = subnm.replace('_', ' ');
        cell = new PdfPCell(new Phrase("Subject Name: " + subnm, normalfont));
        cell.setHorizontalAlignment(0);
        cell.setColspan(2);
        table.addCell(cell);
        document.add(table);

        //here are the instructions line and total and obtained marks coloumns
        table = new PdfPTable(5);
        table.setWidthPercentage(95);
        colwidths = new float[]{2f, 2f, 2f, 2f, 4f};
        table.setWidths(colwidths);
        cell = new PdfPCell(new Phrase("Instructions: ", boldfont));
        cell.setHorizontalAlignment(0);
        cell.setVerticalAlignment(0);
        cell.setColspan(4);
        cell.setBorderWidthBottom(0);
        table.addCell(cell);
        String marks = request.getParameter("totalmarks");
        cell = new PdfPCell(new Phrase("Maximum Marks: " + marks, boldfont));
        cell.setHorizontalAlignment(0);
        cell.setVerticalAlignment(0);
        cell.setPadding(3);
        cell.setPaddingRight(15);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("1) Circle the appropriate option/ fill in the blank using blue/black ball point pen.", normalfont));
        cell.setHorizontalAlignment(0);
        cell.setVerticalAlignment(0);
        cell.setColspan(4);
        cell.setBorderWidthTop(0);
        cell.setBorderWidthBottom(0);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Marks Obtained: ", boldfont));
        cell.setHorizontalAlignment(0);
        cell.setVerticalAlignment(0);
        cell.setPadding(3);
        cell.setRowspan(2);
        table.addCell(cell);
        if (set.contentEquals("A")) {
            cell = new PdfPCell(new Phrase("2) 1-10 Questions carry 1 mark. 11-15 Questions carry 2 marks.", normalfont));
        } else {
            cell = new PdfPCell(new Phrase("2) 1-5 Questions carry 2 mark. 6-15 Questions carry 1 marks.", normalfont));
        }
        cell.setHorizontalAlignment(0);
        cell.setVerticalAlignment(0);
        cell.setColspan(4);
        cell.setBorderWidthTop(0);
        table.addCell(cell);
        document.add(table);
        rs.close();

        //questions table starts
        table = new PdfPTable(5);
        table.setWidthPercentage(95);
        colwidths = new float[]{1f, 0.5f, 5f, 0.5f, 5f};
        table.setWidths(colwidths);
        String[] selection = request.getParameterValues("selection");
        for (int i = 0; i < selection.length; i++) {
            sql = "SELECT `Que_ID`, `Question`, `Option_1`, `Option_2`, `Option_3`, `Option_4`, `Answer`, `Is_Question_Image`, `image`, `Question_Type` FROM `questions` WHERE `Que_ID` = " + selection[i];
            rs = st.executeQuery(sql);
            rs.next();
            cell = new PdfPCell(new Phrase(" Q-" + (i + 1) + " "));
            if (rs.getString(8).contentEquals("YES")) {
                cell.setRowspan(4);
            } else {
                cell.setRowspan(3);
            }
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(rs.getString(2), normalfont));
            cell.setColspan(4);
            cell.setPadding(2);

            if (rs.getString(8).contentEquals("YES")) {
                cell.setBorderWidthBottom(0);
                table.addCell(cell);
                //Keep here path to the image from file questiondisplay and not the next line
                Image image = Image.getInstance(config.getServletContext().getRealPath("/") + "images\\" + subj + "_" + String.valueOf(rs.getInt(1)) + ".jpg");
                //Image image = Image.getInstance("2170710_9.jpg");
                image.scaleAbsolute(384, 192);
                cell = new PdfPCell(image);
                cell.setColspan(4);
                cell.setBorderWidthTop(0);
                cell.setPadding(5);
            }
            table.addCell(cell);

            if (rs.getString(10).contentEquals("Only_Question")) {
                cell = new PdfPCell();
                cell.setRowspan(2);
                cell.setColspan(4);
                table.addCell(cell);
            } else {
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
            }
        }
        document.add(table);
        //document.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
