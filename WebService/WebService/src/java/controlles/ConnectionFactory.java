/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlles;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ConnectionFactory implements Serializable{
 
   private static final String USERNAME = "root";
 
   private static final String PASSWORD = "root";
 
   private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/webservice";
  
   public static Connection createConnectionToMySQL(){
       
       Connection connection = null;
       
       try {
           Class.forName("com.mysql.jdbc.Driver"); 

            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return connection;

   }
}