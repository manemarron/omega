<%-- 
    Document   : index
    Created on : Apr 21, 2015, 7:38:51 PM
    Author     : manemarron
--%>

<%@page import="users_connection.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <%=((User)session.getAttribute("user")).getFirstName() %>!</h1>
    </body>
</html>
