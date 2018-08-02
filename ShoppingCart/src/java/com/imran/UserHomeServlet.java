/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.io.PrintWriter;
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
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        
        HttpSession hs = req.getSession();
        
        if(hs == null){
            
            RequestDispatcher rd = req.getRequestDispatcher("ulogin.html");
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
        
        pw.println("<td width = '82%' align='left' valign ='top'>");
        
        pw.println("</tr>");
     
        pw.println("<tr align='center'>");
        
        pw.println("<td colspan='2'>");
        
        pw.println("<em>&copy; Copyrights 2018-2019</em>");
    
        pw.println("</tr>");
        
        pw.println("</table>");
    
        pw.println("</body></html>");
        
        pw.flush();
        
        pw.close();
        
    }
    
}
