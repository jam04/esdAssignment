<%-- 
    Document   : userHome
    Created on : 12-Nov-2016, 15:20:20
    Author     : jm2-wright
--%>

<%@page import="model.Payment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Claim"%>
<%@page import="model.Member"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DBController"%>

    <h1>Welcome to your Dashboard</h1>
        
<%! DBController jdbc; 
    Member currentMember;
    ArrayList<Claim> myClaims;
    ArrayList<Payment> myPayments;
%>

<%
jdbc = new DBController((Connection) application.getAttribute("connection"));
currentMember = jdbc.getMember(request.getParameter("username"));
myClaims = jdbc.claimList(request.getParameter("username"));
myPayments = jdbc.paymentList(request.getParameter("username"));
%>

<h2>Welcome <%= currentMember.getId()%>, this is your home page </h2>

<table>
  <tr>
    <th>Name: </th>
    <th><%= currentMember.getName() %></th>
  </tr>
  <tr>
    <td>Address: </td>
    <td><%= currentMember.getAddress() %></td>
  </tr>
  <tr>
    <td>Date of Birth: </td>
    <td><%= currentMember.getDob()%></td>
  </tr>
  <tr>
    <td>Registered Since: </td>
    <td><%= currentMember.getDor()%></td>
  </tr>
  <tr>
    <td>Current Status: </td>
    <td><%= currentMember.getStatus()%></td>
  </tr>
  <tr>
    <td>Current Balance: </td>
    <td><%= currentMember.getBalance()%></td>
  </tr>
</table>
  
  <h2> Your Claims </h2>
          <table>
            <tr>
                <th>Claim ID</th>
                <th>Date Of Claim</th> 
                <th>Rationale</th>
                <th>Status</th>
                <th>Amount</th>
            </tr>
        <%
            for (Claim eachClaim : myClaims) {
                out.println("<tr>");
                out.println("<td>" + eachClaim.getId() + "</td>");
                out.println("<td>" + eachClaim.getDate() + "</td>");
                out.println("<td>" + eachClaim.getRationale() + "</td>");
                out.println("<td>" + eachClaim.getStatus() + "</td>");
                out.println("<td>£" + eachClaim.getAmount() + "</td>");
                out.println("</tr>");
            }
        %>
        </table>
	
        <div><a href="/JATSolutions/docs/submitClaim">Submit a Claim</a></div>

  <h2> Your Payments </h2>
          <table>
            <tr>
                <th>Payment ID</th>
                <th>Date Of Payment</th> 
                <th>Type of Payment</th>
                <th>Status</th>
                <th>Amount</th>
            </tr>
        <%
            for (Payment eachPayment : myPayments) {
                out.println("<tr>");
                out.println("<td>" + eachPayment.getId() + "</td>");
                out.println("<td>" + eachPayment.getDate() + "</td>");
                out.println("<td>" + eachPayment.getTypeOfPayment() + "</td>");
                out.println("<td>£" + eachPayment.getAmount() + "</td>");
                out.println("</tr>");
            }
        %>
        </table>
	
        <div><a href="/JATSolutions/docs/submitPayment">Submit a Payment</a></div>
  
