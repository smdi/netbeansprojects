/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Imran
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        
        PrintWriter pw  = res.getWriter();
        
        res.setHeader("Pragma","no-cache");
        res.setHeader("Cache-Control","no-cache");
        res.setHeader("Cache-Control","no-store");
        res.setHeader("Expires","0");
        res.setDateHeader("Expires", -1);
        
        
        try{
        
        HttpSession hs = req.getSession(false);
        
           
        hs.removeAttribute("username");
        hs.invalidate();
        
      hs = req.getSession(false);
        
      
        if(hs == null){
        RequestDispatcher rd = req.getRequestDispatcher("ulogin.html");
            
        rd.forward(req, res);   

       } 
       
        
        }
        catch(Exception e){
            
        }
        RequestDispatcher rd = req.getRequestDispatcher("ulogin.html"); //success
              rd.include(req, res);
        
    }

    

}
