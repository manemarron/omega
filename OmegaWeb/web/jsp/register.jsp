<%-- 
    Document   : register
    Created on : Apr 20, 2015, 11:55:08 PM
    Author     : manemarron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="<%= request.getContextPath() %>/register_control" method="post">
            first name: <input type="text" name="first_name" value="" /><br/>
            last name:  <input type="text" name="last_name" value="" /><br/>
            username:   <input type="text" name="username" value="" /><br/>
            password:   <input type="password" name="password" /><br/>
            confirm password: <input type="password" name="confirm_password" /><br/>
            <input type="submit" value="Registrar" name="type" />            
        </form>
    </body>
</html>
