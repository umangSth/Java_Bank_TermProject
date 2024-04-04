<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <form:form method="post" action="registerAccountAction" modelAttribute="account">
        <h1>Registration</h1>
        <table>
          <tr>
    		<td>Owner ID:</td>
		    <td><form:input type="number" path="owner_id" value="${owner_id}" readonly="true" /></td>
		</tr>

            <tr>
                <td>Balance:</td>
                <td><form:input type="text" path="balance" /></td>
            </tr>
            <tr>
                <td>Account Type:</td>
                <td>
                    <form:select path="accountType">
                        <form:option value="CHECKING">Checking</form:option>
                        <form:option value="BUSINESS">Business</form:option>
                        <form:option value="SAVINGS">Savings</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save" /></td>
            </tr>
        </table>
    </form:form>
    <a href="/TermProject_Bank/registration">Back to Registration page</a>
</body>
</html>
