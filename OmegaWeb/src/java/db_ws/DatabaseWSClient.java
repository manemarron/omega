/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db_ws_client.DatabaseWS_Service;
import db_ws_client.DatabaseWS;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author manemarron
 */
@Path("api")
public class DatabaseWSClient {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DatabaseWSClient
     */
    public DatabaseWSClient() {
    }

    /**
     * Retrieves representation of an instance of db_rest_ws.DatabaseWSClient
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        //TODO return proper representation object
        return "hola";
    }

    @PUT
    @Consumes("application/json")
    @Path("createDatabase")
    public void createDatabase(CreateDB createDB) {
        String dbName = createDB.getDbName();
        String user = createDB.getUser();
        String pw = createDB.getPw();
        
        DatabaseWS_Service service = new DatabaseWS_Service();
        DatabaseWS port = service.getDatabaseWSPort();
        port.createDatabase(dbName, user, pw);
    }
}
