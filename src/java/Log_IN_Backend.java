/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Log_IN_Backend extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Logged IN</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\" />\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"demo.css\" media=\"all\" />");
            out.println("</head>");
            out.println("<body>");

//            out.println("<center><table align=\"center\">");
            boolean status = false;

            String email, pass, email_db = null, pass_db, sql, f_name_db = null, key = "Moh@k25R@hil31H@rsh25", error = "";

//            session.setMaxInactiveInterval(1800);
            RequestDispatcher rd_return = request.getRequestDispatcher("/Log_IN");
//            RequestDispatcher rd_forward = request.getRequestDispatcher("/UI_Welcome");
            RequestDispatcher rd_forward = request.getRequestDispatcher("/UI_Welcome.jsp");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
                Statement st = (Statement) con.createStatement();

                email = request.getParameter("email");
                email = email.trim();
                pass = request.getParameter("password");
                pass = pass.trim();
//                sql = "SELECT `Email`, `First_Name`, `Password` FROM `users` WHERE Email= '" + email + "'";

                sql = "SELECT `Email`, `First_Name` FROM `users` WHERE `Email` = '" + email + "'";

                ResultSet rs = st.executeQuery(sql);
                /*             while (rs.next()) {
                    out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td></tr>");
                }*/
                if (rs.next()) {
                    //  rs.next();
                    email_db = rs.getString(1);
                    f_name_db = rs.getString(2);
                    sql = "SELECT `Password` FROM `users` WHERE `Email` = '" + email + "' AND `Password` =DES_ENCRYPT('" + pass + "','" + key + "')";
                    ResultSet rs2 = st.executeQuery(sql);

//                    if (pass.equals(pass_db)) {
//                        status = true;
//                    if (!(email_db.isEmpty()) && !(f_name_db.isEmpty()) && !(pass_db.isEmpty())) {
                    if (rs2.next()) {
                        status = true;

                    } else {
//                        out.println("<h1 align=\"center\">You Have Entered Wrong Password </h1>");
//                        out.println("<div class=\"container\"><header><h1><span>Craft THE Exam</span>The email ID and Password does not match</h1></header>");
                        request.setAttribute("data", "The email ID and Password does not match!");
                        rd_return.forward(request, response);
                    }
                } else {
//                    out.println("<h1 align=\"center\">No Entry Found!!</h1>");
                    request.setAttribute("data", "No Entry Found!!");
                    rd_return.forward(request, response);
                }

            } catch (SQLException | ClassNotFoundException ex) {
//                Logger.getLogger(Log_IN_Backend.class.getName()).log(Level.SEVERE, null, ex);
                status = false;
//                out.println("<h1 align=\"center\">" + ex + "</h1>");
                error = ex.toString();
            }

            if (status) {
//                out.println("<br/><h1 align=\"center\">User Logged IN " + email_db + "</h1>");
//                request.setAttribute("data", email_db);
//                request.setAttribute("fname", f_name_db);
                HttpSession session = request.getSession(true);
                request.changeSessionId();

                session.setAttribute("data", email_db);
                session.setAttribute("f_name", f_name_db);
                rd_forward.forward(request, response);

            } else {
//                out.println("<br/><h1 align=\"center\">User Not Logged IN</h1>");
                error = "Something went wrong!<br/>Try again later.<br/>Error: " + error;
                request.setAttribute("data", error);
                rd_return.forward(request, response);
            }

            //out.println("<h1>Servlet Log_IN_Backend at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
