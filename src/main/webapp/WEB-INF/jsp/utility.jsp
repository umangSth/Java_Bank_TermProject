<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
            width: 40%;
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
        input[type="number"],
        input[type="text"] {
            width: calc(100% - 120px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            transition: border-color 0.3s ease-in-out;
        }
        input[type="number"]:focus,
        input[type="text"]:focus {
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
<div class="card">
  <h1>Pay Your Utility:</h1>
   <div id="errorContainer"></div>
    <form:form action="/TermProject_Bank/utilityAction" method="post" modelAttribute="transaction">
        <table>
            <tr>
                <td>Transaction Type:</td>
                <td>
                    <form:select path="transactionType">
                            <form:option value="UTILITY">UTILITY</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>From Account:</td>
                <td>
                    <form:select path="fromAccountType">
                        <form:option value="CHECKING">Checking</form:option>
                        <form:option value="BUSINESS">Business</form:option>
                        <form:option value="SAVINGS">Savings</form:option>
                    </form:select>
                </td>
            </tr>
             <tr>
                <td>Utility Type:</td>
	            <td>
				    <select >
				        <option value="ELECTRICITY">Electricity</option>
				        <option value="WATER">Water</option>
				        <option value="GAS">Gas</option>
				        <option value="INTERNET">Internet</option>
				    </select>
				</td>

            </tr>
            <tr>
                <td>Amount:</td>
                <td><form:input type="number" path="amount" /></td>
            </tr>
            <tr>
                <td>Transaction Status:</td>
                <td>
                    <form:select path="transactionStatus">
                        <form:option value="PENDING">Pending</form:option>
                    </form:select>
                </td>
            </tr>
           
            
            <tr>
                <td></td>
                <td><input type="submit" value="Submit" /></td>
            </tr>
        </table>
    </form:form>
   </div>
   <script>
   window.onload = function() {
   
       var errorContainer = document.getElementById("errorContainer");
       var urlParams = new URLSearchParams(window.location.search);
       var errorMessage = urlParams.get('error');

       if (errorMessage) {
           var alertDiv = document.createElement("div");
           alertDiv.classList.add("alert", "alert-warning");
           alertDiv.textContent = errorMessage;
           errorContainer.appendChild(alertDiv);

        // Hide the alert after 5 seconds
           setTimeout(() => {
               alertDiv.style.display = 'none';
               // Remove the error parameter from the URL
               const newUrl = window.location.pathname + window.location.search.replace(/[\?&]error=[^&]+/, '');
               history.replaceState({}, '', newUrl);
           }, 3000);
       }
   };
   
   </script>
</body>
</html>