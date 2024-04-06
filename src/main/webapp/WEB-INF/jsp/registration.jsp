<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
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
            /* float: right; */
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
<body>
    <div class="card">
        <form:form method="post" action="saveUser">  
            <h1>Registration</h1>
            <div id="errorContainer"></div>
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
                    <td><form:input type="text" path="phone" /></td>
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
            <a href="/TermProject_Bank/">Back to Register page</a>
        </form:form>
    </div>
    <script>
   window.onload = function() {
   	console.log("here i am ")
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
