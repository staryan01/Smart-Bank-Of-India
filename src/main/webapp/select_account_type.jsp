<%@page import="Dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome_to_account_creation_page</h1>
<% Customer customer =(Customer) request.getSession().getAttribute("customer"); %>
<h1> Hello:Dear <%= customer.getCname() %> </h1>
<form action="createbankaccount">
<input type="radio" name="accounttype" value="savings" required="required">Savings
<input type="radio" name="accounttype" value="current" required="required">Current<br><br>
<button>Select</button><button type="reset">Cancel</button>

</form>
</body>
</html>