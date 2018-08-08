/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;

/**
 *
 * @author Imran
 */
public class InsertTag extends BodyTagSupport {
   
     private DbOperations dbo = null ;
    
    
    @Override
    public int doEndTag(){
        
        dbo  =  new DbOperations();
        
        JspWriter out  = pageContext.getOut();
        
        try {
         
             String str = bodyContent.getString();
		
             String [] arrOfStr = str.split("@", 0);

             for (String a : arrOfStr)
            System.out.println(a);
             
		String pcod = arrOfStr[0].replaceAll("\\s+", "");
                String pqt = arrOfStr[2].replaceAll("\\s+", "");
                String ppric = arrOfStr[3].replaceAll("\\s+", "");        
                        
                int pcode = Integer.parseInt(pcod);
                int pqty  = Integer.parseInt(pqt);
                int pprice= Integer.parseInt(ppric);
                
                
                boolean done  =  dbo.operate("insert into products values("+pcode+",'"+ arrOfStr[1] +"',"+pqty+","+pprice+")");
        
            if(done){
               
                
               
                out.println("<h3 style='color:white'> Inserted Records Successfully !!!</h3>");
                out.println();
                out.println();
                out.println();
            }
            else{
                
                
                out.println("<h3 style='color:white'> Unable to insert the records !!!</h3>");
                out.println();
                out.println();
                out.println();
            }
     
            DisplayRecords dr  = new DisplayRecords();
            dr.doDisplay(out);
            
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        } 
        
        
        
        
        return EVAL_PAGE;
    }
}
