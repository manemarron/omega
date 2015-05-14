/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db_ws.models.AddTableModel;
import db_ws.models.AllTablesModel;
import db_ws.models.CreateDBModel;

//import db_ws_client.DatabaseWS_Service;
//import db_ws_client.DatabaseWS;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

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
import users_connection.User;

/**
 * REST Web Service
 *
 * @author manemarron
 */
@Path("api")
public class DatabaseWSClient {

    @Context
    private UriInfo context;
    
    @Context
    private HttpServletRequest request;

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
     * @return 
     * @throws Exception
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("createDatabase")
    public CreateDBModel createDatabase(CreateDBModel params) throws Exception {
        User usr = (User)request.getSession().getAttribute("user");
        
        String dbName = params.getDbName();
        String user = params.getUser();
        String pw = params.getPw();
        int user_id = usr.getId();
        
//        DatabaseWS_Service service = new DatabaseWS_Service();
//        DatabaseWS port = service.getDatabaseWSPort();
//        if(!port.createDatabase(dbName, user, pw, user_id))
//            throw new Exception("API ERROR");
        return params;
    }
    
    /**
     *
     * @throws Exception
     */
    @DELETE
    @Path("deleteDatabase")
    public void deleteDatabase() throws Exception {
        User usr = (User)request.getSession().getAttribute("user");
        int user_id = usr.getId();
//        DatabaseWS_Service service = new DatabaseWS_Service();
//        DatabaseWS port = service.getDatabaseWSPort();
//        if(!port.deleteDatabase(user_id))
//            throw new Exception("API ERROR");
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("getTables")
    public AllTablesModel getTables() {
        User usr = (User)request.getSession().getAttribute("user");
        int user_id = usr.getId();
        
//        DatabaseWS_Service service = new DatabaseWS_Service();
//        DatabaseWS port = service.getDatabaseWSPort();
//        AllTablesModel model = new AllTablesModel();
//        model.setTables((ArrayList<String>) port.getTables(user_id));
//        return model;
        return null;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("addTable")
    public void addTable(AddTableModel table){
        
    }
}
