<%-- 
    Document   : listAllClaims
    Created on : 15-Nov-2016, 12:22:14
    Author     : a5-jama
--%>

<%@page import="java.sql.Connection"%>
<%@page import="model.DBController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Claim"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%! ArrayList<Claim> claimList;%>
<%! DBController jdbc;%>
<%
    jdbc = new DBController((Connection) application.getAttribute("connection"));
    claimList = jdbc.claimList("");
%>


<h1>Claims List</h1>

<br>
<form action="/JATSolutions/ProcessClaimController.do">
    <input name="claimId" type="text" placeholder="ID" id="claimId"><br>
    <input type="radio" name="status" value="APPROVED" checked> Approve<br>
    <input type="radio" name="status" value="REJECTED"> Reject<br>
    <button type="submit" class="btn btn-primary btn-block btn-large">Submit</button>    
</form>
<br>

<%
    if(request.getAttribute("message") != null){
        out.println((String) request.getAttribute("message") + "<br>");
    }
%>

<table>
    <tr>
        <th>User Name</th>
        <th>Name ID</th> 
        <th>Date</th>
        <th>Rationale</th>
        <th>Amount</th>
        <th>Status</th>
    </tr>
    <%
        for (Claim eachClaim : claimList) {
            out.println("<tr>");
            out.println("<td>" + eachClaim.getId() + "</td>");
            out.println("<td>" + eachClaim.getMemID() + "</td>");
            out.println("<td>" + eachClaim.getDate() + "</td>");
            out.println("<td>" + eachClaim.getRationale() + "</td>");
            out.println("<td>" + eachClaim.getAmount() + "</td>");
            out.println("<td>" + eachClaim.getStatus() + "</td>");
            out.println("</tr>");
        }
    %>
</table>

<a href="/JATSolutions/docs/home">Admin Dashboard</a><br>