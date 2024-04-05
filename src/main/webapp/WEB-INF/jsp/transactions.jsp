<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transactions</title>
</head>
<body>
    <h1>Transactions</h1>
    <table border="1">
        <tr>
            <th>Transaction ID</th>
            <th>Transaction Type</th>
            <th>To Account</th>
            <th>From Account</th>
            <th>Amount</th>
            <th>Transaction Date</th>
            <th>Transaction Status</th>
        </tr>
        <c:forEach var="transaction" items="${transactions}">
            <tr>
                <td>${transaction.transactionId}</td>
                <td>${transaction.transactionType}</td>
                <td>${transaction.toAccount}</td>
                <td>${transaction.fromAccount}</td>
                <td>${transaction.amount}</td>
                <td>${transaction.transactionDate}</td>
                <td>${transaction.transactionStatus}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>