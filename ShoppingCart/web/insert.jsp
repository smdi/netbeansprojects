<%-- 
    Document   : insert
    Created on : 5 Aug, 2018, 12:18:43 PM
    Author     : Imran
--%>

<%@page import="com.imran.DbOperations"%>
<%@page import="com.imran.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.sql.*" %>
<%@page errorPage="error.jsp" %>
<jsp:include page="insert.html" flush="true"/>
<%@taglib uri="/WEB-INF/tlds/result.tld"  prefix="dbtags" %>

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
   
    private String pname ;
 
    private int pcode , pqty , pprice;

%>


<%
   
            pname  = request.getParameter("pname");
            pcode  = Integer.parseInt(request.getParameter("pcode"));
            pqty  = Integer.parseInt(request.getParameter("pqty")); 
            pprice  = Integer.parseInt(request.getParameter("pprice"));
  
 %>   
 
 
 
<dbtags:username>
   <jsp:expression> pcode +"@"+pname + "@" + pqty + "@" + pprice  </jsp:expression>
</dbtags:username>  