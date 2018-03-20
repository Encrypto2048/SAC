/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processchecker;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jednipat.pong
 */
public class connect_db {
    
public static void main(String[] args) {         
         }

public Connection connect_db() {
            try
    {
      //create a mysql database connection
      String myDriver = "com.mysql.jdbc.Driver";
      //String myDriver = "org.apache.derby.jdbc.EmbeddedDriver";
      //String myUrl = "jdbc:mysql://192.168.1.100:3306/producttracker";
      String myUrl = "jdbc:mysql://localhost:3306/producttracker";
      //String myUrl = "jdbc:mysql://192.168.1.88:3306/producttracker";
      Class.forName(myDriver);
     // Connection conn = DriverManager.getConnection(myUrl, "root", "toor");
     Connection conn = DriverManager.getConnection(myUrl, "tsd", "toor");
     // Connection conn = DriverManager .getConnection("jdbc:derby:"+ "jjphone;create=true");
     //Statement smt1 = conn.createStatement();
    // int a = 0;
     //a = smt1.executeUpdate("CREATE TABLE USER1 (username VARCHAR(26),password  VARCHAR(26),role  VARCHAR(26))");
      return conn;
      
    }
     catch(Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }   
    return null;
}


    
}