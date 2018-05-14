<%-- 
    Document   : thanks
    Created on : Mar 29, 2018, 12:25:05 PM
    Author     : Rahil  Dave
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Thanks</title>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="style.css" media="all" />
        <link rel="stylesheet" type="text/css" href="demo.css" media="all" />
        <script type="text/javascript">
            /*history.pushState(null, null, location.href);
            window.onpopstate = function () {
                history.go(1);
            };*/
        </script>
    </head>

    <body>
        <div class="container">
            <header>
                <h1><span>Craft THE Exam</span>Thank You</h1>
            </header>
            <div class="form">
                <form id="form" method="post" action="Log_Out"> 
                    <table border="0" cellpadding="0" style="margin-right: 10%;">
                        <tr>
                            <td valign="top" align="center" colspan="3"><label for="msg" style=" font-family: fantasy; font-size: 30px">Congratulation!!</label></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;&nbsp;&nbsp;</td>
                        </tr>

                        <tr>
                            <td valign="top" align="center" colspan="3" style=" font-family:monospace; font-size: 20px">The Question Paper Is Generated for Download Or Print press the Print button</td>
                        </tr>
                        <tr height="20">
                            <td  align="center" width="33%">
                                <%
                                    out.print("<a target=\"_blank\" href=\"http://localhost:8084/Sign_Log/example/" + session.getId() + ".pdf\">");
                                %>
                                <input class="buttom" name="print" id="print" value="Print" type="button"/></a></td>
                            <td width="33%" align="center">
                                <%--<a href="http://localhost:8084/Sign_Log/Question_Display.jsp">
                                    <input class="buttom" name="home" id="home" value="Go Back" type="button"/></a>--%>
                                </td>
                            <td align="center"><input class="buttom" name="home" id="home" value="LOG OUT" type="submit"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>


    </body>
</html>
