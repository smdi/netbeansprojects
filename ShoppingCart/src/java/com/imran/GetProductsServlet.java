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
public class GetProductsServlet extends HttpServlet {

    ProductsDao dao = null;
    
    public void init(){
        
        dao  =  new ProductsDao();
        
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        
        HttpSession hs  = req.getSession();
        
        if(hs == null){
            
             RequestDispatcher rd  = req.getRequestDispatcher("ulogin.html");
             rd.forward(req, res);
        
        }
        
         String uname  = (String)hs.getAttribute("username");
        
        res.setContentType("text/html");
        
        PrintWriter pw = res.getWriter();
        
        pw.println("<html><head>");
      
        pw.println("</head><body style='background : whitesmoke '>");
        
        pw.println("<table width='100%' height='100%'  style='border-collapse: collapse'>");
        
        pw.println("<tr style='background-color:lightgreen'>");
        
        pw.println("<td align='center' height='50px' colspan='2'>");
        
        pw.println("<strong><font size='6' style='color:white'>Shopping Kart&emsp;&emsp;&emsp;&emsp;Welcome , "+uname+"</font>");
        
        pw.println("</strong></td>");
        
        pw.println("</tr>");

        pw.println("<tr >");
        
        pw.println("<td style='background-color:white ' width='18%' height='500' valign = 'top'>");
        
        pw.println("<p>&nbsp;</p>");
        
        pw.println("<blockquote><p><ul style='list-style-type: none;margin: 0; margin-top: 10px;  padding: 0;display:block;background-color:#ddd;'>");
        
        pw.println("<li style='display:block;background-color:#ddd'><a href='"+res.encodeURL("getProducts")+"' >");
        
        pw.println("View Products </a></li></p>");
        
        pw.println("<li><a   href='"+res.encodeURL("getCart")+"' >");
        
        pw.println("View Cart Details </a></li></p>");
       
        pw.println("<li><a href='"+res.encodeURL("logout")+"' >");
        
        pw.println("Logout </a></li></ul></p>");
        
        pw.println("</blockquote></td>");
        
        pw.println("<td width = '82%' align='left' valign ='top'><p style='background-color:blue ; color : white;'>");
       
        pw.println("<form method = 'post' action= '"+res.encodeURL("addProducts")+"'>");

        pw.println("<table width='100%'  style='border-collapse: collapse ; border: 5px solid white;'>");
        
        pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>");
        
        pw.println("<th width='8%' >check</th>");
        
        pw.println("<th width='24%'>Products Code</th>");
        
        pw.println("<th width='28%'>Products Name</th>");
        
        pw.println("<th width = '20%'>Available</th>");
        
        pw.println("<th width='20%'>Required</th>");
        
        pw.println("</tr>");
        
        
        
            Collection prods = dao.getProducts();
            if(prods ==  null){
                
                pw.println("<tr style=' height:35px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:#4CAF50 ; color:white ; text-align:center'><td colspan='5' align='center'>");
                pw.println("No Products Available");
                pw.println("</td></tr>");
                
            }
            
            else{
                
                Iterator i  = prods.iterator();
        
                while(i.hasNext())
                {
                    
                    ProductsBean pb  = (ProductsBean)i.next();
                    
                    pw.println("<tr style=' height:35px;  padding-top:20px ; padding-bottom:20px ; font-family:verdana ; color:black ;background-color:white; text-align:left'>");
                    
                    pw.println("<td align='center'>");
                    
                    pw.println("<input type='checkbox' name='products' value='"+pb.getPcode()+"'>");
                    
                    pw.println("<input type='hidden' name='"+pb.getPcode()+"Name' value='"+pb.getPname()+"'>");
                    
                    pw.println("<input type='hidden' name='"+pb.getPcode()+"Cost' value='"+pb.getPprice()+"'>");
                    
                    pw.println("<td>"+ pb.getPcode()+"</td>");
                
                    pw.println("<td>"+ pb.getPname().toLowerCase()+"</td>");
                        
                    pw.println("<td>"+ pb.getPqty()+"</td>");
                            
                    pw.println("<td>");
                    
                    pw.println("<input type='text' name = '"+ pb.getPcode() +"'>");
                    
                    pw.println("</td></tr>");
                    
                    
                }
                
                 }
            
            
            pw.println("</table>");
            
            pw.println("<br><center>");

            pw.println("<input style='width:150px;height:30px; color:white;  background-color:slateblue ; border-top:none; border-bottom :blue ; border-left:none ;border-right:none ;' type='submit' value='Add products to cart'>");
    
            pw.println("</center>");
            
            pw.println("</form>");
            
            pw.println("</td></tr>");
            
            pw.println("<tr align='center'>");
            
            pw.println("<td  colspan='2' size='1' style='font-family:arial ; margin-top:20px; '>");
            
            pw.println("<br><br><br>&copy;&emsp;&emsp;&emsp;&emsp;Intel HadrCore Zulfiqar Group of Technologies copyrights 2018 - 2019</td>");
            
            pw.println("</tr>");
            
            pw.println("</table>");

            pw.println("</body></html>");
            
            pw.flush();
            
            pw.close();
            
    }
    
}
