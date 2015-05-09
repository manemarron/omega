/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andemurillo
 */
public class DatabaseAPI {
    
    private Connection connection;
    private final boolean DEBUG = false;
    private static String DB_PATH;
    
    public DatabaseAPI(){
        DB_PATH = this.currentDBPath();
    }
    
    public static void main(String args[]){
        
        String[] names = {"COL1","COL2","COL3"};
        String[] types = {"INT","VARCHAR(20)","INT"};
        String[] nulls = {"NOT NULL","","NOT NULL"};
        String[] pk = {"COL1","COL3"};
        String[] values = {"123","HOLA","888"};
        String[] delCols = {"COL2","COL3"};
        String[] delValues = {"ADIOS","33"};
        
        DatabaseAPI api = new DatabaseAPI();
        
        System.out.println("Databases location: "+DB_PATH);      
        
        System.out.println("CREATE DB: "+api.createDatabase("DB_NAME", "root", "admin"));
        System.out.println("OPEN CONNECTON: "+api.openConnection("DB_NAME", "root", "admin"));
        System.out.println("CREATE TABLE: "+api.createTable("T1",names,types,nulls,pk));     
        System.out.println("ADD ROW: "+api.addRow("T1",values));
        System.out.println("DELETE ROW: "+api.deleteRow("T1", delCols, delValues));
        System.out.println("DELETE TABLE: "+api.deleteTable("T1"));
        System.out.println("CLOSE CONNECTION: "+api.closeConnection("DB_NAME", "root", "admin"));
        System.out.println("DELETE DB: "+api.deleteDatabase("DB_NAME", "root", "admin"));
    }
    
    /**
     * Creates a new database with the provided parameters
     * @param dbName : Name of the new database
     * @param user : Username of the database
     * @param pw  : Password for that user
     * @return : True if successfull, False otherwise
     */
    public boolean createDatabase(String dbName, String user, String pw){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                            "jdbc:derby:"+DB_PATH+dbName+";create=true",
                            user,
                            pw);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteDatabase(String dbName, String user, String pw){
        try {
            //There is no drop database command. To drop a database, delete the database
            //directory with operating system commands. The database must not be booted
            //when you remove a database.
            this.closeConnection(dbName,user,pw);
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("rm -r " + DB_PATH + dbName);        
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    /**
     * Creates a connection with the provided parameters
     * @param dbName : Name of the new database
     * @param user : Username of the database
     * @param pw  : Password for that user
     * @return : True if successfull, False otherwise
     */
    public boolean openConnection(String dbName, String user, String pw){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(
                            "jdbc:derby:"+DB_PATH+dbName,
                            user,
                            pw);         
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Closes the connection associated to the provided parameters
     * @param dbName : Name of the new database
     * @param user : Username of the database
     * @param pw  : Password for that user
     * @return : True if successfull, False otherwise
     */
    public boolean closeConnection(String dbName, String user, String pw){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                            "jdbc:derby:"+DB_PATH+dbName+";shutdown=true",
                            user,
                            pw);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Creates a new table on the current database (determined by the connection)
     * by receiving a name and 4 lists:
     * @param tableName : Name of the new table
     * @param columnNames : Names of the columns
     * @param columnTypes : Types of the columns
     * @param nulls : If column can be NULL or not
     * @param pk : The columns to use as primary key
     * @return : True if successfull, False otherwise
     */
    public boolean createTable(String tableName, String[] columnNames, String[] columnTypes, String[] nulls, String[] pk){
        
        StringBuilder cad = new StringBuilder();
        
        cad.append("CREATE TABLE ").append(tableName).append(" (");
        
        for(int i=0;i<columnNames.length;i++){
            cad.append(columnNames[i]).append(" ").append(columnTypes[i]).append(" ").append(nulls[i]).append(", ");
        }
        
        cad.append("PRIMARY KEY (");
        
        for(int i=0;i<pk.length;i++){
            cad.append(pk[i]).append(", ");
        }
        
        String createString = cad.toString();
        createString = createString.substring(0, createString.length()-2) + "))";
        
        try {          
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(createString);
            return true;
        } catch (SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Deletes a table given its name
     * @param tableName : The name of the table
     * @return : True if successfull, False otherwise
     */
    public boolean deleteTable(String tableName){
        try {          
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE " + tableName);
            return true;
        } catch (SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;           
        }
    }
    
    public boolean alterTable(){
        return true;
    }
    
    /**
     * Inserts a new registry into the selected table
     * @param tableName : Name of the table
     * @param values : Values to be inserted
     * @return : True if successfull, False otherwise
     */
    public boolean addRow(String tableName, String[] values){
        
        StringBuilder cad = new StringBuilder();
        cad.append("INSERT INTO ").append(tableName).append(" VALUES (");
        
        for(int i=0;i<values.length;i++){
            cad.append("?,");
        }
        
        String insertString = cad.toString();
        insertString = insertString.substring(0, insertString.length()-1) + ")";
              
        try {
            
            PreparedStatement psInsert = connection.prepareStatement(insertString);
            
            for(int i=0;i<values.length;i++){
                psInsert.setString(i+1, values[i]);
            }
            
            psInsert.executeUpdate();       
            return true;
        } catch (SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Receives the name of a table and deletes all rows satisfying the WHERE clause
     * composed of (ColumnName - Value) elements
     * @param tableName : Name of the table
     * @param columnNames : Names of the columns to compare
     * @param values : Values of the columns being compared
     * @return : True if successfull, False otherwise
     */
    public boolean deleteRow(String tableName, String[] columnNames, String[] values){
        
        StringBuilder cad = new StringBuilder();
        cad.append("DELETE FROM ").append(tableName).append(" WHERE ");
        
        for(int i=0;i<values.length;i++){
            
            try{ // If column has a decimal value
                double valueFloat = Double.parseDouble(values[i]);
                cad.append(columnNames[i]).append(" = ").append(valueFloat).append("");                 
            }
            catch(Exception e1){
                
                try{ // If column has an integer value
                    int valueInt = Integer.parseInt(values[i]);
                    cad.append(columnNames[i]).append(" = ").append(valueInt).append("");     
                }
                catch(Exception e2){ // Column is not a number
                    cad.append(columnNames[i]).append(" = '").append(values[i]).append("'");
                } 
            }
                    
            if(i<values.length-1){ // Appends AND clause for all values except the last one
                cad.append(" AND ");
            }
        }
        
        String deleteString = cad.toString();
        
        try {          
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(deleteString);
            return true;
        } catch (SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**Receives the name of a table and selects all rows satisfying the WHERE clause
     * composed of (ColumnName - Value) elements. 
     * @param tableName : Name of the table
     * @param columnNames : Names of the columns to compare
     * @param values : Values of the columns being compared
     * @return : ResultSet, null if exception
     */
    public ResultSet select(String tableName, String[] columnNames, String[] values){
        
        StringBuilder cad = new StringBuilder();
        cad.append("SELECT FROM ").append(tableName).append(" WHERE ");
        
        for(int i=0;i<values.length;i++){
            
            try{ // If column has a decimal value
                double valueFloat = Double.parseDouble(values[i]);
                cad.append(columnNames[i]).append(" = ").append(valueFloat).append("");                 
            }
            catch(Exception e1){
                
                try{ // If column has an integer value
                    int valueInt = Integer.parseInt(values[i]);
                    cad.append(columnNames[i]).append(" = ").append(valueInt).append("");     
                }
                catch(Exception e2){ // Column is not a number
                    cad.append(columnNames[i]).append(" = '").append(values[i]).append("'");
                } 
            }
                    
            if(i<values.length-1){ // Appends AND clause for all values except the last one
                cad.append(" AND ");
            }
        }
        
        String createString = cad.toString();
        
        try {          
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(createString);
        } catch (SQLException ex) {
            if(DEBUG) Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private String currentDBPath(){       
        
        URL location = this.getClass().getProtectionDomain().getCodeSource()
            .getLocation();
        String path = location.getFile();

        String[] pathSegments = path.split("/");
        StringBuilder cleanPath = new StringBuilder("");
        
        for(String directory : pathSegments){
            if(!directory.equals("build")){
                cleanPath.append(directory).append("/");
            }
            else{
                cleanPath.append("db").append("/");
                break;
            }
        }

        return cleanPath.toString();    
    }
    
}
