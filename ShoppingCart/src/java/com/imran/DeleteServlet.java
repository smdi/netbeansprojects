/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Imran
 */
public class DeleteServlet extends HttpServlet {
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
        
            RequestDispatcher rd =  req.getRequestDispatcher("delete.html");
            
        try {
        
            
            pcode  = Integer.parseInt(req.getParameter("pcode"));
            
            boolean done  =  dbo.operate("delete from products where pcode = "+pcode+"");
        
            if(done){
               
                
                rd.include(req,res);
                pw.println(" Deleted Records Successfully !!!");
                pw.println();
                pw.println();
                pw.println();
            }
            else{
                
                rd.include(req,res);
                pw.println(" Unable to Delete the records !!!");
                pw.println();
                pw.println();
                pw.println();
            }
        
        
        } catch (SQLException ex) {
            
        }
        
        
        
        
    }

}
