/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db.DatabaseAPI;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author andermurillo
 */
@WebService(serviceName = "DatabaseWS")
public class DatabaseWS {

    @WebMethod(operationName = "createDatabase")
    public boolean createDatabase(@WebParam(name = "dbName") String dbName,
                                  @WebParam(name = "user") String user,
                                  @WebParam(name = "pw") String pw) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.createDatabase(dbName, user, pw);
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
    public boolean createTable(@WebParam(name = "dbName") String dbName,
                               @WebParam(name = "user") String user,
                               @WebParam(name = "pw") String pw,
                               @WebParam(name = "tableName") String tableName,
                               @WebParam(name = "columnNames") String[] columnNames,
                               @WebParam(name = "columnTypes") String[] columnTypes,
                               @WebParam(name = "nulls") String[] nulls,
                               @WebParam(name = "pk") String[] pk) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.createTable(dbName, user, pw, tableName, columnNames, columnTypes, nulls, pk);
    }

    @WebMethod(operationName = "deleteTable")
    public boolean deleteTable(@WebParam(name = "dbName") String dbName,
                               @WebParam(name = "user") String user,
                               @WebParam(name = "pw") String pw,
                               @WebParam(name = "tableName") String tableName) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.deleteTable(dbName, user, pw, tableName);
    }

    @WebMethod(operationName = "addRow")
    public boolean addRow(@WebParam(name = "dbName") String dbName,
                          @WebParam(name = "user") String user,
                          @WebParam(name = "pw") String pw,
                          @WebParam(name = "tableName") String tableName,
                          @WebParam(name = "values") String[] values) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.addRow(dbName, user, pw, tableName, values);
    }

    @WebMethod(operationName = "deleteRow")
    public boolean deleteRow(@WebParam(name = "dbName") String dbName,
                             @WebParam(name = "user") String user,
                             @WebParam(name = "pw") String pw,
                             @WebParam(name = "tableName") String tableName,
                             @WebParam(name = "columnNames") String[] columnNames,
                             @WebParam(name = "values") String[] values) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.deleteRow(dbName, user, pw, tableName, columnNames, values);
    }

    @WebMethod(operationName = "select")
    public ArrayList<ArrayList<String>> select(
            @WebParam(name = "dbName") String dbName,
            @WebParam(name = "user") String user,
            @WebParam(name = "pw") String pw,
            @WebParam(name = "tableName") String tableName,
            @WebParam(name = "selectColumnNames") String[] selectColumnNames,
            @WebParam(name = "whereColumnNames") String[] whereColumnNames,
            @WebParam(name = "values") String[] values) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.select(dbName, user, pw, tableName, selectColumnNames, whereColumnNames, values);
    }
    
    @WebMethod(operationName = "getTables")
    public ArrayList<String> getTables(
            @WebParam(name = "dbName") String dbName,
            @WebParam(name = "user") String user,
            @WebParam(name = "pw") String pw) {
        DatabaseAPI dbApi = new DatabaseAPI();
        return dbApi.getAllTablesOf(dbName, user, pw);
    }
}
