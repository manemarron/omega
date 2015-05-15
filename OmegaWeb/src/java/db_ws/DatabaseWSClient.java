/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_ws;

import db_ws.models.AddRowModel;
import db_ws.models.AddTableModel;
import db_ws.models.AllTablesModel;
import db_ws.models.CreateDBModel;
import db_ws.models.DeleteRowModel;
import db_ws.models.SelectRequestModel;

import db_ws_client.DatabaseWS_Service;
import db_ws_client.DatabaseWS;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import users_connection.User;
import users_connection.UsersConnection;

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
     *
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
     * @param request
     * @return
     * @throws Exception
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Path("createDatabase")
    public CreateDBModel createDatabase(CreateDBModel params, @Context HttpServletRequest request) throws Exception {
        User usr = (User) request.getSession().getAttribute("user");
        UsersConnection usersConnection = new UsersConnection();
        if (usr != null) {
            String dbName = params.getDbName();
            String user = params.getUser();
            String pw = params.getPw();

            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            if (!port.createDatabase(dbName, user, pw)) {
                throw new Exception("API ERROR");
            }
            if(!usersConnection.setDatabaseToUser(usr.getId(), dbName, user, pw)){
                throw new Exception("API ERROR");
            }   
            return params;
        }
        return null;
    }

    /**
     *
     * @param request
     * @throws Exception
     */
    @DELETE
    @Path("deleteDatabase")
    public void deleteDatabase(@Context HttpServletRequest request) throws Exception {
        User usr = (User) request.getSession().getAttribute("user");
        UsersConnection usersConnection = new UsersConnection();
        if (usr != null) {
            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            if (!port.deleteDatabase(usr.getDbName(), usr.getDbUser(), usr.getDbPw())) {
                throw new Exception("API ERROR");
            }
            if(!usersConnection.removeDatabaseFromUser(usr.getId())){
                throw new Exception("API ERROR");
            }
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("getTables")
    public AllTablesModel getTables(@Context HttpServletRequest request) {
        User usr = (User) request.getSession().getAttribute("user");
        if (usr != null) {

            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            AllTablesModel model = new AllTablesModel();
            model.setTables((ArrayList<String>) port.getTables(usr.getDbName(), usr.getDbUser(), usr.getDbPw()));
            return model;
        }
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("addTable")
    public void addTable(AddTableModel params, @Context HttpServletRequest request) throws Exception {
        User usr = (User) request.getSession().getAttribute("user");
        if (usr != null) {
            String dbName = usr.getDbName();
            String user = usr.getDbUser();
            String pw = usr.getDbPw();
            String table_name = params.getTable_name();
            ArrayList<String> column_names = params.getColumnNames();
            ArrayList<String> column_types = params.getColumnTypes();
            ArrayList<String> column_nulls = params.getNulls();
            ArrayList<String> column_pks = params.getPks();
            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            if (!port.createTable(dbName,user,pw,table_name, column_names, column_types, column_nulls, column_pks)) {
                throw new Exception("API ERROR");
            }
        }
    }
    
    @DELETE
    @Path("deleteTable/{table}")
    public void deleteTable(@PathParam("table") String table,@Context HttpServletRequest request) throws Exception{
        User usr = (User) request.getSession().getAttribute("user");
        if (usr != null) {
            String dbName = usr.getDbName();
            String user = usr.getDbUser();
            String pw = usr.getDbPw();
            String table_name = table;
            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            if (!port.deleteTable(dbName,user,pw,table_name)) {
                throw new Exception("API ERROR");
            }
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Path("addRow")
    public void addRow(AddRowModel params,@Context HttpServletRequest request) throws Exception{
        User usr = (User) request.getSession().getAttribute("user");
        if (usr != null) {
            String dbName = usr.getDbName();
            String user = usr.getDbUser();
            String pw = usr.getDbPw();
            String table_name = null;
            ArrayList<String> list_values = null;
            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            if (!port.addRow(dbName,user,pw,table_name,list_values)) {
                throw new Exception("API ERROR");
            }
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Path("deleteRow")
    public void deleteRow(DeleteRowModel params,@Context HttpServletRequest request) throws Exception{
        User usr = (User) request.getSession().getAttribute("user");
        if (usr != null) {
            String dbName = usr.getDbName();
            String user = usr.getDbUser();
            String pw = usr.getDbPw();
            String table_name = null;
            ArrayList<String> column_names = null;
            ArrayList<String> list_values = null;
            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            if (!port.deleteRow(dbName,user,pw,table_name,column_names,list_values)) {
                throw new Exception("API ERROR");
            }
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("select")
    public String select(SelectRequestModel params, @Context HttpServletRequest request){
        User usr = (User) request.getSession().getAttribute("user");
        if (usr != null) {
            String dbName = usr.getDbName();
            String user = usr.getDbUser();
            String pw = usr.getDbPw();
            String table_name = params.getTableName();
            List<String> selectColumnNames = params.getSelectColumnNames();
            List<String> whereColumnNames = params.getWhereColumnNames();
            List<String> values = params.getValues();
            DatabaseWS_Service service = new DatabaseWS_Service();
            DatabaseWS port = service.getDatabaseWSPort();
            port.select(dbName,user,pw,table_name,selectColumnNames,whereColumnNames,values);
        }
        return null;
    }
}
