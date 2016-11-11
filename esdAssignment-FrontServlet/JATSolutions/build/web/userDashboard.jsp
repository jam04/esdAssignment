<%-- 
    Document   : userDashboard
    Created on : 07-Nov-2016, 16:11:23
    Author     : James
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="model.*"%>
<!DOCTYPE html>
<html>
    <%! Member thisMember = new Member();%>
    <%
        thisMember.setAddress("Dury Lane");
        thisMember.setBalance((float)50.00);
        thisMember.setDob(new Date(622581559));
        thisMember.setDor(new Date(1478537345));
        thisMember.setId(13);
        thisMember.setName("The Muffin Man");
        thisMember.setStatus("inactive");
    %>
    <%! Member foundMember ;%>
    <%
        foundMember = (Member) request.getAttribute("foundMember");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello <%= thisMember.getName() %></h1>
        <h2>This is your dashboard</h2>
        <p>
            Current Address: <%= thisMember.getAddress()%>
        </p>
        <p>
            Balance: <%= thisMember.getBalance()%>
        </p>
        <p>
            Current Status: <%= thisMember.getStatus()%>
        </p>
    </body>
</html>
