/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Imran
 */
public class DbOperations {
    
    
    private Connection con  = null ;
    private Statement stmt = null ;
    String sql;
    
    public boolean operate( String sql) throws SQLException{
        
        
        try{
             con = DBConnection.getCon();
            
             stmt  = con.createStatement();
             
        }
        catch(Exception e){
            
            
        }
        
        
        return stmt.executeUpdate(sql) == 1 ? true : false;
    }
    
    
    
}
