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
 private  HttpSession hs = null;
 private  UserDao ud ;
 private  AdminDao ad;
 private  StoreDetails sd;
 private ServletContext sct;
   
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
       
       
       sct = req.getServletContext();
       
       sd = (StoreDetails)  sct.getAttribute("who");
       
       int log = (int) sct.getAttribute("logout");
       
       if(log == 0){
          try{  
         
        con  = DBConnection.getCon();
  
        
        hs  = req.getSession();
        
        
        
         String who= sd.getWho();
                
        if(who.equals("user")){
          
            hs.setAttribute("username",cid);
                   RequestDispatcher rd = req.getRequestDispatcher("userhome"); //success
                   rd.include(req, res);
               System.out.println("\n attribute added");    
    
        }
        else if(who.equals("admin")){
    
             hs.setAttribute("username",cid);
                   RequestDispatcher rd = req.getRequestDispatcher("adminlogin.html"); //success
                   rd.include(req, res);

             System.out.println("\n attribute added");         

        }
        else if(who.equals(null)){
            RequestDispatcher rd = req.getRequestDispatcher("ulogin.html");
           rd.forward(req,res);
        }
        
        
        
    
   } catch (Exception ex) {
         
       
        pw.println(""+ex);                    
     } 
 
       }
       else{
            RequestDispatcher rd = req.getRequestDispatcher("ulogin.html");
           rd.forward(req,res);
        }
       
       
}

   
}
    
    
