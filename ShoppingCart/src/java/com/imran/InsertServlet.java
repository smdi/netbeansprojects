/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Imran
 */
public class InsertServlet extends HttpServlet {

  
    private DbOperations dbo = null;
    private String pname ;
    private int pcode , pqty , pprice;
    
    public void init(){
        
        dbo  =  new DbOperations();
    
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
           
            PrintWriter pw  = res.getWriter();
            
            res.setContentType("text/html");
        
            RequestDispatcher rd =  req.getRequestDispatcher("insert.html");
            
        try {
        
            pname  = req.getParameter("pname");
            pcode  = Integer.parseInt(req.getParameter("pcode"));
            pqty  = Integer.parseInt(req.getParameter("pqty")); 
            pprice  = Integer.parseInt(req.getParameter("pprice"));
            
            boolean done  =  dbo.operate("insert into products values("+pcode+",'"+ pname +"',"+pqty+","+pprice+")");
        
            if(done){
               
                
                rd.include(req,res);
                pw.println(" Inserted Records Successfully !!!");
                pw.println();
                pw.println();
                pw.println();
            }
            else{
                
                rd.include(req,res);
                pw.println(" Unable to insert the records !!!");
                pw.println();
                pw.println();
                pw.println();
            }
        
        
        } catch (SQLException ex) {
            
        }
        
        
        
        
    }

   
}
