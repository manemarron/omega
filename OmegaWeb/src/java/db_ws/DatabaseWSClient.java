/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db_ws_client.DatabaseWS;
import db_ws_client.DatabaseWS_Service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 *
 * @author andermurillo
 */
@Path("db/api")
public class DatabaseWSClient {

//    @Context
//    private UriInfo context;
    public DatabaseWSClient() {

    }

    @GET
    @Produces("text/plain")
    @Path("createDatabase")
    public String createDatabase(@QueryParam("dbName") String dbName,
                                 @QueryParam("user") String user,
                                 @QueryParam("pw") String pw) {
        DatabaseWS_Service service = new DatabaseWS_Service();
        DatabaseWS port = service.getDatabaseWSPort();
        return "" + port.createDatabase(dbName, user, pw);
    }

}
