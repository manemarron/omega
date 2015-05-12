/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db.DatabaseAPI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import users_connection.User;
import users_connection.UsersConnection;

/**
 *
 * @author andermurillo
 */
@WebService(serviceName = "DatabaseWS")
@Stateless
public class DatabaseWS {

    @WebMethod(operationName = "createDatabase")
    public boolean createDatabase(@WebParam(name = "dbName") String dbName,
            @WebParam(name = "user") String user,
            @WebParam(name = "pw") String pw,
            @WebParam(name = "user_id") int user_id) {
        User userObject = getRequestUser(user_id);
        boolean success = false;
        if (userObject != null) {
            if (userObject.getDbName() == null) {
                DatabaseAPI dbApi = new DatabaseAPI();
                success = dbApi.createDatabase(dbName, user, pw);
                if (success) {
                    success = setDatabaseToUser(user_id, dbName, user, pw);
                }
            } else {
                System.out.println("El usuario ya tiene BD");
            }
        } else {
            System.err.println("userObject es nulo");
        }
        return success;
    }

    @WebMethod(operationName = "deleteDatabase")
    public boolean deleteDatabase(@WebParam(name = "dbName") String dbName,
            @WebParam(name = "user") String user,
            @WebParam(name = "pw") String pw) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.deleteDatabase(dbName, user, pw);
    }

    @WebMethod(operationName = "openConnection")
    public boolean openConnection(@WebParam(name = "dbName") String dbName,
            @WebParam(name = "user") String user,
            @WebParam(name = "pw") String pw) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.openConnection(dbName, user, pw);
    }

    @WebMethod(operationName = "closeConnection")
    public boolean closeConnection(@WebParam(name = "dbName") String dbName,
            @WebParam(name = "user") String user,
            @WebParam(name = "pw") String pw) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.closeConnection(dbName, user, pw);
    }

    @WebMethod(operationName = "createTable")
    public boolean createTable(@WebParam(name = "tableName") String dbName,
            @WebParam(name = "columnNames") String[] columnNames,
            @WebParam(name = "columnTypes") String[] columnTypes,
            @WebParam(name = "nulls") String[] nulls,
            @WebParam(name = "pk") String[] pk) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.createTable(dbName, columnNames, columnTypes, nulls, pk);
    }

    @WebMethod(operationName = "deleteTable")
    public boolean deleteTable(@WebParam(name = "tableName") String dbName) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.deleteTable(dbName);
    }

    @WebMethod(operationName = "addRow")
    public boolean addRow(@WebParam(name = "tableName") String dbName,
            @WebParam(name = "values") String[] values) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.addRow(dbName, values);
    }

    @WebMethod(operationName = "deleteRow")
    public boolean deleteRow(@WebParam(name = "tableName") String dbName,
            @WebParam(name = "columnNames") String[] columnNames,
            @WebParam(name = "values") String[] values) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.deleteRow(dbName, columnNames, values);
    }

    @WebMethod(operationName = "select")
    public ArrayList<ArrayList<String>> select(
            @WebParam(name = "tableName") String dbName,
            @WebParam(name = "selectColumnNames") String[] selectColumnNames,
            @WebParam(name = "whereColumnNames") String[] whereColumnNames,
            @WebParam(name = "values") String[] values) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.select(dbName, selectColumnNames, whereColumnNames, values);
    }

    private User getRequestUser(int user_id) {
        User userObject;
        UsersConnection connection = new UsersConnection();
        try {
            connection.setConnection();
            String query = "SELECT * FROM USERS WHERE ID=?";
            List<?> params = Arrays.asList(new Integer[]{user_id});
            ResultSet rs = connection.getResultSet(query, params);
            if (rs.next()) {
                userObject = User.setFromResultSet(rs);
            } else {
                userObject = null;
            }
        } catch (SQLException sqlEx) {
            userObject = null;
            System.err.println(sqlEx.getMessage());
        } finally {
            try {
                connection.closeConnection();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return userObject;
    }

    private boolean setDatabaseToUser(int user_id, String dbName, String user, String pw) {
        UsersConnection connection = new UsersConnection();
        boolean success = false;
        try {
            connection.setConnection();
            String query = "UPDATE USERS SET DB_NAME=?,DB_USER=?,DB_PASSWORD=? WHERE ID=?";
            List<?> params = Arrays.asList(new Object[]{dbName, user, pw, user_id});
            if (connection.executeQuery(query, params) > 0) {
                success = true;
            }
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx.getMessage());
        } finally {
            try {
                connection.closeConnection();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return success;
    }
}
