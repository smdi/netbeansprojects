/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;


public class RegisterServlet extends GenericServlet {
    
    
 
 private  String cid ="",cname="",cmail="",sql ="",cpass="",fname="",lname="",caddr="",cphone="";
 Connection con = null;
 PreparedStatement pstmt = null;
 
    
 @Override
    public void service(ServletRequest req ,ServletResponse res) throws ServletException ,IOException
   {
       
       
       
       PrintWriter pw = res.getWriter();
       
       res.setContentType("text/html");
       
       cid  = req.getParameter("cid");
       cname  = req.getParameter("cname");
       cpass  = req.getParameter("cpass");
       
     
     try{  
         
        
        
        con  = DBConnection.getCon();
      
       sql = "insert into userlogin_shoppingcart(emp_id  , emp_name ,emp_password) values(?,?,?)";
       
       pstmt = con.prepareStatement(sql);
                    
       pstmt.setString(1,cid);
       pstmt.setString(2,cname);
       pstmt.setString(3,cpass);
                   
                    
                int succ =   pstmt.executeUpdate();
       
                if(succ == 1)
                {
                                      
        
                    RequestDispatcher rd = req.getRequestDispatcher("ulogin.html");
                    rd.include(req, res);
                    pw.println("Successfully created the Account");  
                }
                
                
       
        
   } catch (Exception ex) {
         
       
        pw.println(""+ex);                    
     } 
     finally {
         
           try { 
               con.close();
           } catch (SQLException ex) {
               Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
     }

    

}

}
    
    
