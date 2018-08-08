/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author Imran
 */
public class DeleteTag extends BodyTagSupport {

    private DbOperations dbo = null; 
    private int pcode ;
    @Override
    public int doEndTag() throws JspException {
       
        dbo  =  new DbOperations();
        
        try {
        
             JspWriter pw  = pageContext.getOut();
            
            
            
              String str = bodyContent.getString();
	
             pcode  = Integer.parseInt(str.replaceAll("\\s+", ""));
             
            boolean done  =  dbo.operate("delete from products where pcode = "+pcode+"");
        
            if(done){
               
                

                pw.println("<h3 style='color:white'> Deleted Records Successfully !!! </h3>");
                pw.println();
                pw.println();
                pw.println();
            }
            else{
                
                
                pw.println("<h3 style='color:white'> Unable to Delete the records !!! </h3>");
                pw.println();
                pw.println();
                pw.println();
            }
        
            
            DisplayRecords dr  = new DisplayRecords();
            dr.doDisplay(pw); 
        
        } catch (SQLException ex) {
            
        } catch (IOException ex) {
            Logger.getLogger(DeleteTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EVAL_PAGE;
    }
    
}
