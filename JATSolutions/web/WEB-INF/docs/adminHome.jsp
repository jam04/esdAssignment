<%-- 
    Document   : adminHome
    Created on : 14-Nov-2016, 13:46:07
    Author     : James
--%>

<%@page import="java.sql.Connection"%>
<%@page import="model.DBController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Member"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%! ArrayList<Member> memberlist;%>
<%! DBController jdbc;%>
<%
    jdbc = new DBController((Connection) application.getAttribute("connection"));
    memberlist = jdbc.memberList();
%>

        <h1>Admin Pages: </h1>
        <a href="/JATSolutions/docs/listClaims">All Claims</a><br>
        <a href="/JATSolutions/docs/listApplications">Pending Member Applications</a><br>
        <a href="/JATSolutions/docs/listBalance">Outstanding Balances</a><br>
        <a href="/JATSolutions/docs/report">Report</a><br>
        <a href="/JATSolutions/docs/sendClaimFee">Send Claim Fee</a><br><br>
        
        <%
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        
        <h1>Member List: </h1>
        <table>
            <tr>
                <th>User Name</th>
                <th>Name</th> 
                <th>Outstanding Balance</th>
                <th>Status</th>
            </tr>
        <%
            for (Member eachMember : memberlist) {
                out.println("<tr>");
                out.println("<td>" + eachMember.getId() + "</td>");
                out.println("<td>" + eachMember.getName() + "</td>");
                out.println("<td>" + eachMember.getBalance() + "</td>");
                out.println("<td>" + eachMember.getStatus() + "</td>");
                out.println("</tr>");
            }
        %>
        </table>

