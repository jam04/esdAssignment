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
        <h3 align="left"> Register </h3>
        <input name="id" type="text" placeholder="ID" id="id"><br>
        <input name="password" type="password" placeholder="Password" id="password"><br>
        <input name="name" type="text" placeholder="Name" id="name"><br>
        <input name="address" type="text" placeholder="Address" id="address"><br>
        <input name="dob" type="text" placeholder="Date yyyy-mm-dd" id="date"><br>
        <button type="submit" class="btn btn-primary btn-block btn-large">Register</button>
        <input type="button" class="btn btn-primary btn-block btn-large" value = "Cancel"/>
    </body>
</html>
