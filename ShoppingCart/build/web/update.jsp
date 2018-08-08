<%-- 
    Document   : update
    Created on : 8 Aug, 2018, 9:17:26 PM
    Author     : Imran
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp" %>
<jsp:include  flush="true" page="update.html"/>
<%@taglib uri="/WEB-INF/tlds/update.tld"  prefix="dbtags" %>

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

    private String pcode , pqty , pprice;
    
%>


<%
    
            pcode  = request.getParameter("pcode");
            pqty  = request.getParameter("pqty"); 
            pprice  = request.getParameter("pprice");

%>

<dbtags:update>
    <jsp:expression> pcode + "@" + pqty + "@" + pprice  </jsp:expression>
</dbtags:update>




