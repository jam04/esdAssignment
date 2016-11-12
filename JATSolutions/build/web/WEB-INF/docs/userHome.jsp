<%-- 
    Document   : userHome
    Created on : 12-Nov-2016, 15:20:20
    Author     : jm2-wright
--%>

<%@page import="model.Member"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DBController"%>
        <h1>FOOTER!</h1>
<%! DBController jdbc; 
    Member currentMember;
%>

<%
jdbc = new DBController((Connection) application.getAttribute("connection"));
currentMember = jdbc.getMember(request.getParameter("username"));
%>

<h2>Welcome <%= currentMember.getId()%>, this is your home page </h3>

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
