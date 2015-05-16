<%-- 
    Document   : view_table
    Created on : May 14, 2015, 2:11:04 PM
    Author     : manemarron
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="db.DatabaseAPI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DatabaseAPI db = new DatabaseAPI();
    User usr = (User) session.getAttribute("user");
    String dbName = usr.getDbName();
    String user = usr.getDbUser();
    String pw = usr.getDbPw();
    String table_name = request.getParameter("table"); 
    ArrayList<ArrayList<String>> columns = db.getColumnsOfTable(dbName, user, pw, table_name);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Table</title>
        <%@include file="jsp/styles_scripts.jsp" %>
        <script type="text/javascript" src="js/view_table.js" ></script>
    </head>
    <body onload="document.indice = 0;getRows('<%= table_name %>');">
        <%@include file="jsp/loading.jsp" %>
        <%@include file="jsp/header.jsp" %>  
        <article id ="table">
            <h2>Tabla: <%= table_name %></h2>
            <table>
                <tr>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Tamaño</th>
                    <th>Null</th>
                </tr>
                <% for(ArrayList<String> column : columns){ %>
                <tr>
                    <% for(String name: column){ %>
                    <td><%= name %></td>
                    <% } %>
                </tr>
                <% } %>
            </table>
            <br/>
            <br/>
            Agregar registro:
            <script>document.columns = [];</script>
            <form onsubmit="return validateAddRow('<%= table_name %>');" action="#" method="POST">
            <% for(ArrayList<String> column : columns){ 
                String name = column.get(0); %>
                <script>document.columns.push('<%= name %>');</script>
                <span id="<%= name %>" >
                <label><%= name %></label>
                <input type="text" name="<%= name %>"/>
                &nbsp;&nbsp;
                </span>
            <% } %>
            <input type="submit" value="Agregar" />
            </form>
            <br/>
            <br/>
            <section>
                <table id="results">
                    <tr>
            <% for(ArrayList<String> column : columns){ 
                String name = column.get(0); %>
                <th><%= name %></th>
            <% } %>
                </tr>
                <tr id="resultsTr">
                  <% for(ArrayList<String> column : columns){ 
                String name = column.get(0); %>
                <td></td>
            <% } %>  
                </tr>
                </table>
                <button onclick="document.indice=0;setCurrentResult();">First</button>
                <button onclick="document.indice = document.indice===0 ? document.indice : document.indice-1;setCurrentResult();">Previous</button>
                <button onclick="document.indice = document.indice===document.valores_tabla.length-1 ? document.indice : document.indice+1;setCurrentResult();">Next</button>
                <button onclick="document.indice=document.valores_tabla.length-1;setCurrentResult();">Last</button>
            </section>
        </article>
    </body>
</html>
