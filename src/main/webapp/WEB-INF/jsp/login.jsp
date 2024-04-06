<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
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
        .card {
            width: 300px;
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
        table {
            width: 100%;
        }
        table tr td {
            padding: 10px 0;
        }
        input[type="text"],
        input[type="password"] {
            width: calc(100% - 120px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s ease-in-out;
        }
        input[type="text"]:focus,
        input[type="password"]:focus {
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
            transition: background-color 0.3s ease-in-out;
        }
        input[type="submit"]:hover {
            background-color: #005f77;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #008cba;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="card">
        <form:form modelAttribute="user" method="POST" action="/TermProject_Bank/loginAction">  
            <h1>Login</h1>
            <table> 
                <tr>
                    <td>Email:</td> 
                    <td><form:input type="text" path="email" /></td>
                </tr>  
                <tr>
                    <td>Password:</td>  
                    <td><form:password path="password" /></td>
                </tr> 
                <tr>
                    
                    <td><input type="submit" value="Login" /></td>
                </tr>
            </table>
            
            <a href="/TermProject_Bank/registration">Don't have an account? Register here</a>
        </form:form>
    </div>
</body>
</html>
