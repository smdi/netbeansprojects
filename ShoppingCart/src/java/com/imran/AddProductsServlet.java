/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imran;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class AddProductsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        
        
        res.setContentType("text/html");
        
        PrintWriter pw  = res.getWriter();
       
        HttpSession hs = req.getSession();

        if(hs == null){
            
            RequestDispatcher  rd  = req.getRequestDispatcher("ulogin.html");
            
            rd.forward(req,res);
            
            
        }
        
        String uname  =  (String)hs.getAttribute("username");
        
        ArrayList al = (ArrayList)hs.getAttribute("products");
        
        if(al == null){
            
            al = new ArrayList();
            
            hs.setAttribute("products", al);
            
        }
        
        String[] pcodes = req.getParameterValues("products");
        
        for(int i=0 ; i<pcodes.length ;i++ ){
            
            if(req.getParameter(pcodes[i]).equals(""))
                continue;
            
            ProductsBean pb = new ProductsBean();
            
            pb.pcode = pcodes[i];
            
             ProductsBean pbi;
             int valid = 1 ,valj=0;
             
            for(int j=0;j<al.size() ;j++){
             
                pbi =  (ProductsBean) al.get(j);
              
                 if(pbi.getPcode().equals(pb.pcode)){
                     valid = 0;
                     valj = j;
                 }
            
            }
                    
             
            
            if(valid == 0){
                
                pb = (ProductsBean)al.get(valj);
                
                pb.pqty += Integer.parseInt(req.getParameter(pcodes[i]));
                
                pb.tprice = pb.pqty * pb.pprice ;
            }
            else{
            
                pb.pname = req.getParameter(pcodes[i]+"Name");
                
                pb.pprice = Double.parseDouble(req.getParameter(pcodes[i]+"Cost"));
                
                pb.pqty = Integer.parseInt(req.getParameter(pcodes[i]));
                
                pb.tprice = pb.pqty * pb.pprice ;
                
                al.add(pb);
                 
            }
            
        }
        
        
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
        
        pw.println("<td margin='5px' width = '82%' align='center' style='font-size:32px;background-color:whitesmoke ; color:tomato;' valign ='top'><p>");
        
        pw.println("<img src='images/cart.png' width='180px' height='180px' style='margin-top:18%'><br>Products added to cart</td");
        
        pw.println("</tr>");
        
        pw.println("<tr align='center'>");
        
        pw.println("<td colspan='2'><em>&copy;copyrights 2017-2018</em></td>");
        
        pw.println("</tr>");
        
        pw.println("</table>");
        
        pw.println("</></>");
    
        pw.flush();
        
        pw.close();
    }

}
