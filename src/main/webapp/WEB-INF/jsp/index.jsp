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
            background-color: #f0f0f0; /* Light gray background color */
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff; /* White container background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Drop shadow effect */
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333; /* Dark text color */
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
            padding: 12px; /* Increased padding */
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
        .option a {
            text-decoration: none;
            color: #333; /* Dark text color for links */
            padding: 10px 20px;
            border: 1px solid #333; /* Dark border */
            border-radius: 5px;
            transition: all 0.3s ease; /* Smooth transition */
        }
        .option a:hover {
            background-color: #333; /* Dark background on hover */
            color: #fff; /* White text color on hover */
        }
        .footer {
            text-align: center;
            margin-top: 50px;
            color: #666; /* Medium gray text color */
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
    <div class="container">
        <h1>Welcome to Your Bank</h1>
        <div class="welcome-message">
            <p>Welcome, <strong>${name}</strong>!</p>
            <div id="errorContainer"></div>
        </div>
        <h2>Your Account Balances</h2>
        <table class="balance-table">
            <tr>
                <th>Account Type</th>
                <th>Balance</th>
                <th>Transaction</th>
                <th>Actions</th>
            </tr>
           <c:forEach var="entry" items="${accounts}">
			    <tr>
			        <td>${entry.key}</td> <!-- Access the key (account type) -->
			        <td>${entry.value}</td> <!-- Access the value (balance) -->

			        <td>
			        	<a href="/TermProject_Bank/transactions/${entry.key}">Transactions</a>
			        </td>
			        <td>
			        	<a href="/TermProject_Bank/withdraw_deposit/${entry.key}">Withdraw/Deposit</a>			        
			        </td>
			       
			  
			    </tr>
			</c:forEach>
        </table>
        <div class="options">
          
            <div class="option">
                <a href="/TermProject_Bank/registerAccount">Open Accounts</a>
            </div>
            <div class="option">
            	<a href="/TermProject_Bank/internalTransfer/">Internal Transfer</a>
            </div>
            
            <div class="option">
            	<a href="/TermProject_Bank/e_interac/">E-interac</a>
            </div>
            
             <div class="option">
            	<a href="/TermProject_Bank/utility/">Pay Utility</a>
            </div>
            
            
            <div class="option">
            	<a href="/TermProject_Bank/logout/">Logout</a>
            </div>
            
            
            <!-- Empty div removed -->
        </div>
        <div class="footer">
            <p>Need help? Contact support.</p>
        </div>
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

