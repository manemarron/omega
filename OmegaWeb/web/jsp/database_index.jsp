<%-- 
    Document   : database_index
    Created on : May 12, 2015, 8:46:53 PM
    Author     : manemarron
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.DatabaseAPI"%>
<%@page import="users_connection.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User usr = (User) session.getAttribute("user");
    String dbName = usr.getDbName() == null ? "" : usr.getDbName();
    String dbUsr = usr.getDbUser() == null ? "" : usr.getDbUser();
    String dbPw = usr.getDbPw() == null ? "" : usr.getDbPw();
    System.out.println(dbName);
    System.out.println(dbUsr);
    System.out.println(dbPw);
    
    ArrayList tables = new ArrayList();
    if (usr.getDbName() != null) {
        DatabaseAPI db = new DatabaseAPI();
        db.openConnection(dbName, dbUsr, dbPw);
        tables = db.getAllTablesOf(dbName, dbUsr, dbPw);
        db.closeConnection(dbName, dbUsr, dbPw);
    }
%>
<button id="db_button" onclick="callDeleteDatabase();">
    Eliminar la base de datos
</button>
<h3 id="db_name"><%= dbName%></h3>
<p><b>username</b>: <span id="username_span" ><%= dbUsr%></span></p>
<p><b>password</b>: <span id="password_span" ><%= dbPw%></span></p>
<h4 style="margin-top:40px">Tablas:</h4>
<ul id="table_list">
    <%if(tables.size()>0){for(Object table: tables) { %>
    <li><a href="ViewTable?table=<%= table %>"><%= table %></a></li>
    <% } } else {
        out.print("No hay ninguna tabla en la base de datos");
    }%>
</ul>
