<%-- 
    Document   : submitPayment
    Created on : 11-Nov-2016, 14:50:49
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3 align="left"> Submit Payment</h3>
        Type Of Payment:
        <br>
        <select name="typeOfPayment" size="1">
            <option value="Membership Fee"> Membership Fee</option>
            <option value="Claim Payment"> Claim Payment</option>
        </select>
        <br><br>
        <input name="paymentAmount" type="text" placeholder="Enter Amount" id="paymentAmount"><br><br>
        <button type="submit" class="btn btn-primary btn-block btn-large">Submit Payment</button>  
    </body>
</html>
