<%-- 
    Document   : test2
    Created on : 09-Nov-2016, 14:53:28
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
        <form action="/JATSolutions/RegistrationController.do">
        <h3 align="left"> Register </h3>
        <input name="name" type="text" placeholder="Name" id="name"><br>
        <input name="address" type="text" placeholder="Address" id="address"><br>
        <input name="dob" type="text" placeholder="Date of Birth: dd-mm-yyyy" id="date"><br>
        <button type="submit" class="btn btn-primary btn-block btn-large">Register</button>
        <a href="/JATSolutions/docs/login">Cancel</a>
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
