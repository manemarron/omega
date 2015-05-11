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
        <title>Register</title>
        <% if(request.getSession().getAttribute("error") != null) { %>
        <script type="text/javascript">
            alert("<%= request.getSession().getAttribute("error") %>");
        </script>
        <% } 
        request.getSession().removeAttribute("error");%>
    </head>
    <body>
        <h1>Register</h1>
        <form action="<%= request.getContextPath() %>/register_control" method="post">
            First name: <input type="text" name="first_name" value="" /><br/>
            Last name:  <input type="text" name="last_name" value="" /><br/>
            Username:   <input type="text" name="username" value="" /><br/>
            Password:   <input type="password" name="password" /><br/>
            Confirm password: <input type="password" name="confirm_password" /><br/>
            <input type="submit" value="Registrar" name="type" />            
        </form>
            <p>
                Si ya tienes cuenta, <a href="login" >haz clic aquí</a> para entrar
            </p>
    </body>
</html>
