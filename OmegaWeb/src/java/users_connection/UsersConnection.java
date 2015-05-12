/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

    public ResultSet getResultSet(String query, List<?> params) throws SQLException {
        ResultSet result;
        if (params == null) {
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
        } else {
            PreparedStatement statement = con.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                try {
                    statement.setInt(i + 1, Integer.parseInt(params.get(i).toString()));
                } catch (NumberFormatException nfe) {
                    statement.setString(i + 1, (String) params.get(i));
                } catch(Exception e){
                    statement.setString(i + 1, null);
                }
            }
            result = statement.executeQuery();
        }
        return result;
    }

    public int executeQuery(String query, List<?> params) throws SQLException {
        PreparedStatement statement = con.prepareStatement(query);
        for (int i = 0; i < params.size(); i++) {
            try {
                statement.setInt(i + 1, Integer.parseInt(params.get(i).toString()));
            } catch (NumberFormatException nfe) {
                statement.setString(i + 1, (String) params.get(i));
            }
        }
        return statement.executeUpdate();
    }

    public void closeConnection() throws SQLException {
        if (con != null) {
            con.commit();
            con.close();
            con = null;
        }
    }
}
