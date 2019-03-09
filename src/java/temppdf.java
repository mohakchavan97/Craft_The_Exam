/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohak Chavan
 */
public class temppdf extends HttpServlet {

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
        
        ServletConfig config = getServletConfig();
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String filePath = config.getServletContext().getRealPath("/") + "example\\" 
                + session.getId().trim().toString()
                + ".pdf";
        try(PrintWriter out=response.getWriter()) {
            //response.setContentType("application/pdf");
            
            //response.setHeader("Expires", "0");
            //response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            
            //response.setHeader("Accept-Ranges", "bytes");
            /*File file = new File(filePath);
            int length=0;
            response.setContentLength((int)file.length());
            String filename=(new File(filePath)).getName();
            response.setHeader("Content-Disposition", "inline; filename=" + filename );
            //FileInputStream fis = new FileInputStream(file);
            //BufferedInputStream bis = new BufferedInputStream(fis);
            ServletOutputStream os = response.getOutputStream();
            byte[] buffer = new byte[4096];
            DataInputStream dis=new DataInputStream(new FileInputStream(file));
            while ((dis!=null)&& ((length=dis.read(buffer))!=-1)) {
                os.write(buffer, 0, length);
                os.flush();
            }
            os.flush();
            dis.close();
            os.close();*/
            
            out.println("<html><body>"
                    + "<a target=\"_blank\" href=\"./example/"+session.getId()+".pdf\">Print</a>"
                    + "</body></html>");
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
