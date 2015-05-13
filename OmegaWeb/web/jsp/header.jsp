<%-- 
    Document   : header
    Created on : May 11, 2015, 12:12:00 AM
    Author     : manemarron
--%>
<%@page import="users_connection.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav id="headerDiv" >
    <h1>DataWebWizard</h1>
    <div class="account">
        Bienvenido, <%=((User)session.getAttribute("user")).getFirstName() %>
        &nbsp;&nbsp;&nbsp;
        <a href="<%= request.getContextPath() %>/logout">logout</a>
    </div>
</nav>
