/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db_ws.models.CreateDBModel;
import db_ws.models.DeleteDBModel;

import db_ws_client.DatabaseWS_Service;
import db_ws_client.DatabaseWS;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

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
        return "DataWebWizard: REST API para interacción con bases de datos";
    }

    /**
     *
     * @param params
     * @throws Exception
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("createDatabase")
    public void createDatabase(CreateDBModel params) throws Exception {
        String dbName = params.getDbName();
        String user = params.getUser();
        String pw = params.getPw();
        int user_id = params.getUser_id();
        
        DatabaseWS_Service service = new DatabaseWS_Service();
        DatabaseWS port = service.getDatabaseWSPort();
        if(!port.createDatabase(dbName, user, pw, user_id))
            throw new Exception("API ERROR");
        
    }
    
    /**
     *
     * @param user_id
     * @throws Exception
     */
    @DELETE
    @Path("deleteDatabase/{user_id}")
    public void deleteDatabase(@PathParam("user_id") int user_id) throws Exception {       
        DatabaseWS_Service service = new DatabaseWS_Service();
        DatabaseWS port = service.getDatabaseWSPort();
        if(!port.deleteDatabase(user_id))
            throw new Exception("API ERROR");
        
    }
}
