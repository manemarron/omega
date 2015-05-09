/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author manemarron
 */
public class UsersConnection {

    private static final String DB_URL = "jdbc:derby://localhost:1527/OmegaDB";

    private Connection con;

    public void setConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(DB_URL, "root", "root123");
        } catch (ClassNotFoundException ex) {
        }
    }

    public ResultSet getResultSet(String query) throws SQLException {
        Statement statement = con.createStatement();
        return statement.executeQuery(query);
    }

    public void executeQuery(String query) throws SQLException {
        Statement s = con.createStatement();
        s.executeUpdate(query);
    }

    public void closeConnection() throws SQLException {
        if (con != null) {
            con.commit();
            con.close();
            con = null;
        }
    }
}
