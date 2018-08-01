/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
    
    
 
 private  String cid ="",cname="",sql="";
 private  Connection con = null;
 private  HttpSession hs,hs1 = null;
 private  UserDao ud ;
 private  AdminDao ad;
   
 public void init(){
       
    ud = new UserDao();
    ad = new AdminDao();
       
   }
 
 @Override
    public void doPost(HttpServletRequest req ,HttpServletResponse res) throws ServletException ,IOException
   {
       

       PrintWriter pw = res.getWriter();
       
       res.setContentType("text/html");
       res.setHeader("Cache-Control","no-cache");
        res.setHeader("Cache-Control","no-store");
        res.setDateHeader("Expires", 0); 
        res.setHeader("Pragma","no-cache");
        
       cid  = req.getParameter("username");
       cname  = req.getParameter("password");
       
     try{  
         
    
        con  = DBConnection.getCon();
  
        
        hs  = req.getSession();
        hs1 = req.getSession();
        
        if(ud.validate(cid, cname)){
          
            hs.setAttribute("username",cid);
                   RequestDispatcher rd = req.getRequestDispatcher("userhome"); //success
                   rd.include(req, res);
//                   pw.println("User Login Success");
                  

 
        }
        else if(ad.validate(cid, cname)){
    
             hs.setAttribute("username",cid);
                   RequestDispatcher rd = req.getRequestDispatcher("adminlogin.html"); //success
                   rd.include(req, res);
//                   pw.println("Admin Login Success");
                  

        }
        else{
           
                   RequestDispatcher rd = req.getRequestDispatcher("ulogin.html"); //failed
                   rd.include(req, res);
                   pw.println("Invalid username or password");
        }
        
    
   } catch (NullPointerException |SQLException ex) {
         
       
        pw.println(""+ex);                    
     } 

}

}
    
    
