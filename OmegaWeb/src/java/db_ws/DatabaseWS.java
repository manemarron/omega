/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db.DatabaseAPI;
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
}
