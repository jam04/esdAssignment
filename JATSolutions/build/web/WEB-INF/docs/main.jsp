<%-- 
    Document   : mainview
    Created on : 09-Nov-2016, 12:12:32
    Author     : James
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% String include = (String) request.getAttribute("doco");%>
    <jsp:include page="<%=include%>" flush="true" /> 
    </body>
</html>
