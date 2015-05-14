/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andermurillo
 */
public class DatabaseAPI {

    private Connection connection;
    private final boolean DEBUG = true;
    private static String DB_PATH;

    public DatabaseAPI() {
        DB_PATH = this.currentDBPath();
    }

    public static void main(String args[]) {

        String[] names = {"COL1", "COL2", "COL3"};
        String[] snames = {"COL1", "COL3"};
        String[] wnames = {"COL3"};
        String[] types = {"INT", "VARCHAR(20)", "INT"};
        String[] nulls = {"NOT NULL", "", "NOT NULL"};
        String[] pk = {"COL1", "COL3"};
        String[] values = {"123", "HOLA", "888"};
        String[] values2 = {"456", "ADIOS", "888"};
        String[] svalues = {"888"};
        String[] delCols = {"COL2", "COL3"};
        String[] delValues = {"ADIOS", "33"};

        DatabaseAPI api = new DatabaseAPI();

        System.out.println("Databases location: " + DB_PATH);

//        System.out.println("CREATE DB: " + api.createDatabase("DB_NAME", "root", "admin"));
//        System.out.println("OPEN CONNECTON: " + api.openConnection("DB_NAME", "root", "admin"));
//        System.out.println("CREATE TABLE: " + api.createTable("DB_NAME", "root", "admin", "T1", names, types, nulls, pk));
//        System.out.println("ADD ROW: " + api.addRow("DB_NAME", "root", "admin", "T1", values));
//        System.out.println("ADD ROW: " + api.addRow("DB_NAME", "root", "admin", "T1", values2));
//        System.out.println("SELECT: " + api.select("DB_NAME", "root", "admin", "T1", snames, wnames, svalues));
//        System.out.println("DELETE ROW: " + api.deleteRow("DB_NAME", "root", "admin", "T1", delCols, delValues));
//        System.out.println("DELETE TABLE: " + api.deleteTable("DB_NAME", "root", "admin", "T1"));
//        System.out.println("CLOSE CONNECTION: " + api.closeConnection("DB_NAME", "root", "admin"));
//        System.out.println("DELETE DB: " + api.deleteDatabase("DB_NAME", "root", "admin"));
    }

    /**
     * Creates a new database with the provided parameters
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @return : True if successful, False otherwise
     */
    public boolean createDatabase(String dbName, String user, String pw) {
        File db = new File(DB_PATH + dbName);
        if (db.exists()) {
            return false;
        }
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + DB_PATH + dbName + ";create=true",
                    user,
                    pw);
            db = new File(DB_PATH + dbName);
            if (db.exists()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            if (DEBUG) {
                Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    /**
     * Deletes an existing database
     *
     * @param dbName : The name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @return : True if deletion was successful, False otherwise
     */
    public boolean deleteDatabase(String dbName, String user, String pw) {
        try {
            if (connection != null && !connection.isClosed()) {
                this.closeConnection(dbName, user, pw);
            }
            File f = new File(DB_PATH + dbName);
            if (!f.exists()) {
                return false;
            }
            return deleteRecursive(f);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean deleteRecursive(File path) {
        File[] c = path.listFiles();
        for (File file : c) {
            if (file.isDirectory()) {
                deleteRecursive(file);
                file.delete();
            } else {
                file.delete();
            }
        }
        return path.delete();
    }

    /**
     * Creates a connection with the provided parameters
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @return : True if successful, False otherwise
     */
    public boolean openConnection(String dbName, String user, String pw) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + DB_PATH + dbName,
                    user,
                    pw);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            if (DEBUG) {
                Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    /**
     * Closes the connection associated to the provided parameters
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @return : True if successful, False otherwise
     */
    public boolean closeConnection(String dbName, String user, String pw) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(
                    "jdbc:derby:" + DB_PATH + dbName + ";shutdown=true",
                    user,
                    pw);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {

            if (ex.getClass().equals(SQLNonTransientConnectionException.class)) {
                //System.out.println("Successfull shutdown");
                return true;
            } else if (DEBUG) {
                Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    /**
     * Creates a new table on the current database (determined by the
     * connection) by receiving a name and 4 lists:
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @param tableName : Name of the new table
     * @param columnNames : Names of the columns
     * @param columnTypes : Types of the columns
     * @param nulls : If column can be NULL or not
     * @param pk : The columns to use as primary key
     * @return : True if successful, False otherwise
     */
    public boolean createTable(String dbName, String user, String pw,
            String tableName, String[] columnNames,
            String[] columnTypes, String[] nulls,
            String[] pk) {

        if (openConnection(dbName, user, pw)) {

            StringBuilder cad = new StringBuilder();

            cad.append("CREATE TABLE ").append(tableName).append(" (");

            if (columnNames.length > 0) {
                for (int i = 0; i < columnNames.length; i++) {
                    cad.append(columnNames[i]).append(" ").append(columnTypes[i]).append(" ").append(nulls[i]).append(", ");
                }
            }

            if (pk.length > 0) {
                cad.append("PRIMARY KEY (");
                for (int i = 0; i < pk.length; i++) {
                    cad.append(pk[i]);
                    if (i < pk.length - 1) {
                        cad.append(", ");
                    }
                }
                cad.append(")");
            }

            cad.append(")");
            System.out.println(cad.toString());
            try {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(cad.toString());
                closeConnection(dbName, user, pw);
                return true;
            } catch (SQLException ex) {
                if (DEBUG) {
                    Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                closeConnection(dbName, user, pw);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Deletes a table given its name
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @param tableName : The name of the table
     * @return : True if successful, False otherwise
     */
    public boolean deleteTable(String dbName, String user, String pw,
            String tableName) {
        if (openConnection(dbName, user, pw)) {
            try {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("DROP TABLE " + tableName);
                closeConnection(dbName, user, pw);
                return true;
            } catch (SQLException ex) {
                if (DEBUG) {
                    Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                closeConnection(dbName, user, pw);
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean alterTable() {
        return true;
    }

    /**
     * Inserts a new registry into the selected table
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @param tableName : Name of the table
     * @param values : Values to be inserted
     * @return : True if successful, False otherwise
     */
    public boolean addRow(String dbName, String user, String pw,
            String tableName, String[] values) {

        if (openConnection(dbName, user, pw)) {
            StringBuilder cad = new StringBuilder();
            cad.append("INSERT INTO ").append(tableName).append(" VALUES (");

            for (int i = 0; i < values.length; i++) {
                cad.append("?,");
            }

            String insertString = cad.toString();
            insertString = insertString.substring(0, insertString.length() - 1) + ")";

            try {

                PreparedStatement psInsert = connection.prepareStatement(insertString);

                for (int i = 0; i < values.length; i++) {
                    psInsert.setString(i + 1, values[i]);
                }

                psInsert.executeUpdate();
                closeConnection(dbName, user, pw);
                return true;
            } catch (SQLException ex) {
                if (DEBUG) {
                    Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                closeConnection(dbName, user, pw);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Receives the name of a table and deletes all rows satisfying the WHERE
     * clause composed of (ColumnName - Value) elements
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @param tableName : Name of the table
     * @param columnNames : Names of the columns to compare
     * @param values : Values of the columns being compared
     * @return : True if successful, False otherwise
     */
    public boolean deleteRow(String dbName, String user, String pw,
            String tableName, String[] columnNames,
            String[] values) {
        if (openConnection(dbName, user, pw)) {
            StringBuilder cad = new StringBuilder();
            cad.append("DELETE FROM ").append(tableName).append(" WHERE ");

            for (int i = 0; i < values.length; i++) {

                try { // If column has an integer value
                    int valueInt = Integer.parseInt(values[i]);
                    cad.append(columnNames[i]).append(" = ").append(valueInt).append("");
                } catch (Exception e1) {

                    try { // If column has a decimal value
                        double valueFloat = Double.parseDouble(values[i]);
                        cad.append(columnNames[i]).append(" = ").append(valueFloat).append("");
                    } catch (Exception e2) { // Column is not a number
                        cad.append(columnNames[i]).append(" = '").append(values[i]).append("'");
                    }
                }

                if (i < values.length - 1) { // Appends AND clause for all values except the last one
                    cad.append(" AND ");
                }
            }

            String deleteString = cad.toString();

            try {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(deleteString);
                closeConnection(dbName, user, pw);
                return true;
            } catch (SQLException ex) {
                if (DEBUG) {
                    Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                closeConnection(dbName, user, pw);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Receives the name of a table and selects all rows satisfying the WHERE
     * clause composed of (ColumnName - Value) elements.
     *
     * @param dbName : Name of the database
     * @param user : Username of the database
     * @param pw : Password for that user
     * @param tableName : Name of the table
     * @param selectColumnNames : Name of the columns to return
     * @param whereColumnNames : Names of the columns to compare
     * @param values : Values of the columns being compared
     * @return : 2-D ArrayList with results, null if exception
     */
    public ArrayList<ArrayList<String>> select(String dbName, String user,
            String pw, String tableName,
            String[] selectColumnNames,
            String[] whereColumnNames,
            String[] values) {

        if (openConnection(dbName, user, pw)) {
            StringBuilder cad = new StringBuilder();
            cad.append("SELECT ").append(join(selectColumnNames, ",")).append(" FROM ").
                    append(tableName).append(" WHERE ");

            for (int i = 0; i < values.length; i++) {

                try { // If column has an integer value
                    int valueInt = Integer.parseInt(values[i]);
                    cad.append(whereColumnNames[i]).append(" = ").append(valueInt).append("");
                } catch (Exception e1) {

                    try { // If column has a decimal value
                        double valueFloat = Double.parseDouble(values[i]);
                        cad.append(whereColumnNames[i]).append(" = ").append(valueFloat).append("");
                    } catch (Exception e2) { // Column is not a number
                        cad.append(whereColumnNames[i]).append(" = '").append(values[i]).append("'");
                    }
                }

                if (i < values.length - 1) { // Appends AND clause for all values except the last one
                    cad.append(" AND ");
                }
            }

            String createString = cad.toString();

            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(createString);

                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();

                ArrayList<ArrayList<String>> results = new ArrayList();
                while (rs.next()) {
                    ArrayList<String> row = new ArrayList();
                    for (int i = 1; i <= columnsNumber; i++) {
                        row.add(rs.getString(i)); // Index starts at 1
                    }
                    results.add(row);
                }
                closeConnection(dbName, user, pw);
                return results;
            } catch (SQLException ex) {
                if (DEBUG) {
                    Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                closeConnection(dbName, user, pw);
                return null;
            }
        } else {
            return null;
        }
    }

    public ArrayList<String> getAllTablesOf(String dbName, String user, String pw) {
        ArrayList<String> result = new ArrayList<>();
        if (openConnection(dbName, user, pw)) {
            try {
                String query = "select t.tablename from sys.systables t, sys.sysschemas s  \n"
                        + "where t.schemaid = s.schemaid \n"
                        + "and t.tabletype = 'T' \n"
                        + "order by t.tablename";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    result.add(rs.getString("tablename"));
                }
                closeConnection(dbName, user, pw);
            } catch (SQLException ex) {
                if (DEBUG) {
                    Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
                }
                closeConnection(dbName, user, pw);
            }
        }
        return result;
    }

    private String currentDBPath() {

        URL location = this.getClass().getProtectionDomain().getCodeSource()
                .getLocation();
        String path = location.getFile();

        String[] pathSegments = path.split("/");
        StringBuilder cleanPath = new StringBuilder("");

        for (String directory : pathSegments) {
            if (!directory.equals("build")) {
                cleanPath.append(directory).append("/");
            } else {
                cleanPath.append("db").append("/");
                break;
            }
        }
        String s = cleanPath.toString();
        s = s.replaceAll("%20", "\\ ");
        return s;
    }

    private String join(String[] array, String sep) {
        StringBuilder cad = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            cad.append(array[i]).append(sep);
        }
        cad.append(array[array.length - 1]);
        return cad.toString();
    }

}
