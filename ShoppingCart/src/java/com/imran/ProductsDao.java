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
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Imran
 */
public class ProductsDao {
 

    private ResultSet rs = null;
    private ProductsBean pb = null;
    ArrayList al;
    public Collection getProducts() {

        
        try{
            
            Connection con  = DBConnection.getCon();
            
            Statement st  = con.createStatement();

            rs =  st.executeQuery("select * from products");
            
             al = new ArrayList();
            
            pb  = new ProductsBean();
            
            while(rs.next()){

                pb  = new ProductsBean();
                    pb.setPcode(rs.getString(1));
                    pb.setPname(rs.getString(2));
                    pb.setPqty(rs.getInt(3));
                    pb.setPprice(rs.getDouble(4));
                    
                    al.add(pb);
            }
            
        }
        catch(Exception e){
            
            e.printStackTrace();
            
        }
        

    return al;        
    }
    

    
    
}
