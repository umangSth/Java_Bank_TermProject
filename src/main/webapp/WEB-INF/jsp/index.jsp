<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Your Bank</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .welcome-message {
            text-align: center;
            margin-bottom: 20px;
        }
        .balance-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .balance-table th,
        .balance-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .balance-table th {
            background-color: #f2f2f2;
        }
        .options {
            display: flex;
            justify-content: center;
        }
        .option {
            margin: 0 10px;
        }
        .footer {
            text-align: center;
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Your Bank</h1>
        <div class="welcome-message">
            <p>Welcome, <strong>${name}</strong>!</p>
        </div>
        <h2>Your Account Balances</h2>
        <table class="balance-table">
            <tr>
                <th>Account Type</th>
                <th>Balance</th>
            </tr>
           <c:forEach var="entry" items="${accounts}">
			    <tr>
			        <td>${entry.key}</td> <!-- Access the key (account type) -->
			        <td>${entry.value}</td> <!-- Access the value (balance) -->
			    </tr>
			</c:forEach>
        </table>
        <div class="options">
            <div class="option">
                <a href="/profile">Profile</a>
            </div>
            <div class="option">
                <a href="/registerAccount">Open Accounts</a>
            </div>
            <div class="option">
                <a href="/transactions">Transactions</a>
            </div>
         
        </div>
        <div class="footer">
            <p>Need help? Contact support.</p>
        </div>
    </div>
</body>
</html>
