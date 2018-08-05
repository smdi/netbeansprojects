<%-- 
    Document   : insert
    Created on : 5 Aug, 2018, 12:18:43 PM
    Author     : Imran
--%>

<%@page import="com.imran.DbOperations"%>
<%@page import="com.imran.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.sql.*" %>

<jsp:include page="insert.html" flush="true"/>

<%!
   
    private DbOperations dbo = null ;
    
    private String pname ;
   
    int count;
  
    private int pcode , pqty , pprice;
    
    static Connection con =null ;
  
    static Statement stmt = null ;

    ResultSet rs = null;
   
    ResultSetMetaData rsmd =  null; 
 
static{
    try { 
         con  = DBConnection.getCon();
 
         stmt = con.createStatement();

        }
  catch(Exception e){
      
      e.printStackTrace();
  }

}

public void init(){
        
        dbo  =  new DbOperations();
    
    }

%>


<%

try  {
    
        
            pname  = request.getParameter("pname");
            pcode  = Integer.parseInt(request.getParameter("pcode"));
            pqty  = Integer.parseInt(request.getParameter("pqty")); 
            pprice  = Integer.parseInt(request.getParameter("pprice"));
            
            boolean done  =  dbo.operate("insert into products values("+pcode+",'"+ pname +"',"+pqty+","+pprice+")");
        
            if(done){
               
                
               
                out.println(" Inserted Records Successfully !!!");
                out.println();
                out.println();
                out.println();
            }
            else{
                
                
                out.println(" Unable to insert the records !!!");
                out.println();
                out.println();
                out.println();
            }
     
            
            rs   =  stmt.executeQuery("select * from products");
            
            rsmd = rs.getMetaData();
            
            count= rsmd.getColumnCount();

 %>   
    
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
 <table width='100%'  style='border-collapse: collapse ; border: 5px solid white;'>
 <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>
 
 <%   
 
   for(int i = 1 ;i<=count ; i++){

 %>    
     
  <th width='24%'><%=rsmd.getColumnName(i)%></th> 

  <%
  
   }
  %>
 
  </tr>
 <%
    
   while(rs.next()){
       
 %> 
  <tr style=' height:35px;  padding-top:20px ; padding-bottom:20px ; font-family:verdana ; color:black ;background-color:white; text-align:left'>

    <%   
 
   for(int i = 1 ;i<=count ; i++){

 %>    
     
  <td align='center' width='24%'><%= rs.getString(i).toLowerCase()%></td> 

  <%
  
   }
  %>
 
  </tr>  
      
<%

}
%>
</table></body></html>  
<%  
}
catch( Exception e){

        e.printStackTrace();
}

%>
<br><br><br>


















