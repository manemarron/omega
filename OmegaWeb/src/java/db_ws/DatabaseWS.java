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
}
