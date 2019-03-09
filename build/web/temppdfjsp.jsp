<%-- 
    Document   : temppdfjsp
    Created on : 24 Mar, 2018, 2:33:28 PM
    Author     : Mohak Chavan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <br/>
        <br/>
        <% String filePath = config.getServletContext().getRealPath("/") + "example\\" 
                + session.getId().trim().toString()
                + ".pdf";
        %>
        <a href="<%=filePath%>?attredirects=0&amp;d=1" rel="nofollow" dir="ltr" target="_blank"" >download</a>
    </body>
</html>
