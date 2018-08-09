<%-- 
    Document   : getcartdetailsjsp
    Created on : 9 Aug, 2018, 9:21:29 PM
    Author     : Imran
--%>

<%@page import="com.imran.ProductsBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<jsp:include page="includejsp.jsp" flush="true" />


<%! 
   int which = 0; 
%>  


<td width = '82%' align='left' valign ='top'><p style='background-color:blue ; color : white;'>
        
 <form  method = 'post' action= "payment">     
    
     <table width='100%'  style='border-collapse: collapse ; border: 5px solid white;'>

         <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>
         
        <th width='8%' >check</th>
        
        <th width='24%'>Products Code</th>
        
        <th width='28%'>Products Name</th>
        
        <th width = '20%'>Available</th>
        
        <th width='20%'>Required</th>
        
        </tr>

        
  <%      
 Collection ct = (Collection)session.getAttribute("products");
        
        if(ct ==  null){
            
            
       %>     
        
       
       <tr><td colspan='5' align='center'>
            
            You have not Added any products yet !!!!

            </td></tr>
            

      
<%          
    
                which = 1;
            }





else{


            Iterator i = ct.iterator();
             
            while(i.hasNext())
            {
             ProductsBean pb = (ProductsBean)i.next();
             

%>
<tr style='text-align:center'>
                
                <td><%=pb.getPcode()%></td>
                
                <td><%=pb.getPname().toLowerCase()%></td>
  

                <td><%=pb.getPqty()%></td>
                
                <td><%=pb.getPprice()%></td>
                
                <td><%=pb.getTprice()%></td>
                
                </tr>
                
 <%
                
            }

        }
 
  if(which == 0){
 

%>
        <td colspan='6' align='center'><br><button style='width:150px;height:30px; color:white;  background-color:slateblue ; border-top:none; border-bottom :blue ; border-left:none ;border-right:none ;' type='submit'>Make Payment</button><br><br></td>

<%        
        }
%>   
        
        </table></center>
        
        </td></tr>

<%@include file="copyright.html" %>