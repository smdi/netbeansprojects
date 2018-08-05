/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
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
public class GetCartDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

           
          HttpSession hs = req.getSession(false);
           
          
          
          if(hs == null){
              
              
              RequestDispatcher rd  = req.getRequestDispatcher("ulogin.html");
              rd.forward(req,res);
              
              
          }
          else{   
       String  uname  = (String)hs.getAttribute("username");
        
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
        
        pw.println("<td width = '82%' align='left' valign ='top'><p  style='background-color:blue ; color : white;'>");
        
        pw.println("<form method='post' action='payment'>");

        pw.println("<table width='100%'  style='border-collapse: collapse ; border: 5px solid white;'>");
        
        pw.println("<tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>");
        
        pw.println("<th width='15%'>Product Code</th>");
        
        pw.println("<th width='15%'>Product Name</th>");
        
        pw.println("<th width='15%'>Product Quantity</th>");
        
        pw.println("<th width='15%'>Product Price</th>");
        
        pw.println("<th width='15%'> Total  </th>");
        
        pw.println("</tr>");
        
        Collection ct = (Collection)hs.getAttribute("products");
        
        int which = 0;
        
        if(ct ==  null){
            
            pw.println("<tr><td colspan='5' align='center'>");
            
            pw.println("You have not Added any products yet !!!!");

            pw.println("</td></tr>");            
            
            which = 1;
        }
        else{


            Iterator i = ct.iterator();
             
            while(i.hasNext())
            {
                ProductsBean pb = (ProductsBean)i.next();
                
                pw.println("<tr>");
                
                pw.println("<td>"+pb.getPcode()+"</td>");
                
                pw.println("<td>"+pb.getPname().toLowerCase()+"</td>");
            
                pw.println("<td>"+pb.getPqty()+"</td>");
                
                pw.println("<td>"+pb.getPprice()+"</td>");
                
                pw.println("<td>"+pb.getTprice()+"</td>");
                
                
                pw.println("</tr>");
                
            
            }

        }
        
        pw.println("<br><br><br>");
        
        if(which == 0){
            
        pw.println("<td colspan='6' align='center'><br><button style='width:150px;height:30px; color:white;  background-color:slateblue ; border-top:none; border-bottom :blue ; border-left:none ;border-right:none ;' type='submit'>Make Payment</button><br><br></td>");
        
        }
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
    }

}

}











