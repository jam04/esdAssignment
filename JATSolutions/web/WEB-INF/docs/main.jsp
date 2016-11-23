<%-- 
    Document   : mainview
    Created on : 09-Nov-2016, 12:12:32
    Author     : James
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ Assoc</title>
    </head>
    <body>
        
        <%!
           String forecast;
           String[] forecastList;
        %>
        
        <h1>----- XYZ ASSOC-------</h1>
        <%
            String name;
            if (session.getAttribute("userName") == null) {
                name = "Guest";
            } else {
                name = (String) session.getAttribute("userName");
            }
        %>
        <h2>Signed in as: <%=name%> - 
            <% if (name != "Guest") {
                    out.print("<a href=\"/JATSolutions/docs/signOut\">(Sign Out)</a>");
                }
            %></h2>
            <% String include = (String) request.getAttribute("doco");%>
            
            <br>
            
            <%
                
                forecast = (String) request.getAttribute("weatherReport");
                forecastList = forecast.split(",");
                
                out.print("The weather today is: " + forecastList[0] + 
                        ". Tomorrow it will be: " + forecastList[1] +
                        " and the day after that is: " + forecastList[2]);
            %>
            
            <jsp:include page="<%=include%>" flush="true" /> 
    </body>
</html>
