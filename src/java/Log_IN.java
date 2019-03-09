/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rahil Dave
 */
public class Log_IN extends HttpServlet {

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
        String data = null;
        //HttpSession session=request.getSession();
        //session.invalidate();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>"
                    + "<title>Craft THE Exam LOG IN</title>"
                    + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=EmulateIE7; IE=EmulateIE9\"/>"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"all\"/>"
                    + "<link rel=\"stylesheet\" type=\"text/css\" href=\"demo.css\" media=\"all\"/>"
                    + "<script type=\"text/javascript\" src=\"Log_IN_JS_File.js\">"
                    + "</script>");
            out.println("</head>");
            out.println("<body>");

//            out.println("<h1>Servlet Log_IN at " + request.getContextPath() + "</h1>");
            try {
                data = (request.getAttribute("data")).toString();
            } catch (Exception ex) {
            }
            //
            out.println("<div class=\"container\">"
                    + "<header>"
                    + "<h1><span>Craft THE Exam</span>Sign IN</h1>"
                    + "</header>"
                    + "<div class=\"form\">"
                    + "<form id=\"login\" method=\"post\" action=\"Log_IN_Backend\" onsubmit=\"return validation()\">"
                    + "<table border=\"0\" cellpadding=\"0\">"
                    + "<tr>"
                    + "<td height=\"40\"><label for=\"email\">Email</label></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td colspan=\"3\"><input id=\"email\" name=\"email\" required=\"required\" autofocus type=\"email\"/></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td height=\"40\"><label for=\"password\">Password</label></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td colspan=\"3\"><input type=\"password\" id=\"password\" name=\"password\" required=\"required\"/></td>"
                    + "</tr>");
            if (data != null) {
                out.println("<tr height=\"30\"><td colspan=\"3\"><h3><font color=\"red\">" + data + "</font></h3></td></tr>");
            }
            out.println("<tr height=\"20\">"
                    + "<td colspan=\"3\" align=\"center\"><input class=\"buttom\" name=\"login\" id=\"login\" value=\"LOG IN\" type=\"submit\"/></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td height=\"80\" align=\"left\"><a href=\"\" onclick=\"return forpass()\">Forgot Password</a></td>"
                    + "<td align=\"right\"><a href=\"Sign_UP.jsp\">New User? Register NOW</a></td>"
                    + "</tr>"
                    + "</table>"
                    + "</form>"
                    + "</div>"
                    + "</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ServletConfig config=getServletConfig();
        new File(config.getServletContext().getRealPath("/")+"/example").mkdir();
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
