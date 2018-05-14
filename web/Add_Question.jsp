<%-- 
    Document   : Add_Question
    Created on : Mar 28, 2018, 3:18:33 PM
    Author     : Rahil  Dave
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Add Question</title>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="style.css" media="all" />
        <link rel="stylesheet" type="text/css" href="demo.css" media="all" />

        <script type="text/javascript">
            function refresh() {
                var type = document.getElementById("quetype");

                var url1 = "http://localhost:8084/Sign_Log/Add_Question.jsp?type=" + type.options[type.selectedIndex].value;
                //var url2 = "&img=" + img.options[img.selectedIndex].value;
                var url2 = "";
                var url = url1.concat(url2);
                location.href = url
                /*if ((type.value) == '2') {
                 optdiv.hidden = false;
                 }*/
            }
            function refresh2() {
                var img = document.getElementById("isqueimg");
                var imgfile = document.getElementById("pic");
                if ((img.options[img.selectedIndex].value) == 'NO')
                    imgfile.disabled = true;
                else
                    imgfile.disabled = false;
            }

            function refresh3(oInput) {
                var _validFileExtensions = [".png"];
                if (oInput.type == "file") {
                    var sFileName = oInput.value;
                    if (sFileName.length > 0) {
                        var blnValid = false;
                        for (var j = 0; j < _validFileExtensions.length; j++) {
                            var sCurExtension = _validFileExtensions[j];
                            if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                                blnValid = true;
                                break;
                            }
                        }

                        if (!blnValid) {
                            alert("Sorry, " + sFileName + " is invalid, allowed extension is: " + _validFileExtensions.join(", "));
                            oInput.value = "";
                            return false;
                        }
                    }
                }
                return true;
            }
        </script>
    </head>
    <body>
        <%
            if (!(session.isNew())) {
                //if (true) {
                String data = (String) session.getAttribute("data"), errdata = null;
                String f_name = (String) session.getAttribute("f_name");
                String type, img;
                try {
                    type = request.getParameter("type");
                    //img = Integer.parseInt(request.getParameter("img"));
                } catch (Exception ex) {
                    type = "Question_Type";
                    //img = 0;
                }
                try {
                    errdata = (request.getAttribute("data")).toString();
                } catch (Exception ex) {
                }

        %>
        <div class="container">
            <header>
                <h1><span>Craft THE Exam</span>Add Question</h1>
            </header>

            <div class="form">
                <form id="form" method="post" action="Add_Question_Backend" enctype="multipart/form-data">
                    <table border="0" cellpadding="0">
                        <tr>
                            <td valign="middle" align="right" style="width: 130px;"><label for="quetype">Question Type:</label></td>
                            <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                            <td align="left">
                                <select class="select-style" id="quetype" name="quetype" onchange="refresh()" autofocus>
                                    <option value="Question_Type"
                                            <%                                                if (type.contains("Question_Type")) {
                                                    out.print(" selected");
                                                }
                                                out.println(">Question Type</option>");
                                            %>
                                            <option value="MCQ_Based"
                                            <%
                                                if (type.equals("MCQ_Based")) {
                                                    out.print(" selected");
                                                }
                                                out.println(">MCQ Based</option>");
                                            %>
                                            <option value="Only_Question"
                                                <%
                                                    if (type.equals("Only_Question")) {
                                                        out.print(" selected");
                                                    }
                                                    out.println(">Only Question</option>");
                                                %>
                                                </select>
                                                </td>
                                                </tr>
                                                <%
                                                    if (!type.equals("Question_Type")) {
                                                %>
                                                <tr>
                                                    <td valign="middle" align="right"><label for="que">Question:</label></td>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    <td  style="padding-bottom: 10px;">
                                                        <textarea style="resize: none;" rows="5" cols="35" name="question" id="question" form="form" required="required" placeholder="Enter Question Here..."></textarea>
                                                    </td>
                                                </tr>
                                                <%
                                                    if (type.equals("MCQ_Based")) {
                                                        out.println("<tr><td valign=\"top\" align=\"right\" ><label for=\"opt1\">Option 1:</label></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name=\"opt1\" id=\"opt1\" placeholder=\"Enter Option 1\" style=\"max-width: 50%;\"></td></tr><tr><td valign=\"top\" align=\"right\" ><label for=\"opt2\">Option 2:</label></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name=\"opt2\" id=\"opt2\" placeholder=\"Enter Option 2\" style=\"max-width: 50%;\"></td></tr><tr><td valign=\"top\" align=\"right\"><label for=\"opt3\">Option 3:</label></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name=\"opt3\" id=\"opt3\" placeholder=\"Enter Option 3\" style=\"max-width: 50%;\"></td></tr><tr><td valign=\"top\" align=\"right\"><label for=\"opt4\">Option 4:</label></td><td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type=\"text\" name=\"opt4\" id=\"opt4\" placeholder=\"Enter Option 4\" style=\"max-width: 50%;\"></td></tr>");
                                                    }
                                                %>
                                                <tr>
                                                    <td valign="top" align="right"><label for="ans">Answer:</label></td>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    <td>
                                                        <input type="text" name="ans" id="ans" placeholder="Answer" style="max-width: 50%;" required/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" align="right" style="min-width: 130px"><label for="marks">Marks:</label></td>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    <td align="left" style="vertical-align: middle; padding: 2px 0px 10px 10px;" >
                                                        <input name="marks" type="radio" value="01" required="required" id="marks"/>
                                                        <label for="1">&nbsp;&nbsp;1</label>

                                                        <input name="marks" type="radio" value="02" style="margin-left: 25px;" required="required" id="marks"/>
                                                        <label for="2">&nbsp;&nbsp;2</label></td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" align="right" style="min-width: 130px"><label for="queimg">Is Question Image:</label></td>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    <td align="left" style="vertical-align: middle; padding: 2px 0px 10px 10px;">
                                                        <%--  <input name="isqueimg" type="radio" value="yes" required="required" id="isqueimg"/>
                                                          <label for="yes">&nbsp;&nbsp;Yes</label>
                                                          <input name="isqueimg" type="radio" value="no" style="margin-left: 25px;" required="required" id="isqueimg"/>
                                                          <label for="no">&nbsp;&nbsp;No</label> --%>
                                                        <select name="isqueimg" id="isqueimg" class="select-style" onchange="refresh2()">
                                                            <option value="YES">YES</option>
                                                            <option value="NO" selected>NO</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td valign="top" align="right"><label for="image">Select Image:</label></td>
                                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                    <td><input type="file" name="pic" accept="image/*" id="pic" disabled style="max-width: 50%;" required="required" onchange="refresh3(this)"/></td>
                                                </tr>
                                                <%
                                                    if (errdata != null) {
                                                        out.println("<tr height=\"30\"><td colspan=\"3\"><h3><font color=\"red\">" + data + "</font></h3></td></tr>");
                                                    }
                                                %>
                                                <tr height="20">
                                                    <td colspan="3" align="center"><input class="buttom" name="save" id="save" value="Save" type="submit" style="margin-right: 150px"/></td>
                                                </tr>
                                                <%                                                    }
                                                %>
                                                <%
                                                    } else {
                                                        //out.println("session error");
                                                        request.setAttribute("data", "Something went wrong!<br/>Please try again.");
                                                        request.getRequestDispatcher("/Log_IN").forward(request, response);
                                                    }
                                                %>
                                                </table>
                                                </form>
                                                </div>
                                                </body>
                                                </html>
