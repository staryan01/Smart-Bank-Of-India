<%@page import="Dto.Bank_account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome_to_active_account_page</h1>

<% List<Bank_account>list =(List<Bank_account>)  request.getSession().getAttribute("list"); 

if(list.isEmpty())
{%>
	<h1>No_active_accounts_found</h1>

<%} else {%>

<h1>Select_bank_account</h1>


<% for(Bank_account bank_account : list) {%>

<a href="setActiveAccount?acno=<%=bank_account.getAcc_no() %>"><button><%=bank_account.getAcc_no() %></button></a>



<%}%>

<% } %>

</body>
</html>