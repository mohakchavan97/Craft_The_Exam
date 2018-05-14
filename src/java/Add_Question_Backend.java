/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.Blob;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Mohak Chavan
 */
@MultipartConfig
public class Add_Question_Backend extends HttpServlet {

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
        HttpSession session = request.getSession();
        String subj = (String) session.getAttribute("subj");
        RequestDispatcher rd_forward = request.getRequestDispatcher("/UI_Welcome.jsp");
        RequestDispatcher rd_return = request.getRequestDispatcher("/Add_Question.jsp");
        PrintWriter out = null;
        try {
            /* TODO output your page here. You may use following sample code. */
            out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Add_Question_Backend</title>");
            out.println("</head>");
            out.println("<body>");
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
                String question = request.getParameter("question");
                
                String answer = request.getParameter("ans");
                String sql = null, opt1 = null, opt2 = null, opt3 = null, opt4 = null;
                Blob img;
                String marks = request.getParameter("marks");
                String isqueimg = request.getParameter("isqueimg");
                String quetype = request.getParameter("quetype");
                
                sql = "INSERT INTO `questions`(`Question_Type`, `Subj_ID`, `Is_Question_Image`, `Question`, `Option_1`, `Option_2`, `Option_3`, `Option_4`, `Answer`, `image`, `Marks`) VALUES ( '" + quetype + "' , '" + subj + "' , '" + isqueimg + "' , '" + question + "' , ? , ? , ? , ? , '" + answer + "' , ? , '" + marks + "' )";
                PreparedStatement statement = con.prepareStatement(sql);
                
                if (quetype.contentEquals("MCQ_Based")) { //if mcq based is selected
                    statement.setString(1, request.getParameter("opt1").trim());
                    statement.setString(2, request.getParameter("opt2").trim());
                    statement.setString(3, request.getParameter("opt3").trim());
                    statement.setString(4, request.getParameter("opt4").trim());
                    
                    if (isqueimg.contentEquals("YES")) { //if there is image to be handled in mcq based
                        Part part = request.getPart("pic");
                        
                        if (part != null) {
                            InputStream inputStream = part.getInputStream();
                            statement.setBlob(5, inputStream);
                            
                        }
                    } else { //if there is no image to be handled in mcq based
                        statement.setString(5, null);
                    }
                } else { //if only question is selected
                    statement.setString(1, "");
                    statement.setString(2, "");
                    statement.setString(3, "");
                    statement.setString(4, "");
                    if (isqueimg.contentEquals("YES")) { //if there is image to be handled in only question
                        Part part = request.getPart("pic");
                        
                        if (part != null) {
                            InputStream inputStream = part.getInputStream();
                            statement.setBlob(5, inputStream);
                        }
                    } else { //if there is no image to be handled in only question
                        statement.setString(5, null);
                    }
                }
                int i = statement.executeUpdate();
                //out.println(statement.toString());
                if (i > 0) {
                    //out.println("Image inserted");
//                    request.setAttribute("subj", subj);
                    request.setAttribute("subj", subj);
                    rd_forward.forward(request, response);
                } else {
                    //out.println("Image not inserted");
                    request.setAttribute("data", "Something went wrong!<br/>Try again.");
                    rd_return.forward(request, response);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                //Logger.getLogger(Add_Question_Backend.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex);
                ex.printStackTrace(out);
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            ex.printStackTrace(out);
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
