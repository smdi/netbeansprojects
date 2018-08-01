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
        
        pw.println("Products added successfully to your cart</td");
        
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
