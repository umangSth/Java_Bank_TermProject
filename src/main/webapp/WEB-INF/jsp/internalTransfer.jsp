<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
    <title>Internal Transaction</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 400px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        form {
            width: 100%;
        }
        form table {
            width: 100%;
        }
        form table tr td {
            padding: 10px 0;
        }
        input[type="text"],
        input[type="number"],
        select {
            width: calc(100% - 120px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s ease-in-out;
        }
        input[type="text"]:focus,
        input[type="number"]:focus,
        select:focus {
            outline: none;
            border-color: #008cba;
        }
        input[type="submit"] {
            background-color: #008cba;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
            transition: background-color 0.3s ease-in-out;
        }
        input[type="submit"]:hover {
            background-color: #005f77;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Internal Transfer</h1>
        <form:form method="post" action="/TermProject_Bank/internalTransferAction">  
            <table>
                 <tr>
                    <td>From Account:</td>  
                    <td>
                        <form:select path="fromAccType">
                            <form:option value="CHECKING">CHECKING</form:option>
                            <form:option value="BUSINESS">BUSINESS</form:option>
                            <form:option value="SAVINGS">SAVINGS</form:option>
                        </form:select>
                    </td>
                </tr>  
                <tr>
                    <td>Amount:</td>  
                    <td><form:input type="number" path="amount" /></td>
                </tr> 
                <tr>
                    <td>To Account:</td> 
                    <td> 
                   <form:select path="toAccType">
                            <form:option value="CHECKING">CHECKING</form:option>
                            <form:option value="BUSINESS">BUSINESS</form:option>
                            <form:option value="SAVINGS">SAVINGS</form:option>
                        </form:select>
                        </td>
                </tr> 
                <tr>
                    <td></td>  
                    <td><input type="submit" value="Submits Transaction" /></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>