/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
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
    
    public DatabaseAPITest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
//    @Test
//    public void testCreateDatabase() {
//        System.out.println("createDatabase");
//        String dbName = "test_db";
//        String user = "test_user";
//        String pw = "test_pass";
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = true;
//        boolean result = instance.createDatabase(dbName, user, pw);
//        assertEquals(expResult, result);
//    }
    
    @Test
    public void testDatabaseCreated(){
        System.out.println("DatabaseCreated");
        String dbName = "test_db";
        String user = "test_user";
        String pw = "test_pass";
        DatabaseAPI instance = new DatabaseAPI();
        boolean exp_db_created = true;
        boolean db_created = instance.openConnection(dbName, user, pw);
        assertEquals(exp_db_created, db_created);
    }
//    
//    /**
//     * Test of deleteDatabase method, of class DatabaseAPI.
//     */
//    @Test
//    public void testDeleteDatabase() {
//        System.out.println("deleteDatabase");
//        String dbName = "test_db";
//        String user = "test_user";
//        String pw = "test_pass";
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.deleteDatabase(dbName, user, pw);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of openConnection method, of class DatabaseAPI.
//     */
//    @Test
//    public void testOpenConnection() {
//        System.out.println("openConnection");
//        String dbName = "";
//        String user = "";
//        String pw = "";
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.openConnection(dbName, user, pw);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of closeConnection method, of class DatabaseAPI.
//     */
//    @Test
//    public void testCloseConnection() {
//        System.out.println("closeConnection");
//        String dbName = "";
//        String user = "";
//        String pw = "";
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.closeConnection(dbName, user, pw);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createTable method, of class DatabaseAPI.
//     */
//    @Test
//    public void testCreateTable() {
//        System.out.println("createTable");
//        String tableName = "";
//        String[] columnNames = null;
//        String[] columnTypes = null;
//        String[] nulls = null;
//        String[] pk = null;
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.createTable(tableName, columnNames, columnTypes, nulls, pk);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteTable method, of class DatabaseAPI.
//     */
//    @Test
//    public void testDeleteTable() {
//        System.out.println("deleteTable");
//        String tableName = "";
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.deleteTable(tableName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of alterTable method, of class DatabaseAPI.
//     */
//    @Test
//    public void testAlterTable() {
//        System.out.println("alterTable");
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.alterTable();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addRow method, of class DatabaseAPI.
//     */
//    @Test
//    public void testAddRow() {
//        System.out.println("addRow");
//        String tableName = "";
//        String[] values = null;
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.addRow(tableName, values);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deleteRow method, of class DatabaseAPI.
//     */
//    @Test
//    public void testDeleteRow() {
//        System.out.println("deleteRow");
//        String tableName = "";
//        String[] columnNames = null;
//        String[] values = null;
//        DatabaseAPI instance = new DatabaseAPI();
//        boolean expResult = false;
//        boolean result = instance.deleteRow(tableName, columnNames, values);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of select method, of class DatabaseAPI.
//     */
//    @Test
//    public void testSelect() {
//        System.out.println("select");
//        String tableName = "";
//        String[] selectColumnNames = null;
//        String[] whereColumnNames = null;
//        String[] values = null;
//        DatabaseAPI instance = new DatabaseAPI();
//        ArrayList<ArrayList<String>> expResult = null;
//        ArrayList<ArrayList<String>> result = instance.select(tableName, selectColumnNames, whereColumnNames, values);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
