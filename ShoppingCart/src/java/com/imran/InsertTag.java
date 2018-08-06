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
    
     int count;
     
     static Connection con =null ;
  
     static Statement stmt = null ;

     ResultSet rs = null;
   
     ResultSetMetaData rsmd =  null; 
 
     static{
    
         try { 
         con  = DBConnection.getCon();

         stmt = con.createStatement();

        }
  catch(Exception e){
      
      e.printStackTrace();
  }

}
     
     
     
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
     
            rs   =  stmt.executeQuery("select * from products");
            
            rsmd = rs.getMetaData();
            
            count= rsmd.getColumnCount();
            
            out.println("<table width='100%'  style='border-collapse: collapse ; border: 5px solid white;'>");
            
            out.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>");
            
            for(int i = 1 ;i<=count ; i++){
                
                
                out.println("<th width='24%'>"+rsmd.getColumnName(i)+"</th>");
	        
            }
            
            out.println("</tr>");
            
            
             while(rs.next()){
          
                 out.println(" <tr style=' height:35px;  padding-top:20px ; padding-bottom:20px ; font-family:verdana ; color:black ;background-color:white; text-align:left'>");
                 
                 for(int i = 1 ;i<=count ; i++){
                     
                     
                     out.println(" <td align='center' width='24%'>"+ rs.getString(i).toLowerCase()+"</td> ");
                 }
                 
             }
            
             out.println("</tr>");
             out.println("</table></body></html> ");
            
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        } 
        
        
        
        
        return EVAL_PAGE;
    }
}
