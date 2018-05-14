/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohak Chavan
 */
public class UI_Welcome_Backend extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        String sql = null, f_name, data;

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
            Statement st = (Statement) con.createStatement();
            data = request.getParameter("data");
            f_name = request.getParameter("f_name");

            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"
                    + "<title>Welcome</title>"
                    + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7; IE=EmulateIE9\"/>"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"demo.css\" media=\"all\"/>"
                    + "<script type=\"text/javascript\" src=\"UI_Welcome_Backend_JS_File.js\"></script>");
            out.println("</head>");
            out.println("<body>");

//            out.println("<h1>Servlet UI_Welcome_Backend at " + request.getContextPath() + "</h1>");
            String dept_id = request.getParameter("branch");
            String sem = request.getParameter("sem");
            sql = "SELECT `Subj_ID`, `Name` FROM `subjects` WHERE `Dept_ID`= " + dept_id + " AND `Semester`= " + sem;
            ResultSet rs = st.executeQuery(sql);
            out.println("<div class=\"container\"><header><h1><span>Craft THE Exam</span>Welcome " + f_name + "</h1></header>");
            out.println("<div class=\"form\">");
            out.println("<form id=\"br_sem\" method=\"post\" action=\"UI_Welcome_Backend\" onsubmit=\"return validation()\">"
                    + "<table border=\"0\">");
            out.println("<tr><td height=\"40\" align=\"right\"><label>Subject</label></td>"
                    + "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><select name=\"subj\" id=\"subj\" class=\"select-style\" style=\"max-width:350px;\">"
                    + "<option value=\"0_0\">Subject</option>");

            while (rs.next()) {
                out.println("<option value=\"\">" + rs.getInt(1) + " - " + rs.getString(2)+"</option>");
            }

            out.println("</select></td></tr>"
                    + "<tr height=\"20\"><td></td><td></td>\n"
                    + "<td align=\"center\">\n"
                    + "<input name=\"next\" id=\"next\" value=\"GO\" class=\"buttom\" type=\"submit\"/>\n"
                    + "<input type=\"hidden\" name=\"data\" value=\"" + data + "\" id=\"data\">"
                    + "<input type=\"hidden\" name=\"f_name\" value=\"" + f_name + "\" id=\"f_name\">"
                    + "</td></tr>");

            out.println("</table></form></div></div></body>");
            out.println("</html>");

            st.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UI_Welcome_Backend.class.getName()).log(Level.SEVERE, null, ex);
        }

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
