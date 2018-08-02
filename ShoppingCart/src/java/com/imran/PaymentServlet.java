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
          HttpSession hs = req.getSession();
                  
          if(hs == null){
              
              
              RequestDispatcher rd  = req.getRequestDispatcher("ulogin.html");
              rd.forward(req,res);
              
              
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
        
        pw.println("<td width = '82%' align='left' valign ='top'><p>");
       
        
       
//        pw.println("<center>Your Cart Contains following Products</center>");
        
        pw.println("<form method='post' action='payment'>");

        pw.println("<table width = '82%' align='left' valign ='top'><p style='background-color:blue ; color : white;'>");
        
        
        pw.println("<tr align='center' style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>");
        
        pw.println("<th colspan='5'><br>Payment Details<br></th>");
        
        pw.println("</tr>");
        
        pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>");
        

        
        pw.println("<th width='10%' >Product Name</th>");
        
        pw.println("<th width='10%'>Product Quantity</th>");
        
        pw.println("<th width='10%'>Product Price</th>");
        
        pw.println("<th width='10%'> Total  </th>");
        
        pw.println("</tr>");
        
        Collection ct = (Collection)hs.getAttribute("products");
        
        if(ct ==  null){
            
            pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:text ; text-align:center'><td colspan='5' >");
            
            pw.println("You have not Added any products yet !!!!");

            pw.println("</td></tr>");            
            
        }
        else{


            
            Iterator i = ct.iterator();
             
            while(i.hasNext())
            {
                
                ProductsBean pb = (ProductsBean)i.next();
                
                
                
                
                try {
                  
                    
                 boolean rec   =   rdb.reduceFromDB(Integer.parseInt(pb.getPcode()) ,pb.getPqty() ,pw );
                
                 if(rec){
                     
                     pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:white ; text-align:center'>");
                     
                     pw.println("<td align='center' colspan='5'>\n the products "+pb.getPname()+" availabiltity is less</td>");
                     
                     pw.println("</tr>");
                     
                 }
                 else{
                     pw.println("<tr  style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:white ; text-align:center'>");
                
                
                pw.println("<td align='center'>"+pb.getPname().toLowerCase()+"</td>");
            
                pw.println("<td align='center'> "+pb.getPqty()+"</td>");
                
                pw.println("<td align='center'>"+pb.getPprice()+"</td>");
                
                pw.println("<td align='center'>"+pb.getTprice()+"</td>");
                
                pw.println("</tr>");
             
                grandTotal += pb.getTprice();
                 }
                 
                 
                } catch (SQLException ex) {
                    
                }
                
            }

        }
        
        pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center' align='center'>");
        
        pw.println("<td colspan='5'><em><br></em></td>");
        
        pw.println("</tr>");
        
        pw.println("<br>");
        
        pw.println("<td style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:black ; text-align:center' colspan='5' align='center'><br>Grand Total  = "+grandTotal+"  <br></td>");
        
        pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:black ; text-align:center' align='center'>");
        
        pw.println("<td colspan='5'><em><br></em></td>");
        
        pw.println("</tr>");
        
        pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:tomato ; font-size:32px; text-align:center' align='center'>");
        
        pw.println("<td colspan='5'><br><br>Thank you !! keep Shopping with Shopping Kart<br><br></td>");
        
        pw.println("</tr>");
        
        pw.println("</table></center>");
        
        pw.println("</td></tr>");

        pw.println("<tr align='center'>");
        
        pw.println("<td colspan='2'><em>&copy;Copyrights 2017-2018</em></td>");
        
        pw.println("</tr>");
        
        pw.println("</table>");
        
        pw.println("<br><br><br><br>");
        
        pw.println("</form>");
        
        pw.println("</body></html>");
        
        pw.flush();
        
        pw.close();
        
        hs.removeAttribute("products");
        
    }

   
}
