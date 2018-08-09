/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PaymentServlet extends HttpServlet {

    ReduceDb rdb;
    
    public void init(){
        
        rdb  = new ReduceDb();
        
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

          
        float grandTotal =0;
         
        HttpSession hs = req.getSession(false);
                  
          if(hs == null){
              
              
              RequestDispatcher rd  = req.getRequestDispatcher("ulogin.html");
              rd.forward(req,res);
              
              
          }
          
          else{  
       
              
              RequestDispatcher rd = req.getRequestDispatcher("paymentjsp.jsp");
              
              rd.include(req, res);
              
              
//        
    }

    }
}
