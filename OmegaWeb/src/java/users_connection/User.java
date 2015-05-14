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
    private String dbUser;
    private String dbPw;
    
    public static User setFromResultSet(ResultSet rs) throws SQLException{
        User user = new User();
        user.id = rs.getInt("ID");
        user.username = rs.getString("USERNAME");
        user.firstName = rs.getString("FIRST_NAME");
        user.lastName = rs.getString("LAST_NAME");
        user.dbName = rs.getString("DB_NAME");
        user.dbUser = rs.getString("DB_USER");
        user.dbPw = rs.getString("DB_PASSWORD");
        return user;
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

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPw() {
        return dbPw;
    }
    
    
}
