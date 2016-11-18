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
<h1>All Payments</h1>
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