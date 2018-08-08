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
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author Imran
 */
public class DisplayRecords {
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
     
     public void doDisplay(JspWriter out) throws SQLException, IOException{
         
         
           
         
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
            
         
     }
     
     
     
}
