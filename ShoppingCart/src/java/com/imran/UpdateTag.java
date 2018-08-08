/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author Imran
 */
public class UpdateTag extends BodyTagSupport {

    
     private DbOperations dbo = null;
    private String pcode , pqty , pprice;
     
    
    
    
    @Override
    public int doEndTag() throws JspException {
        
        
         dbo  =  new DbOperations();
         
         JspWriter pw  = pageContext.getOut();
         
         try{
             
              String str = bodyContent.getString();
		
             String [] arrOfStr = str.split("@", 0);

             for (String a : arrOfStr)
             System.out.println(a);
             
		String pcod = arrOfStr[0].replaceAll("\\s+", "");
                String pqt = arrOfStr[1].replaceAll("\\s+", "");
                String ppric = arrOfStr[2].replaceAll("\\s+", "");        
                        
                int pcode = Integer.parseInt(pcod);
                int pqty  = Integer.parseInt(pqt);
                int pprice= Integer.parseInt(ppric);
             
                
                
                if(pqty==0  && pprice>0){
               
               setDone("update products set pprice = "+pprice+" where pcode="+pcode+"",pw);
           }
           else if(pqty>0  && pprice==0){
               setDone("update products set pqty = "+pqty+" where pcode="+pcode+"",pw);
           }
           else if(pqty>0  && pprice>0){
               setDone("update  products set pqty = "+pqty+" ,  pprice = "+pprice+"  where pcode="+pcode+"",pw);
           }
           else {
               
                pw.println("<h3 style='color:white'> please enter atleast one value to update !!!</h3>");
           }
           
           DisplayRecords dr  = new DisplayRecords();
            dr.doDisplay(pw);   
                
            
            
        } catch (Exception  ex) {
            ex.printStackTrace();
        } 
     
        return EVAL_PAGE;
    }
    
    private void setDone(String sql,  JspWriter pw) throws ServletException, SQLException, IOException {
        
        boolean done  =  dbo.operate(sql);
        
            if(done){
               
                
                
                pw.println("<h3 style='color:white'> Updated Records Successfully !!!</h3>");
                pw.println();
                pw.println();
                pw.println();
            }
            else{
                
                
                pw.println("<h3 style='color:white'> Unable to Update Records Successfully !!!</h3>");
                pw.println();
                pw.println();
                pw.println();
            }
        
    }
    
    
    
}
