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
    
    public void setFromResultSet(ResultSet rs) throws SQLException{
        id = rs.getInt("id");
        username = rs.getString("username");
        firstName = rs.getString("first_name");
        lastName = rs.getString("last_name");
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
    
    
}
