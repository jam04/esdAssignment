<%-- 
    Document   : listPayments
    Created on : 16-Nov-2016, 12:02:28
    Author     : James
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Payment"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.DBController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! DBController jdbc;%>
<%! ArrayList<Payment> paymentList;%>
<%
    jdbc = new DBController((Connection) application.getAttribute("connection"));
    paymentList = jdbc.applicationList();
%>
<h1>Pending Member Applications</h1>

<br>
<form action="/JATSolutions/ProcessApplicationController.do">
    <input name="paymentId" type="text" placeholder="ID" id="paymentId"><br>
    <input type="radio" name="status" value="APPROVED" checked> Approve<br>
    <button type="submit" class="btn btn-primary btn-block btn-large">Submit</button>    
</form>
<br>

<table>
            <tr>
                <th>Payment ID</th>
                <th>Username</th>
            </tr>
<%
    for(Payment eachPayment: paymentList){
        out.println("<tr>");
        out.println("<td>" + eachPayment.getId() + "</td>");
        out.println("<td>" + eachPayment.getMemID() + "</td>");
        out.println("</tr>");
    }
    
%>
</table>

   <div><a href="/JATSolutions/docs/home">Admin Dashboard</a></div>