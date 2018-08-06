<%-- 
    Document   : error
    Created on : 6 Aug, 2018, 5:28:23 PM
    Author     : Imran
--%>

<%@page isErrorPage="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        
         <style>
        
       img{
           margin-left: 35%;
            
            background-color: lightgray;
            align-items: center;
        }
        h1{
            background-color: white;
            margin-left: 20px;
        }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
        
        <br><br>
    <p><%=exception.toString() %></p>
    </body>
</html>
