<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
                <td></td>  
                <td><input type="submit" value="login" /></td>
            </tr>
        </table>
        
        <a href="/registration">To Register page</a>
    </form:form>
    
    
</body>
</html>