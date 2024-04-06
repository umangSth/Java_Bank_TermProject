<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            width: 50%;
            margin: 20px auto;
            padding: 20px;
            background-color: #f2f2f2;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
        }
        td {
            padding: 8px;
        }
        input[type="text"],
        input[type="number"] {
            width: calc(100% - 120px);
            padding: 6px;
            margin: 4px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Transaction Form</h1>
    <form:form action="/withdrawdepositAction" method="post">
        <table>
            <tr>
                <td>Transaction Type:</td>
                <td>
                    <form:select path="fromAccountType">
                        <form:option value="WITHDRAW">Withdraw</form:option>
                        <form:option value="DEPOSIT">Deposit</form:option>
                      </form:select>
                </td>
            </tr>
             <tr>
                <td>Transaction Status:</td>
                <td><form:input type="text" path="transactionStatus" value="COMPLETED" readonly /></td>
            </tr>
            <tr>
                <td>Amount:</td>
                <td><form:input type="number" path="amount"/></td>
            </tr>
      
            <tr>
                <td></td>
                <td><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form >
</body>
</html>