<%-- 
    Document   : getproductsjsp
    Created on : 9 Aug, 2018, 6:05:10 PM
    Author     : Imran
--%>

<%@page import="com.imran.ProductsBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="com.imran.ProductsDao"%>
<%@page errorPage="error.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includejsp.jsp" flush="true" />

<%!

    ProductsDao dao = null;
    ProductsBean pb;
    public void init(){
        
        dao  =  new ProductsDao();
        
    }

%>

<td width = '82%' align='left' valign ='top'><p style='background-color:blue ; color : white;'>
        
 <form  method = 'post' action= "addProducts">     
     <table width='100%'  style='border-collapse: collapse ; border: 5px solid white;'>

         <tr style='position:sticky; height:50px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:lightgray ; color:white ; text-align:center'>
         
        <th width='8%' >check</th>
        
        <th width='24%'>Products Code</th>
        
        <th width='28%'>Products Name</th>
        
        <th width = '20%'>Available</th>
        
        <th width='20%'>Required</th>
        
        </tr>
        
        
        <%
         
        Collection prods = dao.getProducts();
            if(prods ==  null){
                
          %> 
        
          <tr style=' height:35px;  padding-top:20px ; padding-bottom:20px ; font-family:Arial ; background-color:#4CAF50 ; color:white ; text-align:center'><td colspan='5' align='center'>
            No Products Available    
            
          </td></tr>
          
          
        <%  
          
            }

            else  {
                
                Iterator i  = prods.iterator();
        
                while(i.hasNext())
                {
                    
                     pb   = (ProductsBean)i.next();

        %>
            
        <tr style=' height:35px;  padding-top:20px ; padding-bottom:20px ; font-family:verdana ; color:black ;background-color:white; text-align:left'>
        
        <td align='center'>
        
        <input type='checkbox' name='products' value=<%=pb.getPcode()%> >
         

        <input type='hidden' name=<%=pb.getPcode()+"Name"%> value=<%=pb.getPname()%> >
        
        
        <input type='hidden' name=<%=pb.getPcode()+"Cost"%> value=<%=pb.getPprice()%> >


        <td><%=pb.getPcode()%></td> 
        
        
        <td><%=pb.getPname().toLowerCase()%></td>
        
        
        <td><%= pb.getPqty()%></td>
        
        <td>
        
            
            <input type='text' name = <%=pb.getPcode()%> >    
        
            
            </td></tr>
            
         <%        
        }
        
}
        %>
        
        
        </table>
        
        
        <br><center>
            
            
            <input style='width:150px;height:30px; color:white;  background-color:slateblue ; border-top:none; border-bottom :blue ; border-left:none ;border-right:none ;' type='submit' value='Add products to cart'>
        
        
        </center>
        
        
        
       </form> 
        
        
        </td></tr>
      
        <%@include file="copyright.html" %>
        
        
        
        
        
        
        