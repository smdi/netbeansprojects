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
        
        pw.println("<title>Shopping Cart</title>");
        
        pw.println("</head><body>");
        
        pw.println("<table width='100%' height='90%' border='1'>");
        
        pw.println("<tr align='center'>");
        
        pw.println("<td height='39' colspan='2'>");
        
        pw.println("<strong><font size='5'>Stop and Shop</font>");
        
        pw.println("</strong></td>");
        
        pw.println("</tr>");

        pw.println("<tr>");
        
        pw.println("<td width='18%' height='500' valign = 'top'>");
        
        pw.println("<p>&nbsp;</p>");
        
        pw.println("<blockquote><p>");
        
        pw.println("<a href='"+res.encodeURL("getProducts")+"' >");
        
        pw.println("View Products </a></p>");
        
        pw.println("<a href='"+res.encodeURL("getCart")+"' >");
        
        pw.println("View Cart Details </a></p>");
       
        pw.println("<a href='"+res.encodeURL("logout")+"' >");
        
        pw.println("Logout </a></p>");
        
        pw.println("</blockquote></td>");
        
        pw.println("<td width = '82%' align='left' valign ='top'><p>");
       
        pw.println("<font size='6'>Welcome , "+uname+"</font></p>");
       
//        pw.println("<center>Your Cart Contains following Products</center>");
        
        pw.println("<form method='post' action='payment'>");

        pw.println("<table width='100%' >");
        
        
        pw.println("<tr align='center'>");
        
        pw.println("<td colspan='5'><em><hr>Payment Details<hr></em></td>");
        
        pw.println("</tr>");
        
        pw.println("<tr>");
        
//        pw.println("<th width='20%'>Product Code</th>");
        
        pw.println("<th width='10%' >Product Name</th>");
        
        pw.println("<th width='10%'>Product Quantity</th>");
        
        pw.println("<th width='10%'>Product Price</th>");
        
        pw.println("<th width='10%'> Total  </th>");
        
        pw.println("</tr>");
        
        Collection ct = (Collection)hs.getAttribute("products");
        
        if(ct ==  null){
            
            pw.println("<tr><td colspan='5' align='center'>");
            
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
                     
                     pw.println("<tr>");
                     
                     pw.println("<td align='center' colspan='5'>\n the products "+pb.getPname()+" availabiltity is less</td>");
                     
                     pw.println("</tr>");
                     
                 }
                 else{
                     pw.println("<tr>");
                
//                pw.println("<td>"+pb.getPcode()+"</td>");
                
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
        
        pw.println("<tr align='center'>");
        
        pw.println("<td colspan='5'><em><hr></em></td>");
        
        pw.println("</tr>");
        
        pw.println("<br>");
        
        pw.println("<td colspan='5' align='center'><br>Grand Total  = "+grandTotal+"  <br></td>");
        
        pw.println("<tr align='center'>");
        
        pw.println("<td colspan='5'><em><hr></em></td>");
        
        pw.println("</tr>");
        
        pw.println("<tr align='center'>");
        
        pw.println("<td colspan='5'><em><br><hr>Thank you !! keep Shopping with Stop and Shop<hr><br></em></td>");
        
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
