<%-- 
    Document   : includejsp
    Created on : 9 Aug, 2018, 6:08:11 PM
    Author     : Imran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
</head>
<body style='background : whitesmoke '>
<table width='100%' height='100%'  style='border-collapse: collapse'>    
 <tr style='background-color:lightgreen'>   
 <td align='center' height='50px' colspan='2'>   
     
     <%!
     
        String uname;
     %>
     
     
     <%
         uname  = (String)session.getAttribute("username");
         
     %>
     
 <strong><font size='6' style='color:white'>Shopping Kart&emsp;&emsp;&emsp;&emsp;Welcome&emsp; , <%=uname%> </font>   
  </strong></td>
</tr>
<tr >
    <td style='background-color:white ' width='18%' height='500' valign = 'top'>
    <p>&nbsp;</p>
    <blockquote>
        <p>
        <ul style='list-style-type: none;margin: 0; margin-top: 10px;  padding: 0;display:block;background-color:#ddd;'>
           <li style='display:block;background-color:#ddd'>
               <a href="getProducts" > 
                   View Products </a>
           </li>
        </p>

        <li><a   href="getCart" >
        View Cart Details 
            </a>
        </li>
        </p>
        
        <li><a href="logout" >
        Logout 
            </a>
        </li>
        </ul>
        </p>
        </blockquote></td>
          