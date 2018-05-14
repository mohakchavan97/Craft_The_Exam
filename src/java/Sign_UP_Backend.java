/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohak Chavan
 */
public class Sign_UP_Backend extends HttpServlet {

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
        RequestDispatcher rd_return = request.getRequestDispatcher("/Sign_UP.jsp");
        HttpSession session = request.getSession();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>User Registeration Status</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\" />\n"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"demo.css\" media=\"all\" />"
                    + "<script type=\"text/javascript\" src=\"Log_IN_JS_File.js\"></script>");
            out.println("</head>");
            out.println("<body>");

            boolean status = false;
            String fname, lname, email, pass, repass, cont, gend, bmonth, bday, byear, dept, desg, key = "Moh@k25R@hil31H@rsh25", error = "";
            fname = lname = email = pass = repass = cont = gend = bmonth = bday = byear = dept = desg = "";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
                Statement st = (Statement) con.createStatement();
                fname = request.getParameter("fname");
                lname = request.getParameter("lname");
                email = request.getParameter("email");
                pass = request.getParameter("password");
                repass = request.getParameter("repassword");
                cont = request.getParameter("cont");
                gend = request.getParameter("gend");
                bmonth = request.getParameter("bmonth");
                bday = request.getParameter("bday");
                byear = request.getParameter("byear");
                dept = request.getParameter("dept");
                desg = request.getParameter("desg");

                ResultSet rs = st.executeQuery("SELECT `ID` FROM `users` WHERE `Email`='" + email + "'");
                if (!(rs.next())) {
                    rs.close();

                    int desg_int = Integer.parseInt(desg), dept_int = Integer.parseInt(dept);
                    if (desg_int == 3) {
                        ResultSet i = st.executeQuery("SELECT `ID` FROM `users` WHERE `Dept_ID`= " + dept_int + " AND `Desig_ID`= " + desg_int);
                        if (i.next()) { //here the code of avoiding multiple hod of particular department will come
                        }
                    }

//                int i = st.executeUpdate("INSERT INTO `users`(`Email`, `Password`, `First_Name`, `Last_Name`, `gender`, `birth_date`, `birth_month`, `birth_year`, `Mobile_no`, `Dept_ID`, `Desig_ID`) VALUES ('" + email + "','" + pass + "','" + fname + "','" + lname + "','" + gend + "','" + bday + "','" + bmonth + "','" + byear + "','" + cont + "','" + dept + "','" + desg + "')");
                    int i = st.executeUpdate("INSERT INTO `users`(`Email`, `Password`, `First_Name`, `Last_Name`, `gender`, `birth_date`, `birth_month`, `birth_year`, `Mobile_no`, `Dept_ID`, `Desig_ID`) VALUES ('" + email + "',DES_ENCRYPT('" + pass + "','" + key + "'),'" + fname + "','" + lname + "','" + gend + "','" + bday + "','" + bmonth + "','" + byear + "','" + cont + "','" + dept + "','" + desg + "')");

                    if (i > 0) {
                        status = true;
                    }

                    if (status) {
                        out.println("<div class=\"container\"><header><h1><span>Craft THE Exam</span><font size=\"7\">Congratulations!&nbsp;" + fname + "&nbsp;" + lname + "</font></h1></header>");
                        out.println("<div class=\"form\">"
                                + "<form id=\"login\" method=\"post\" action=\"Log_IN_Backend\" onsubmit=\"return validation()\">"
                                + "<table border=\"0\" cellpadding=\"0\">"
                                + "<tr><td height=\"40\"><label for=\"email\">Email</label></td></tr>"
                                + "<tr><td colspan=\"3\"><input id=\"email\" name=\"email\" required=\"required\" type=\"email\"></td></tr>"
                                + "<tr><td height=\"40\"><label for=\"password\">Password</label></td></tr>"
                                + "<tr><td colspan=\"3\"><input type=\"password\" id=\"password\" name=\"password\" required=\"required\"></td></tr>"
                                + "<tr height=\"20\"><td colspan=\"3\" align=\"center\"><input class=\"buttom\" name=\"login\" id=\"login\" value=\"LOG IN\" type=\"submit\"/></td></tr>"
                                + "<tr><td height=\"80\" align=\"left\"><a href=\"\">Forgot Password?</a></td></tr>");
                    } else {
                        out.println("<br/><h1 align=\"center\">User Not Registered</h1>");
//                        if (error.contains("Duplicate entry") && error.contains("Email")) {
//                            session.setAttribute("error", "Email ID already exists.");
//                            response.sendRedirect("/Sign_Log/Sign_UP.jsp");
//                    rd_return.forward(request, response);
//                    out.println("<br/><h1 align=\"center\">Email ID already exists.</h1>");
                    }
                } else //if the email already exists
                {
                    out.println(" <div class=\"container\">\n"
                            + "<header>\n"
                            + "<h1><span>Craft THE Exam</span>Registration</h1>\n"
                            + "</header>"
                            + "<div class=\"form\">"
                            + "<form id=\"contactform\" method=\"post\" action=\"Sign_UP_Backend\" onsubmit=\"return validation()\">\n"
                            + "<table border=\"0\" cellpadding=\"0\">\n"
                            + "<tr>\n"
                            + "<td width=\"195\" height=\"40\"><label for=\"name\">First Name</label></td>\n"
                            + "</tr>"
                            + "<tr>"
                            + "<td colspan=\"3\"><input id=\"fname\" name=\"fname\" value=\"" + fname + "\" readonly/></td>"
                            + "</tr>"
                            + "<tr>\n"
                            + "<td height=\"40\"><label for=\"name\">Last Name</label></td>\n"
                            + "</tr>\n"
                            + "<tr>\n"
                            + "<td colspan=\"3\"><input id=\"lname\" name=\"lname\" value=\"" + lname + "\" readonly/></td>\n"
                            + "</tr>\n"
                            + "<tr>\n"
                            + "<td valign=\"top\"><label for=\"gender\">Gender:</label></td>\n"
                            + "<td colspan=\"2\" align=\"center\">"
                            + "<input name=\"gend\" value=\"" + gend + "\" readonly/></td></tr><tr>"
                            + "<tr><td colspan=\"3\"><hr></td></tr>"
                            + "<tr>"
                            + "<td height=\"40\"><label for=\"email\">Email</label></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td colspan=\"3\"><input id=\"email\" name=\"email\" placeholder=\"example@git.org.in\" required=\"required\" type=\"email\" autofocus></td></tr><tr height=\"0\"><td colspan=\"3\" valign=\"top\"><h3 id=\"hid\"><font color=\"red\">Email ID already exists.</font></h3></td></tr>"
                            + "<tr>"
                            + "<td height=\"40\"><label for=\"password\">Enter Password</label></td>\n"
                            + "</tr>"
                            + "<tr>"
                            + "<td colspan=\"3\"><input type=\"password\" id=\"password\" name=\"password\" required=\"required\"></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td height=\"40\"><label for=\"repassword\">Confirm Password</label></td>\n"
                            + "</tr>"
                            + "<tr>"
                            + "<td colspan=\"3\"><input type=\"password\" id=\"repassword\" name=\"repassword\" required=\"required\"></td>\n"
                            + "</tr>"
                            + "<tr><td colspan=\"3\"><hr></td></tr>"
                            + "<tr>"
                            + "<td height=\"30\" valign=\"bottom\" style=\"margin-top: 3px\"><label>Birthday Details:</label></td>\n"
                            + "</tr>");

                    //tranformation of months,dept and desg from values to words for display purpose only
                    String bmonth_nh = bmonth, dept_nh = dept, desg_nh = desg;
                    switch (bmonth) {
                        case "01":
                            bmonth_nh = "January";
                            break;
                        case "02":
                            bmonth_nh = "February";
                            break;
                        case "03":
                            bmonth_nh = "March";
                            break;
                        case "04":
                            bmonth_nh = "April";
                            break;
                        case "05":
                            bmonth_nh = "May";
                            break;
                        case "06":
                            bmonth_nh = "June";
                            break;
                        case "07":
                            bmonth_nh = "July";
                            break;
                        case "08":
                            bmonth_nh = "August";
                            break;
                        case "09":
                            bmonth_nh = "September";
                            break;
                        case "10":
                            bmonth_nh = "October";
                            break;
                        case "11":
                            bmonth_nh = "November";
                            break;
                        case "12":
                            bmonth_nh = "December";
                            break;
                    }
                    switch (dept) {
                        case "7":
                            dept_nh = "Computer Engineering";
                            break;
                        case "16":
                            dept_nh = "Information Technology";
                            break;
                        case "9":
                            dept_nh = "Electrical Engineering";
                            break;
                        case "11":
                            dept_nh = "Electronics & Comm. Engineering";
                            break;
                        case "19":
                            dept_nh = "Mechanical Engineering";
                            break;
                        case "6":
                            dept_nh = "Civil Engineering";
                            break;
                        case "0":
                            dept_nh = "Mathematics & Humanities";
                            break;
                    }
                    switch (desg) {
                        case "1":
                            desg_nh = "Asst. Professor";
                            break;
                        case "2":
                            desg_nh = "Professor";
                            break;
                        case "3":
                            desg_nh = "Head of Department";
                            break;
                    }
                    out.println("<tr>"
                            + "<td height=\"20\" valign=\"top\"><label>Month</label></td>\n"
                            + "<td align=\"center\">"
                            + "<input name=\"bmonth_nh\" value=\"" + bmonth_nh + "\" readonly/><input name=\"bmonth\" type=\"hidden\" value=\"" + bmonth + "\"/></td>"
                            + "</tr><tr>"
                            + "<td height=\"20\" valign=\"top\"><label>Date</label></td>\n"
                            + "<td align=\"center\"><input name=\"bday\" value=\"" + bday + "\" readonly/></td>"
                            + "</tr><tr>"
                            + "<td height=\"20\" valign=\"top\"><label>Year</label></td>\n"
                            + "<td align=\"center\"><input name=\"byear\" max-width=\"100px\" value=\"" + byear + "\" readonly/></td>"
                            + "</tr>"
                            + "<tr><td colspan=\"3\"><hr></td></tr>"
                            + "<tr>"
                            + "<td valign=\"middle\"><label>Department</label></td>\n"
                            + "<td colspan=\"2\" align=\"center\">"
                            + "<input name=\"dept_nh\" style=\"margin-top: 2px\" value=\"" + dept_nh + "\" readonly/><input name=\"dept\" type=\"hidden\" value=\"" + dept + "\"/></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td valign=\"top\" height=\"40\"><label>Designation</label></td>\n"
                            + "<td colspan=\"2\" align=\"center\">"
                            + "<input name=\"desg_nh\" value=\"" + desg_nh + "\" readonly/><input name=\"desg\" type=\"hidden\" value=\"" + desg + "\"/></td>"
                            + "</tr>"
                            + "<tr>"
                            + "<td height=\"40\"><label class=\"contact\" for=\"number\">Mobile&nbsp;Number</label></td>\n"
                            + "</tr>"
                            + "<tr>"
                            + "<td colspan=\"3\">"
                            + "<input name=\"cont\" value=\"" + cont + "\" readonly/></td>"
                            + "</tr>"
                            + "<tr height=\"20\">"
                            + "<td colspan=\"3\" align=\"center\"><input class=\"buttom\" name=\"submit\" id=\"submit\" value=\"Sign UP\" type=\"submit\"/></td></tr>");
                }
            } catch (SQLException | ClassNotFoundException ex) {
//                Logger.getLogger(Sign_UP_Backend.class.getName()).log(Level.SEVERE, null, ex);
                status = false;
                out.println("<h1 align=\"center\">" + ex + "</h1>");
                error = ex.toString();
            }
            out.println("</table></form></div></div>");
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
