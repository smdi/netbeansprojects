/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Imran
 */
public class ReduceDb {
    
    
   
    
    public boolean reduceFromDB(int pcode , int qty ,PrintWriter pw) throws SQLException{
  
        
        Connection con =  null;
        Statement stmt = null;
       
        String sql = "";
        
        try{
        
        
       
            
            con  = DBConnection.getCon();
            
            stmt = con.createStatement();
            
            sql = "select pqty from products where pcode = "+pcode+"";
            
//            pw.println("\ntable qty "+stmt.execute(sql));
            
            if(stmt.execute(sql)){
                
                ResultSet rs  = stmt.executeQuery(sql);
                
                rs.next();
                
                int tqty =  Integer.parseInt(rs.getString(1));
                
//                pw.println("\ntable qty "+tqty);
                
                if(tqty > qty){
                
                    int mqty = tqty - qty;
                    
//                    pw.println("\ntable qty "+mqty);
                    
                    sql  = "update products set pqty="+mqty+" where pcode= "+pcode+"";
                    
                    stmt.executeUpdate(sql);
               
//                    pw.println("Update value "+stmt.execute(sql));
                }
                else{
                    
                    return true;
                }
            }
          
        }
        catch(Exception e){
           pw.println(e);
        }
            
        return stmt.execute(sql);
    }
    
    


}

