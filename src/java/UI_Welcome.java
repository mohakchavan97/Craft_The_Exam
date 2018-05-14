/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rahil Dave
 */
public class UI_Welcome extends HttpServlet {

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
        String data = null, f_name = null;
        HttpSession session = request.getSession(false);
        RequestDispatcher rd_return = request.getRequestDispatcher("/UI_Welcome");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");

            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"
                    + "<title>Welcome</title>"
                    + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7; IE=EmulateIE9\"/>"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"demo.css\" media=\"all\"/>"
                    //                    + "<script type=\"text/javascript\" src=\"UI_Welcome_JS_File.js\">"
                    + "<script type=\"text/javascript\">"
                    + "function validation() {"
                    + "var sem = document.getElementById('sem');"
                    + "if ((sem.value) == '0') {"
                    + "alert('Semester not selected');"
                    + "sem.focus();"
                    + "return false;"
                    + "}"
                    + "}"
                    + "function refresh() {"
                    + "var sem = document.getElementById('sem');"
                    + "var url = window.location.href;"
            //                    + "location.href=url+\"?sem=\"+sem.options[sem.selectedIndex].value;"
            );

            out.println(""
                    + "}"
                    + "</script>");
            out.println("</head>");
            out.println("<body>");
            if (session != null) {
                data = (String) session.getAttribute("data");
                f_name = (String) session.getAttribute("f_name");
                int sem;
                try {
                    sem = Integer.parseInt(request.getParameter("sem"));
                } catch (NumberFormatException ex) {
                    sem = 0;
                    out.println("exception at getting sem value.");
                }
//            out.println("<h1>Servlet Log_IN at " + request.getContextPath() + "</h1>");
                out.println("<div class=\"container\"><header><h1><span>Craft THE Exam</span>Welcome " + f_name + "</h1></header>");
                out.println("<div class=\"form\">");

                out.println("<form id=\"br_sem\" method=\"post\""
                        //                        + " action=\"UI_Welcome_Backend\" onsubmit=\"return validation()\""
                        + ">"
                        + "<table border=\"0\">");
                out.println("<tr>"
                        + "<td height=\"40\" align=\"right\"><label>Semester</label></td>"
                        + "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><select id=\"sem\" onchange=\"refresh()\" name=\"sem\" class=\"select-style\">"
                //                        + "<option value=\"0\">Semester</option>"
                //                        + "<option value=\"1\">01</option>"
                //                        + "<option value=\"2\">02</option>"
                //                        + "<option value=\"3\">03</option>"
                //                        + "<option value=\"4\">04</option>"
                //                        + "<option value=\"5\">05</option>"
                //                        + "<option value=\"6\">06</option>"
                //                        + "<option value=\"7\">07</option>"
                //                        + "<option value=\"8\">08</option>"
                );
                out.print("<option value=\"0\"");
                if (sem == 0) {
                    out.print(" selected");
                }
                out.println(">Semester</option>");
                for (int i = 1; i < 9; i++) {
                    out.print("<option value=\"" + i + "\"");
                    if (i == sem) {
                        out.print(" selected");
                    }
                    out.println(">0" + i + "</option>");
                }

                out.println(""
                        + "</select></td></tr>"
                        + "<tr height=\"20\"><td></td><td></td>\n"
                        + "<td  align=\"center\">\n"
                        //+ "<input name=\"next\" id=\"next\" value=\"Next\" class=\"buttom\" type=\"submit\"/>\n"
                        + "</td></tr>");

                /*try {
                Class.forName("com.mysql.jdbc.Driver");

                java.sql.Connection con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
                Statement st = (Statement) con.createStatement();
                String sql = "SELECT `image` FROM `questions` WHERE `Que_ID` = 9";
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                Blob image = rs.getBlob(1);
                byte[] imgbytes = image.getBytes(1, (int) image.length());
                out.println("<tr><td>INPUT=</td><td>" + imgbytes + "</td></tr>");
            } catch (ClassNotFoundException | SQLException ex) {
//                Logger.getLogger(UI_Welcome.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<tr><td>" + ex + "</td></tr>");
            }*/
            } else {
//                request.getRequestDispatcher("/Log_IN");
                out.println("session not got");
            }
            out.println("</table></form></div></div></body></html>");

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
