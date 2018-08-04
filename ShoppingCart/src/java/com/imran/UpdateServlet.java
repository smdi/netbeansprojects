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
public class UpdateServlet extends HttpServlet {

   
    
    private DbOperations dbo = null;
    private String pcode , pqty , pprice;
    
    public void init(){
        
        dbo  =  new DbOperations();
    
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
           
            PrintWriter pw  = res.getWriter();
            
            res.setContentType("text/html");
        
            RequestDispatcher rd =  req.getRequestDispatcher("update.html");
            
        try {
        

            pcode  = req.getParameter("pcode");
            pqty  = req.getParameter("pqty"); 
            pprice  = req.getParameter("pprice");
            
            int code  = Integer.parseInt(pcode);
            int qty   = Integer.parseInt(pqty);
            int price = Integer.parseInt(pprice);
            
           if(qty==0  && price>0){
               
               setDone("update products set pprice = "+price+" where pcode="+code+"",rd ,req,res,pw);
           }
           else if(qty>0  && price==0){
               setDone("update products set pqty = "+qty+" where pcode="+code+"",rd ,req,res,pw);
           }
           else if(qty>0  && price>0){
               setDone("update  products set pqty = "+qty+" ,  pprice = "+price+"  where pcode="+code+"",rd ,req,res,pw);
           }
           else {
                rd.include(req,res);
                pw.println(" please enter atleast one value to update !!!");
           }
           
     
        
        } catch (SQLException ex) {
            
        }
        
        
        
        
    }

    private void setDone(String sql, RequestDispatcher rd, HttpServletRequest req, HttpServletResponse res, PrintWriter pw) throws ServletException, SQLException, IOException {
        
        boolean done  =  dbo.operate(sql);
        
            if(done){
               
                
                rd.include(req,res);
                pw.println(" Updated Records Successfully !!!");
                pw.println("</div>");
                pw.println();
                pw.println();
            }
            else{
                
                rd.include(req,res);
                pw.println(" Unable to update the records !!!");
                pw.println("</div>");
                pw.println();
                pw.println();
            }
        
    }

    
    
}
