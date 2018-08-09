<%-- 
    Document   : userhome
    Created on : 9 Aug, 2018, 5:44:20 PM
    Author     : Imran
--%>

<%@page import="com.imran.StoreDetails"%>
<%@page import="org.apache.catalina.Store"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
 
ServletContext sct;
 StoreDetails sd;
%>


<%
   
      sct = request.getServletContext();
       
       
       
       int log = (int) sct.getAttribute("logout");       
          
       System.out.println("in userjsp"+log);
       
         if(log == 1){
              
              
              RequestDispatcher rd  = request.getRequestDispatcher("ulogin.html");
              rd.forward(request,response);
              
              
          }
          else {
%>
<jsp:include page="includejsp.jsp" flush="true" />
 <%@include file="copyright.html" %>        
      
<%
      }

%>          
