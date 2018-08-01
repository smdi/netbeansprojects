/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Imran
 */
public class UserDao {
    
    
    private ResultSet rs = null;
    public boolean validate(String uname , String upass) throws SQLException{

        
        try{
            
            Connection con  = DBConnection.getCon();
            
            Statement st  = con.createStatement();

            rs =  st.executeQuery("select * from userlogin_shoppingcart where emp_name = '"+uname+"' and emp_password = '"+upass+"'");
            
        }
        catch(Exception e){
            
            e.printStackTrace();
            
        }
        

    return rs.next();        
    }
    
    
}
