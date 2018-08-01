/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Imran
 */
public class DBConnection {
    
     private static Connection con = null;
    
    private DBConnection(){}
    
    static {
        
        try{
        
             Class.forName("oracle.jdbc.driver.OracleDriver");
       con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Immu1234");
        }
        catch(Exception e)
        {
            
        }
        
        
    }
    
     public static Connection getCon()
       {
           return con;
       }
}
