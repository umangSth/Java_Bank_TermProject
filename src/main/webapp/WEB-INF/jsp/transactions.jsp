<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transactions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px;  
            border-collapse: collapse;
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
            text-align: left;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .alert {
            padding: 15px;
            border: 1px solid transparent;
            border-radius: 4px;
            margin-bottom: 20px;
        }

        .alert-warning {
            color: #856404;
            background-color: #fff3cd;
            border-color: #ffeeba;
        }
    </style>
</head>
<body>
    <h1>Transactions</h1>
    <table>
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
