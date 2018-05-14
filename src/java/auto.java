/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mohak Chavan
 */
public class auto extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet auto</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet auto at " + request.getContextPath() + "</h1>");
            out.println("<p>Select a new car from the list.</p>\n"
                    + "\n"
                    + "        <select id=\"mySelect\" onchange=\"myFunction()\">\n"
                    + "            <option value=\"select\" selected></option>\n"
                    + "            <option value=\"demo\">Audi</option>\n"
                    + "            <option value=\"demo2\">BMW</option>\n"
                    + "            <option value=\"Mercedes\">Mercedes</option>\n"
                    + "            <option value=\"Volvo\">Volvo</option>\n"
                    + "        </select>\n"
                    + "\n"
                    + "        <p>When you select a new car, a function is triggered which outputs the value of the selected car.</p>\n"
                    + "\n"
                    + "        <select id=\"demo\" onchange=\"myFunction()\" hidden=\"true\">\n"
                    + "            <option value=\"Audi\">Audi</option>\n"
                    + "            <option value=\"Mercedes\">Mercedes</option>\n"
                    + "            <option value=\"Volvo\">Volvo</option>\n"
                    + "        </select>\n"
                    + "\n"
                    + "        <select id=\"demo2\" onchange=\"myFunction()\" hidden=\"true\">\n"
                    + "            <option value=\"Audi\">Audi</option>\n"
                    + "            <option value=\"BMW\">BMW</option>\n"
                    + "            <option value=\"Mercedes\">Mercedes</option>\n"
                    + "            <option value=\"Volvo\">Volvo</option>\n"
                    + "        </select>\n"
                    + "\n"
                    + "        <script>\n"
                    + "            function myFunction() {\n"
                    + "                var x = document.getElementById(\"mySelect\").value,\n"
                    //                    + "                        url = 'file:///E:/Google%20Drive/GIT/Project/Project-II/auto.html?name=' +x;\n"
                    //                    +"url='"+request.getContextPath()+"/auto?name='+encodeURI(x);"
                    + "                        url = '" + request.getContextPath() + "/auto?name=' +x;\n"
                    + "                document.location.href = url;\n"
                    + "                location.reload(true);\n"
                    + "\n"
                    + "            }\n"
                    + "            window.onload = function () {\n"
                    + "                var url = document.location.href,\n"
                    + "                        params = url.split('?')[1].split('&'),\n"
                    + "                        data = {}, tmp;\n"
                    + "                for (var i = 0, l = params.length; i < l; i++) {\n"
                    + "                    tmp = params[i].split('=');\n"
                    + "                    data[tmp[0]] = tmp[1];\n"
                    + "                }\n"
                    + "                document.getElementById(data.name).hidden = false;\n"
                    + "            }\n"
                    + "        </script>\n"
                    + "");
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
