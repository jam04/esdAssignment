<%-- 
    Document   : submitPayment
    Created on : 11-Nov-2016, 14:50:49
    Author     : r2-augustine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/JATSolutions/SubmitPaymentController.do">
        <h3 align="left"> Submit Payment</h3>
        <input name="username" type="text" placeholder="Username" id="username"><br><br>
        Type Of Payment:
        <br>
        <select name="typeOfPayment" size="1">
            <option value="Membership Fee"> Membership Fee</option>
            <option value="Claim Payment"> Claim Payment</option>
        </select>
        <br><br>
        <input name="paymentAmount" type="text" placeholder="Enter Amount" id="paymentAmount"><br><br>
        <button type="submit" class="btn btn-primary btn-block btn-large">Submit Payment</button>  
        </form>
    <td><a href="/JATSOlutions/docs/userHome">Dashboard</a>
    </body>
</html>
