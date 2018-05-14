<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Craft THE Exam Sign UP</title>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
        <link rel="stylesheet" type="text/css" href="style.css" media="all" />
        <link rel="stylesheet" type="text/css" href="demo.css" media="all" />
        <script type="text/javascript">
            function validation()
            {
                var contact = document.getElementById('contact');
                var email = document.getElementById('email');
                var pass = document.getElementById('password');
                var repass = document.getElementById('repassword');
                var bmonth = document.getElementById('b_mon');
                var dept = document.getElementById('dept');
                var desg = document.getElementById('desg');
                var filter_contact = /^[6-9]+\d{9}/;
                var filter_email = /^([a-zA-Z0-9_\.\-])+\@(git.org.in)$/;

                if (!filter_email.test(email.value))
                {
                    alert('Enter valid email address');
                    email.focus();
                    return false;
                }

                if (!((pass.value.length) > 7 && (pass.value.length) < 15)) {
                    alert('Password must be between 8 to 14 characters only');
                    pass.focus();
                    return false;
                }

                if (!((pass.value) == (repass.value))) {
                    alert('Password and Confirm Password does not match');
                    pass.focus();
                    return false;
                }

                if ((bmonth.value) == '0') {
                    alert('Birthday Month not selected');
                    bmonth.focus();
                    return false;
                }

                if ((dept.value) == '0_0') {
                    alert('Department not selected');
                    dept.focus();
                    return false;
                }

                if ((desg.value) == '0_0') {
                    alert('Designation not selected');
                    desg.focus();
                    return false;
                }

                if ((contact.value.length) > 0) {
                    if (!filter_contact.test(contact.value))
                    {
                        alert('Enter 10 digit Mobile Number');
                        contact.focus();
                        return false;
                    }
                }

                return true;
            }
            function refresh() {
                var email = document.getElementById('email').value;
                var hid = document.getElementById('hid');

            <%
                /*Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT `ID` FROM `users` WHERE `Email`='mohak@git.org.in'");*/
            %>
            }
        </script>
    </head>
    <%        String data = "error";
        try {
            data = session.getAttribute("error").toString();
        } catch (Exception ex) {
            data = "error";
        }

    %>
    <body>
        <div class="container">
            <header>
                <h1><span>Craft THE Exam</span>Registration</h1>
            </header>
            <div class="form">
                <form id="contactform" method="post" action="Sign_UP_Backend" onsubmit="return validation()">
                    <table border="0" cellpadding="0">
                        <tr>
                            <td width="195" height="40"><label for="name">First Name</label></td>
                        </tr>
                        <tr>
                            <td colspan="3"><input id="fname" name="fname" placeholder="First Name" required="required" type="text"<%                                if (data.contentEquals("error")) {
                                    out.print(" autofocus");
                                }
                                                   %>
                                                   /></td>
                        </tr>
                        <tr>
                            <td height="40"><label for="name">Last Name</label></td>
                        </tr>
                        <tr>
                            <td colspan="3"><input id="lname" name="lname" placeholder="Last Name" required="required" type="text"/></td>
                        </tr>
                        <tr>
                            <td valign="top"><label for="gender">Gender:</label></td>
                            <td colspan="2" align="left"><input name="gend" type="radio" value="Male" required="required" id="gmale"/>
                                <label for="male">&nbsp;&nbsp;Male</label></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="2" align="left"><input name="gend" type="radio" value="Female" required="required" id="gfemale"/>
                                <label for="female">&nbsp;&nbsp;Female</label></td>
                        </tr>
                        <tr>
                            <td height="40"><label for="email">Email</label></td>
                        </tr>
                        <tr>
                            <td colspan="3"><input id="email" name="email" placeholder="example@git.org.in" required="required" type="email"
                                                   <%
                                                       if (!(data.contentEquals("error"))) {
                                                           out.println(" autofocus></td></tr>\n<tr height=\"30\"><td colspan=\"3\"><h3 id=\"hid\"><font color=\"red\">" + data + "</font></h3></td></tr>");
                                                       } else {
                                                           out.println("></td></tr>");
                                                       }
                                                   %>
                                                   <tr>
                                        <td height="40"><label for="password">Enter Password</label></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"><input type="password" id="password" name="password" required="required"></td>
                                    </tr>
                                    <tr>
                                        <td height="40"><label for="repassword">Confirm Password</label></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"><input type="password" id="repassword" name="repassword" required="required"></td>
                                    </tr>
                                    <tr>
                                        <td height="20" valign="bottom"><label>Date of Birth</label></td>
                                    </tr>
                                    <tr>
                                        <td align="center" width="40%"><select id="b_mon" class="select-style" name="bmonth">
                                                <option value="0">Month</option>
                                                <option value="01">January</option>
                                                <option value="02">February</option>
                                                <option value="03">March</option>
                                                <option value="04">April</option>
                                                <option value="05">May</option>
                                                <option value="06">June</option>
                                                <option value="07">July</option>
                                                <option value="08">August</option>
                                                <option value="09">September</option>
                                                <option value="10">October</option>
                                                <option value="11">November</option>
                                                <option value="12">December</option>
                                            </select></td>
                                        <td width="20%" align="center"><input class="birthday" maxlength="2" type="number" min="1" max="31" name="bday"  placeholder="Date" required></td>
                                        <td width="40%" align="center"><input class="birthyear" maxlength="2" name="byear" type="number" min="1965" max="2018" placeholder="Year" required></td>
                                    </tr>
                                    <tr>
                                        <td height="40" valign="middle"><label>Department</label></td>
                                        <td colspan="2"><select class="select-style" id="dept" name="dept" style="max-width: 150px">
                                                <option value="0_0">Select</option>
                                                <option value="7">Computer Engineering</option>
                                                <option value="16">Information Technology</option>
                                                <option value="9">Electrical Engineering</option>
                                                <option value="11">Electronics&nbsp;&amp;&nbsp;Comm. Engineering</option>
                                                <option value="19">Mechanical Engineering</option>
                                                <option value="6">Civil Engineering</option>
                                                <option value="0">Mathematics & Humanities</option>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td valign="middle" height="40"><label>Designation</label></td>
                                        <td colspan="2"><select class="select-style" id="desg" name="desg" style="max-width: 150px">
                                                <option value="0_0">Select</option>
                                                <option value="1">Asst.&nbsp;Professor</option>
                                                <option value="2">Professor</option>
                                                <option value="3">Head of Department</option>
                                            </select></td>
                                    </tr>
                                    <tr>
                                        <td height="40"><label class="contact" for="number">Mobile&nbsp;Number</label></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"><input type="text" name="cont" id="contact" placeholder="10-digit Mobile Number" /></td>
                                    </tr>
                                    <tr height="20">
                                        <td colspan="3" align="center"><input class="buttom" name="submit" id="submit" value="Sign UP" type="submit"/></td>
                                    </tr>
                                    </table>
                                    </form>
                                    </div>
                                    </div>
                                    </body>
                                    </html>