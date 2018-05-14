<%-- 
    Document   : Question_Display
    Created on : 28 Feb, 2018, 2:50:05 PM
    Author     : Mohak Chavan
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%--<jsp:include page="\Create_PDF"/>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Questions Display</title>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="style.css" media="all" />
        <link rel="stylesheet" type="text/css" href="demo.css" media="all" />
    </head>
    <style>
        label { color: #000; font-weight:bold;font-size: 15px;font-family:Arial, Helvetica; }

        .buttom2{ background: #4b8df9; display: inline-block; padding: 5px 10px 6px; color: #fbf7f7; text-decoration: blink; font-weight: bold; line-height: 1; margin:20px 0px 20px 0px; -moz-border-radius: 5px; -webkit-border-radius: 5px; border-radius: 5px; -moz-box-shadow: 0 1px 3px #999; -webkit-box-shadow: 0 1px 3px #999; box-shadow: 0 1px 3px #999; text-shadow: 0 -1px 1px #222; border: none; position: relative; cursor: pointer; font-size: 14px; font-family:Verdana, Geneva, sans-serif;}

        .buttom2:hover	{ background-color: #2a78f6; }
        .select-style {
            -webkit-appearance: button; -webkit-border-radius: 2px; -webkit-box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1); -webkit-padding-end: 35px; -webkit-padding-start: 2px; -webkit-user-select: none;
            background-image: url(images/select-arrow.png),linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5);
            /*-webkit-linear-gradient (#FAFAFA, #F4F4F4 40%, #E5E5E5);*/
            background-position: center right; background-repeat: no-repeat; border: 0px solid #FFF; color: #555; font-size: inherit; margin: 0px 0px 10px 0px; padding-top: 5px; padding-bottom: 5px; white-space: nowrap;}
        .contact { font-size: 14px; margin:0px 0px 10px 0;line-height: 14px; font-family:Arial, Helvetica;}
        input[type="checkbox"]{
            width: 24px; /*Desired width*/
            height: 24px; /*Desired height*/
            cursor: pointer;
        }
    </style>

    <script type="text/javascript">
        function checkTotal() {
            var sum = 0;
            var total = document.getElementById("sel_marks");
            //var selected;
            //var selection = document.getElementsByName("selection");
            //var sel_values = document.getElementByName("selection").values();
            var i;
            for (i = 0; i < document.ques_disp.selection.length; i++) {
                if (document.ques_disp.selection[i].checked) {
                    //total.innerHTML=sel_values[i].toString();
                    sum = sum + parseInt(document.getElementById((document.ques_disp.selection[i].value).toString()).value);
                }
            }
            total.value = sum;
        }

        function validate() {
            var tot = Number(document.getElementById("totalmarks").value);
            var sel = Number(document.getElementById("sel_marks").value);
            if (tot != sel) {
                alert('Total Marks Selected are not equal to Total Marks');
                return false;
            }
        }
    </script>

    <body>
        <%            if (!(session.isNew())) {
                //if (true) {
                String data = (String) session.getAttribute("data");
                String f_name = (String) session.getAttribute("f_name");
                Enumeration<String> names = session.getAttributeNames();
                String subj = "empty", x;
                boolean status = true;
                try {
                    subj = request.getParameter("subj");
                    session.setAttribute("subj", subj);

                    //if (status) {
                    //subj=request.getParameter("subj");                    
                    //if (!(((String) request.getAttribute("subj")).isEmpty())) {
                    //}
                } catch (Exception ex) {
                    //subj = "empty";

                    try {
                        subj = (request.getAttribute("subj")).toString();
                        session.setAttribute("subj", subj);

                    } catch (Exception e) {
                        while (names.hasMoreElements()) {
                            x = names.nextElement();
                            if (x.contentEquals("subj")) {
                                subj = (String) session.getAttribute("subj");
                                //status = false;
                                continue;
                            }
                        }
                    }

                }
        %>
        <div class="container" style="height: 14%; position: fixed; background-image: url('images/bac1.jpg'); top: 0px;left: 0px; width: 100%;">
            <header>
                <h1><span>Craft THE Exam</span>Select Questions</h1>
            </header>
        </div>
        <form name="ques_disp" action="Create_PDF" method="post" onsubmit="return validate()">
            <div class="container" style="height: 10%; position: fixed; top: 14%; left: 0px; width: 100%; background-image: url(images/bgnoise_lg.png);" align="center">

                <label style="margin-left: 1%;">SET -</label><select id="set" style="margin-right: 1%; margin-left: 0.5%;" class="select-style" name="set">
                    <option value="A">SET A</option>
                    <option value="B">SET B</option>
                </select>

                <label style="margin-left: 1%;">CLASS TEST -</label><select id="ct" style="margin-right: 1%; margin-left: 0.5%;" class="select-style" name="ct">
                    <option value="1">CT - 1</option>
                    <option value="2">CT - 2</option>
                </select>

                <label style="margin-left: 1%; margin-right: 0.5%;">Total Marks - </label>
                <input type="number" min="5" value="20" id="totalmarks" name="totalmarks" required style="max-width: 40px;"/>

                <label style="margin-left: 1%; margin-right: 0.5%;">Total Marks Selected - </label><input type="number" id="sel_marks" name="sel_marks" readonly style="max-width: 40px;"/>
                <!--<input class="contact" type="number" readonly style="margin-right: 1%; margin-left: 0.5%; max-width: 2%" value="20"></input>-->

                <label style="margin-left: 1%;">Date - </label><input type="date" id="date" name="date" required style="max-width: 130px;"/>
                <label style="margin-left: 1%;">Time - </label><select id="time" style="margin-right: 1%; margin-left: 0.5%; max-width: 150px" class="select-style" name="time">
                    <option value="10:15 AM to 10:45 AM">10:15 AM to 10:45 AM</option>
                    <option value="03:15 PM to 03:45 PM">03:15 PM to 03:45 PM</option>
                </select>

                <input style="margin-right: 1%; margin-left: 1%;" class="buttom2" name="submit" value="SUBMIT" type="submit"/>

                <a href="Add_Question.jsp?type=Question_Type" ><input style="margin-right: 1%; margin-left: 1%;" class="buttom2" name="add" value="+ ADD QUESTIONS" type="button"/>
                </a>

                <hr align="center"/>

            </div>
            <div style="margin-left: 0.5%; margin-top: 12.5%; margin-right: 0.5%; margin-bottom: 0.5%; background-image: url(images/bgnoise_lg.png);">

                <table border="1" align="center">
                    <tr align="center">
                        <td style="padding: 10px;">
                            <label>Selection</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Sr. No.</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Question</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Option A</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Option B</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Option C</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Option D</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Answer</label>
                        </td>
                        <td style="padding: 10px;">
                            <label>Marks</label>
                        </td>

                    </tr>
                    <%
                            //if (!(subj.isEmpty())) {                            
                            //     if (true) {
                            if (!((subj.contentEquals("empty")))) {
                                try {
                                    Class.forName("com.mysql.jdbc.Driver");

                                    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/craft_the_exam", "root", "");
                                    //Connection con2 = (Connection) DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12225357", "sql12225357", "LJmaUbKvFV");
                                    Statement st = (Statement) con.createStatement();
                                    String sql = "SELECT `Que_ID`, `Question`, `Option_1`, `Option_2`, `Option_3`, `Option_4`, `Answer`, `Is_Question_Image`, `image`, `Marks` FROM `questions` WHERE `Subj_ID` = " + subj;
                                    //String sql = "SELECT `Que_ID`, `Question`, `Option_1`, `Option_2`, `Option_3`, `Option_4`, `Answer`, `Is_Question_Image`, `image`, `Marks` FROM `questions` WHERE `Subj_ID` = 2170709";
                                    int i = 1;
                                    ResultSet rs = st.executeQuery(sql);
                                    session.setAttribute("subj", subj);
                                    while (rs.next()) {
                                        out.println("<tr align=\"justify\"><td style=\"padding:10px\" align=\"center\">"
                                                + "<input type=\"checkbox\" name=\"selection\" onchange=\"checkTotal()\" value=\"" + rs.getInt(1) + "\"></td>"
                                                + "<input type=\"hidden\" id=\"" + rs.getInt(1) + "\" value=\"" + rs.getString(10) + "\"/>"
                                                + "<td style=\"padding:10px\" align=\"center\">" + i + "</td>"
                                                + "<td style=\"padding:10px\">" + rs.getString(2));
                                        if (rs.getString(8).contentEquals("YES")) {
                                            Blob image = rs.getBlob(9);
                                            //String img_name=String.valueOf(rs.getInt(1));
                                            out.println("<br/>");
                                            byte[] imgData = image.getBytes(1, (int) image.length());
                                            String filePath = config.getServletContext().getRealPath("/") + "images\\" + subj + "_" + String.valueOf(rs.getInt(1)) + ".jpg";
                                            //File savedFile= new File(filePath);
                                            FileOutputStream os = new FileOutputStream(filePath);
                                            //for(int img=0;img<imgData.length;img++)
                                            os.write(imgData);
                                            //File savedFile = new File(config.getServletContext().getRealPath("/")+"Example\\image-folder\\"+itemName); 
                                            /*response.setContentType("image/jpg");
                                        OutputStream o = response.getOutputStream();
                                        o.write(imgData);
                                        o.flush();
                                        o.close();*/
                                            os.close();
                                            //out.println("YES IMAGE HERE");
                                            out.println("<br/><img src=\"images/" + subj + "_" + String.valueOf(rs.getInt(1)) + ".jpg\" width=\"256\" height=\"128\" alt=\"" + rs.getInt(1) + "\"/>");
                                        }
                                        out.println("</td><td style=\"padding:10px\">" + rs.getString(3) + "</td>"
                                                + "<td style=\"padding:10px\">" + rs.getString(4) + "</td>"
                                                + "<td style=\"padding:10px\">" + rs.getString(5) + "</td>"
                                                + "<td style=\"padding:10px\">" + rs.getString(6) + "</td>"
                                                + "<td style=\"padding:10px\">" + rs.getString(7) + "</td>"
                                                + "<td style=\"padding:10px\">" + rs.getString(10) + "</td>");
                                        i++;
                                        out.println("</tr>");
                                    }
                                } catch (Exception ex) {
                                    out.println(ex);
                                }
                            } else {
                                //if subj value is empty
                                out.println("Subject empty");
                            }
                        } else {
                            //if session is not new
                            request.setAttribute("data", "Something went wrong!<br/>Please try again.");
                            request.getRequestDispatcher("/Log_IN").forward(request, response);
                        }
                    %>
                </table>
            </div>
        </form>


    </body>
</html>
