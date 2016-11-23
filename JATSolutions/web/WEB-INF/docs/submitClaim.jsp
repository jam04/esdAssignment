<%-- 
    Document   : submitClaim
    Created on : 11-Nov-2016, 14:51:51
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
        <form action="/JATSolutions/SubmitClaimController.do">
            <h3 align="left"> Submit a Claim </h3>
            <input name="claimAmount" type="text" placeholder="Enter claim amount" id="amount"><br><br>
            <textarea name="claimRationale" cols="40" rows="5" placeholder="Enter rationale for claim" id="rationale" ></textarea><br><br>
            <button type="submit" class="btn btn-primary btn-block btn-large">Submit Claim</button>
            <a href="/JATSolutions/docs/home">Dashboard</a>
            <br>
            <%
                if (request.getAttribute("message") != null) {
                    out.print(request.getAttribute("message"));
                }
            %>
            <br>
        </form>
    </body>
</html>
