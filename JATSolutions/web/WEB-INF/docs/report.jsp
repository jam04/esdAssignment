<%-- 
    Document   : report
    Created on : 18-Nov-2016, 17:07:09
    Author     : a5-jama
--%>
<%@page import="java.sql.Connection"%>
<%@page import="model.DBController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! ArrayList<Payment> incomeList;%>
<%! ArrayList<Claim> expenseList;%>

<%! DBController jdbc;%>
<%
    float incomeAmount = 0;
    float expenseAmount = 0;
    jdbc = new DBController((Connection) application.getAttribute("connection"));
    incomeList = jdbc.listIncome();
    expenseList = jdbc.listExpense();
%>
<h1>Report of annual turnover </h1>
<table>
            <tr>
                <th>Total Income</th>
                <th>Total Expense</th> 
                <th>Total Profit</th> 
            </tr>

<%
    for (Payment eachPayment : incomeList) {
        incomeAmount += eachPayment.getAmount();
    }
    out.println("<tr>");
    out.println("<td>£" + incomeAmount+ "</td>");
%> 

<%
    for (Claim eachClaim : expenseList) {
        expenseAmount += eachClaim.getAmount();
    }
    out.println("<td>£" + expenseAmount+ "</td>");
%>   

<%
    out.println("<td>£" + (incomeAmount/ expenseAmount)+ "</td>");
    out.println("</tr>");
%>   
</table>

<div><a href="/JATSolutions/docs/home">Admin Dashboard</a></div>