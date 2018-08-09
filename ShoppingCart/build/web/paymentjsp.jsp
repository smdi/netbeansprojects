<%-- 
    Document   : paymentjsp
    Created on : 9 Aug, 2018, 10:50:28 PM
    Author     : Imran
--%>

<%@page import="java.util.Iterator"%>
<%@page import="com.imran.ProductsBean"%>
<%@page import="java.util.Collection"%>
<%@page import="com.imran.ReduceDb"%>
<%@page  errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include  flush="true" page="includejsp.jsp"/>

<%!

     float grandTotal =0;
 ReduceDb rdb;
 Collection ct;
 
 public void init(){
        
        rdb  = new ReduceDb();
        
    }

%>




       <td width = '82%' align='left' valign ='top'><p>
       
        
       <form method='post' action='payment'>

       <table width = '82%' align='left' valign ='top'><p style='background-color:blue ; color : white;'>
        
        
       <tr align='center' style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>
        
       <th colspan='5'><br>Payment Details<br></th>
        
       </tr>
        
       <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>
        

        
        <th width='10%' >Product Name</th>
        
        <th width='10%'>Product Quantity</th>
        
        <th width='10%'>Product Price</th>
        
        <th width='10%'> Total  </th>
        
        </tr>
 <%       
         ct = (Collection)session.getAttribute("products");
        
        if(ct ==  null){
            
 
  %>          
           <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:black ; text-align:center'><td colspan='5' >
            
            You have not Added any products yet !!!!

            </td></tr>
 <%           
        }
        else{


            
            Iterator i = ct.iterator();
             
            while(i.hasNext())
            {
                
                ProductsBean pb = (ProductsBean)i.next();
                
                
                
                
                try {
                  
                    
                 boolean rec   =   rdb.reduceFromDB(Integer.parseInt(pb.getPcode()) ,pb.getPqty() , out );
                
                 if(rec){
                     
   %>

                <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:black ; text-align:center'>
                     
                <td align='center' colspan='5'>\n the products "+pb.getPname()+" availabiltity is less</td>
                     
                </tr>
     
<%                
                 }
                 else{
   
%>
        
           <tr  style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:black ; text-align:center'>
                
                
         <td align='center'><%=pb.getPname().toLowerCase()%></td>
            
         <td align='center'><%=pb.getPqty()%></td>
                
         <td align='center'><%=pb.getPprice()%></td>
                
         <td align='center'><%=pb.getTprice()%></td>
                
         </tr>
             
               
                 
     <%        
          grandTotal += pb.getTprice();
                }
                 
                 
                } catch (Exception ex) {
                    
                }
                
            }

        }
        
%>
        <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center' align='center'>
        
        <td colspan='5'><em><br></em></td>
        
        </tr>"
        
        <br>
        
        <td style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:black ; text-align:center' colspan='5' align='center'><br>Grand Total  = <%= grandTotal %>  <br></td>
        
        <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:black ; text-align:center' align='center'>
        
        <td colspan='5'><em><br></em></td>
        
        </tr>
        
        <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:white ; color:tomato ; font-size:32px; text-align:center' align='center'>
        
        <td colspan='5'><br><br>Thank you !! keep Shopping with Shopping Kart<br><br></td>
        
        </tr>
        
        </table></center>
        
        </td></tr>

       </form>

<%@include  file="copyright.html" %>

<%
        session.removeAttribute("products");
%>