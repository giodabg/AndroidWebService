/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.giodabg.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Gio
 */
@WebService(serviceName = "WSApplicationTestDB")
public class WSApplicationTestDB {


    final static int TABELLA_OPERAZIONI = 1;
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "somma")
    public int add(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        int ris = num1 + num2;
        return ris;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "radiceQuadrata")
    public double radiceQuadrata(@WebParam(name = "num1") double parameter) {
        //TODO write your implementation code here:
        
        return Math.sqrt(parameter);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getList")
    public ArrayList getList(@WebParam(name = "nome") String nome) {
        if (nome.equals("operazioni")) {
            return gelFromDatabase(TABELLA_OPERAZIONI);
        }
        return null;
    }

    
    private ArrayList gelFromDatabase(int table) {
        ArrayList<String> arraylist = new ArrayList<String>();

        // https://www.tutorialspoint.com/jdbc/jdbc-select-records.htm
        // http://stackoverflow.com/questions/21813069/how-to-solve-java-lang-classnotfoundexception-com-mysql-jdbc-driver

        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/iis_jean_monnet";

        //  Database credentials
        String USER = "root";
        String PASS = "";

        //  Object for connection
        Connection conn = null;
        
        //  Object for SQL query
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "";

            switch (table) {
                case TABELLA_OPERAZIONI:
                    sql = "SELECT * FROM operazioni";
                    break;
                default:
                    break;
            }

            if (sql != null) {
                ResultSet rs = stmt.executeQuery(sql);

                // iterate through the java resultset
                while (rs.next()) {
                    //Retrieve by column name
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");

                    arraylist.add(nome);
                    // debug: print the results
                    System.out.format("%s, %s\n", id, nome);
                }

                rs.close();
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        return arraylist;
    }
}
