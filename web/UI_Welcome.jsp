<%-- 
    Document   : newjsp
    Created on : 22 Feb, 2018, 12:10:58 AM
    Author     : Mohak Chavan
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>Welcome</title>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="style.css" media="all"/>
        <link rel="stylesheet" type="text/css" href="demo.css" media="all"/>
        <script type="text/javascript">
            function refresh() {
                var sem = document.getElementById('sem');
                /*var url = window.location.href;*/

                /*var selected_sem = sem.options[sem.selectedIndex].value;*/
                /*location.href = url + "?sem="+sem.options[sem.selectedIndex].value;*/
                location.href = "http://localhost:8084/Sign_Log/UI_Welcome.jsp?sem=" + sem.options[sem.selectedIndex].value;
            }
            function validation() {
                var sub = document.getElementById('subj');
                if ((sub.value) == '0_0') {
                    alert('Subject not selected');
                    sub.focus();
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <%            if (!(session.isNew())) {
                //if (true) {
                String data = (String) session.getAttribute("data");
                String f_name = (String) session.getAttribute("f_name");
                int sem;
                try {
                    sem = Integer.parseInt(request.getParameter("sem"));
                } catch (Exception ex) {
                    sem = 0;
                }
        %>
        <div class="container"><header><h1><span>Craft THE Exam</span>Welcome <%= f_name%></h1></header>
            <div class="form">
                <form id="br_sem" method="post" onsubmit="return validation()" action="Question_Display.jsp">
                    <table border="0">
                        <tr>
                            <td height="40" align="right"><label>Semester</label></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <%--<select id="sem" onchange="refresh()" name="sem" class="select-style">--%>
                                <select id="sem" onchange="refresh()" name="sem" class="select-style" autofocus>
                                    <option value="0"
                                            <%
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
                                            %>
                            </select>
                        </td>
                    </tr>
                    <%
                        if (sem != 0) {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
                            Statement st = (Statement) con.createStatement();
                            String sql = "SELECT `Dept_ID` FROM `users` WHERE `Email`= '" + data + "'";
                            ResultSet rs = st.executeQuery(sql);
                            rs.next();
                            int dept_id = rs.getInt(1);
                            if (dept_id == 9 || dept_id == 11) {
                                sql = "SELECT `Subj_ID`,`Name` FROM `subjects` WHERE (`Dept_ID`=" + dept_id + " OR `Dept_ID`=10) AND `Semester`=" + sem;
                            } else {
                                sql = "SELECT `Subj_ID`,`Name` FROM `subjects` WHERE `Dept_ID`=" + dept_id + " AND `Semester`=" + sem;
                            }
                            rs.close();
                            rs = st.executeQuery(sql);
                            out.println("<tr><td height=\"40\" align=\"right\"><label>Subject</label></td>"
                                    + "<td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><select name=\"subj\" id=\"subj\" class=\"select-style\" style=\"max-width:250px; min-width:250px;\">"
                                    + "<option value=\"0_0\">Subject</option>");
                            while (rs.next()) {
                                out.println("<option value=\"" + rs.getInt(1) + "\">" + rs.getInt(1) + " - " + rs.getString(2) + "</option>");
                            }
                            out.println("</select></td></tr>"
                                    + "<tr height=\"20\"><td></td><td></td>\n"
                                    + "<td align=\"center\">\n"
                                    + "<input name=\"next\" id=\"next\" value=\"GO\" class=\"buttom\" type=\"submit\"/>\n"
                                    + "</td></tr>");
                        }
                    %>


                    <%
                        } else {
                            //out.println("session error");
                            request.setAttribute("data", "Something went wrong!<br/>Please try again.");
                            request.getRequestDispatcher("/Log_IN").forward(request, response);
                        }
                    %>
                </table></form></div></div>
</body>
</html>
