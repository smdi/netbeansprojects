<%-- 
    Document   : delete
    Created on : 8 Aug, 2018, 10:40:24 PM
    Author     : Imran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<jsp:include  flush="true" page="delete.html"/>
<%@taglib uri="/WEB-INF/tlds/delete.tld"  prefix="dbtags" %>

<html><head>
 </head><body >
     <style>
        
       body{
            width: 100%;
            background-color: lightgray;
            align-items: center;
            background-image: url("images/onli.png"); 
        }
        </style>



<%!

    private String pcode ;
    
%>


<%
    
            pcode  = request.getParameter("pcode");
           

%>

<dbtags:delete>
    <jsp:expression> pcode  </jsp:expression>
</dbtags:delete>

