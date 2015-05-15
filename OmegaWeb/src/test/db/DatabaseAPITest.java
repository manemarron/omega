/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author manemarron
 */
public class DatabaseAPITest {
    private static final String TestDBName = "TEST_DB";
    private static final String TestDBUser = "test_user";
    private static final String TestDBPass = "test_pass";
    
    private static final DatabaseAPI TestDBAPI = new DatabaseAPI();
    
    public DatabaseAPITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        TestDBAPI.createDatabase(TestDBName, TestDBUser, TestDBPass);
    }
    
    @AfterClass
    public static void tearDownClass() {
        TestDBAPI.deleteDatabase(TestDBName, TestDBUser, TestDBPass);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDatabase method, of class DatabaseAPI.
     */
    @Test
    public void testCreateOKDatabase() {
        System.out.println("createDatabase");
        String dbName = "prueba_db";
        String user = "prueba_user";
        String pw = "prueba_pass";
        DatabaseAPI instance = new DatabaseAPI();
        boolean expResult = true;
        boolean result = instance.createDatabase(dbName, user, pw);
        assertEquals(expResult, result);
        if(expResult == result){
            instance.deleteDatabase(dbName, user, pw);
        }
    }
    
    /**
     * Test of deleteDatabase method, of class DatabaseAPI.
     */
    @Test
    public void testDeleteExistentDatabase() {
        System.out.println("deleteExistentDatabase");
        String dbName = "prueba_db";
        String user = "prueba_user";
        String pw = "prueba_pass";
        DatabaseAPI instance = new DatabaseAPI();
        instance.createDatabase(dbName, user, pw);
        boolean expResult = true;
        boolean result = instance.deleteDatabase(dbName, user, pw);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDeleteNonExistentDatabase(){
        System.out.println("deleteNonExistentDatabase");
        String dbName = "prueba_db";
        String user = "prueba_user";
        String pw = "prueba_pass";
        DatabaseAPI instance = new DatabaseAPI();
        boolean expResult = false;
        boolean result = instance.deleteDatabase(dbName, user, pw);
        assertEquals(expResult, result);
    }

    /**
     * Test of createTable method, of class DatabaseAPI.
     */
    @Test
    public void testCreateEmptyTable() {
        System.out.println("createTable");
        String tableName = "TEST_TABLE";
        String[] columnNames = new String[]{};
        String[] columnTypes = new String[]{};
        String[] nulls = new String[]{};
        String[] pk = new String[]{};
        boolean expResult = false;
        boolean result = TestDBAPI.createTable(TestDBName, TestDBUser, TestDBPass,tableName, columnNames, columnTypes, nulls, pk);
        TestDBAPI.deleteTable(TestDBName, TestDBUser, TestDBPass,tableName);
        assertEquals(expResult, result);
    }    
    
    @Test
    public void testCreateOKTable() {
        System.out.println("createTable");
        String tableName = "TEST_TABLE";
        String[] columnNames = new String[]{"id"};
        String[] columnTypes = new String[]{"integer"};
        String[] nulls = new String[]{"NOT NULL"};
        String[] pk = new String[]{"id"};
        boolean expResult = true;
        boolean result = TestDBAPI.createTable(TestDBName, TestDBUser, TestDBPass,tableName, columnNames, columnTypes, nulls, pk);
        TestDBAPI.deleteTable(TestDBName, TestDBUser, TestDBPass,tableName);
        assertEquals(expResult, result);
    }  
    
    @Test
    public void testGetAllTablesEmpty(){
        System.out.println("getAllTables: No tables");
        ArrayList<String> tables = TestDBAPI.getAllTablesOf(TestDBName, TestDBUser, TestDBPass);
        ArrayList<String> exp_tables = new ArrayList<>();
        assertEquals(exp_tables, tables);
    }
    
    @Test
    public void testGetColumnsOfTable(){
        System.out.println("getColumns");
        String table_name="TEST_TABLE_TESTS";
        String[] columnNames = new String[]{"id","text"};
        String[] columnTypes = new String[]{"integer","varchar(200)"};
        String[] nulls = new String[]{"NOT NULL",""};
        String[] pk = new String[]{"id"};
        TestDBAPI.createTable(TestDBName, TestDBUser, TestDBPass, table_name, columnNames, columnTypes, nulls, pk);
        List<String> result = TestDBAPI.getColumnsOfTable(TestDBName, TestDBUser, TestDBPass,table_name);
        List<String> expected = Arrays.asList(new String[]{"ID","TEXT"});
        TestDBAPI.deleteTable(TestDBName, TestDBUser, TestDBPass,table_name);
        assertEquals(expected, result);
    }
}
