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
 <form:form method="post" action="saveUser">  
        <h1>Registration</h1>
        <table>
            <tr>
                <td>Name:</td> 
                <td><form:input type="text" path="name" /></td>
            </tr>  
            <tr>
                <td>Email:</td>  
                <td><form:input type="text" path="email" /></td>
            </tr> 
            <tr>
                <td>Phone:</td>  
                <td><form:input type="text" path="price" /></td>
            </tr>
            <tr>
                <td>Address:</td>  
                <td><form:input type="text" path="address" /></td>
            </tr> 
              <tr>
                <td>Password:</td>  
                <td><form:input type="password" path="password" /></td>
            </tr> 
             <tr>
	            <td>User Type:</td>
	            <td>
	                <form:select path="user_type">
	                    <form:option value="ADMIN">Admin</form:option>
	                    <form:option value="CLIENT">Client</form:option>
	                </form:select>
	            </td>
	        </tr>
            <tr>
                <td></td>  
                <td><input type="submit" value="Save" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>