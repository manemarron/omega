/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users_connection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author manemarron
 */
public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String dbName;
    
    public void setFromResultSet(ResultSet rs) throws SQLException{
        id = rs.getInt("ID");
        username = rs.getString("USERNAME");
        firstName = rs.getString("FIRST_NAME");
        lastName = rs.getString("LAST_NAME");
        dbName = rs.getString("DB_NAME");
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDbName() {
        return dbName;
    }
    
    
}
