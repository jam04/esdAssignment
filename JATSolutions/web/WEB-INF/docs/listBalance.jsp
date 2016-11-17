<%-- 
    Document   : listBalance
    Created on : 16-Nov-2016, 11:53:24
    Author     : a5-jama
--%>

<%@page import="java.sql.Connection"%>
<%@page import="model.DBController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%! ArrayList<Member> balanceList;%>
<%! DBController jdbc;%>
<%
    jdbc = new DBController((Connection) application.getAttribute("connection"));
    balanceList = jdbc.balanceList();
%>


<h1>Balance List</h1>
<table>
    <tr>
        <th>Name ID</th> 
        <th>User Name</th>
        <th>Balance</th>
    </tr>
    <%
        for (Member eachBalance : balanceList) {
            out.println("<tr>");
            out.println("<td>" + eachBalance.getId() + "</td>");
            out.println("<td>" + eachBalance.getName() + "</td>");
            out.println("<td>" + eachBalance.getBalance() + "</td>");
            out.println("</tr>");
        }
    %>
</table>

