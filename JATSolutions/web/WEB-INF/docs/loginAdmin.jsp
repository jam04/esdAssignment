<%-- 
    Document   : test
    Created on : 09-Nov-2016, 14:48:50
    Author     : ja2-day
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/JATSolutions/docs/adminHome">
        <h3 align="left"> Login </h3>
        <input name="username" type="text" placeholder="Username" id="username"><br>
        <input name="password" type="password" placeholder="Password" id="password"><br>
        <button type="submit" class="btn btn-primary btn-block btn-large">Login</button>
        <input type="button" class="btn btn-primary btn-block btn-large" value = "Register"/>
        </form>
        
        <br>
        <%
            if(request.getAttribute("message") != null){
                out.print(request.getAttribute("message"));
            }
        %>
        <br>
        
    </body>
</html>
