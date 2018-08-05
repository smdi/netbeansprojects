/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Imran
 */
public class LoginFilter extends HttpServlet implements Filter {

    private  UserDao ud ;
    private  AdminDao ad;
    private  String cid ="",cname="",sql="";
    private  Connection con = null;
    private  HttpSession hs,hs1 = null;
    private  StoreDetails sd ;
    private  ServletContext cxt;
    
   
 @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
         ud = new UserDao();
         ad = new AdminDao();
         sd = new StoreDetails();
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
      
        PrintWriter pw = res.getWriter();
       
        res.setContentType("text/html");
        
       
       cid  = req.getParameter("username");
       cname  = req.getParameter("password");
        
         try{  
         
    
         cxt = req.getServletContext();
         
             
        con  = DBConnection.getCon();
  
        
        
        
        if(ud.validate(cid, cname)){
          
            sd.setWho("user");
            cxt.setAttribute("who", sd);
            chain.doFilter(req, res);
        }
        else if(ad.validate(cid, cname)){
    
             sd.setWho("admin");
             cxt.setAttribute("who", sd);
             chain.doFilter(req, res);
             
        }
        else{
             
                    RequestDispatcher rd =  req.getRequestDispatcher("ulogin.html");
                    rd.include(req, res);
                    pw.println("<div style='background-color:white ; color:red;text-align: center;margin-left: 37%; margin-right: 35%;width=25% ; height=25px; '>");
                    pw.println("\n Invalid username and password");
                    pw.println("</div>");
        }
        
    
   } catch (NullPointerException |SQLException ex) {
         
       
        pw.println(ex);                    
     } 
    }

   

  
    

}
