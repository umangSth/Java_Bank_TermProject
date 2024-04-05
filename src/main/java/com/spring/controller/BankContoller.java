package com.spring.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.beans.Account;
import com.spring.beans.InternalFundTransfer;
import com.spring.beans.Transaction;
import com.spring.beans.User;
import com.spring.dao.AccountDao;
import com.spring.dao.TransactionDao;
import com.spring.dao.UserDao;
@Service
@Transactional
@Controller
public class BankContoller {

	@Autowired
	UserDao user_dao;
	@Autowired
	AccountDao account_dao;
	@Autowired
	TransactionDao transaction_dao;
	
	// user view controller section
	//  ========> start
	
	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	 @RequestMapping(value="/loginAction", method = RequestMethod.POST)
	    public String loginAction(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
	        // Check if the user exists in the database
	        User user = user_dao.getUserByEmail(email, password);
	        		
	        if (user != null) {
	            // Start a session and save the email
	            HttpSession session = request.getSession();
	            session.setAttribute("email", email);
	            session.setAttribute("name", user.getName());
	            session.setAttribute("user_id", user.getId());
	            boolean accountExists = account_dao.checkAccountHasType(user.getId(),null);
		         if (accountExists) {
		        	 return "redirect:/index/"+(user.getId()); // Redirect to the home page after successful login 
		            
		         }else {           
		        	 return "redirect:/registerAccount?owner_id="+user.getId()+"&message=no_account"; // Redirect back to the registration form if no account found
		         }
	            
	        } else {
	            // User authentication failed, redirect back to the login page with an error message
	            return "redirect:/?error";
	        }
	    }
	 
	 
	 @RequestMapping("/registration")
		public String showform(Model m) {
			m.addAttribute("command", new User()); // addAttributes() method add values in the Model that'll be identified globally.
			return "registration";
		}
	 
	 
	 @RequestMapping(value="/saveUser", method = RequestMethod.POST)
	 public String saveUser(@ModelAttribute("User") User user) {
	
		    // Check if the email is already registered
		    if (!isEmailUnique(user.getEmail())) {
		        return "redirect:/registration?error=not_unique_email";
		    }
		    // If email is unique, proceed with user registration
		    user_dao.save(user);
		    User u = user_dao.getUserByEmail(user.getEmail(), user.getPassword());
		    
		    return "redirect:registerAccount?owner_id="+u.getId(); // Redirect to login page after successful registration
		 }	 
	 
	 private boolean isEmailUnique(String email) {
		    // Query the database to check if the email is unique
		   
		    return user_dao.isEmailUnique(email);
		}
	// user view controller section
	//  ========> ends
	
	 
	 // account controller section
	 // --------> starts
	 
	 
	 @RequestMapping(value="/registerAccount")
	 public String showAccountForm(@RequestParam(value = "owner_id", required = false) Integer owner_id, HttpSession session, Model m) {
	     if (owner_id == null) {
	         // Check if user_id is present in the session
	         Integer user_id = (Integer) session.getAttribute("user_id");
	         if (user_id != null) {
	             // Use user_id from session attribute as owner_id
	             m.addAttribute("owner_id", user_id);
	         } else {
	             
	             return "redirect:/error"; // Redirect to error page
	         }
	     } else {
	         // Use owner_id provided as request parameter
	         m.addAttribute("owner_id", owner_id);
	     }
	     
	     m.addAttribute("account", new Account());
	     return "registerAccount";
	 }


	 @RequestMapping(value="/registerAccountAction", method = RequestMethod.POST)
	 public String registerAccountAction(@ModelAttribute("account") Account account, HttpSession session, RedirectAttributes redirectAttributes) {
	     try {
	    	 String accType = account_dao.mapAccTypeToString(account.getAccountType());
	         // Check if the account of the same type already exists for the owner
	         boolean accountExists = account_dao.checkAccountHasType(account.getOwner_id(), accType);
	         if (accountExists) {
	             // Add error message to be displayed on the registration page
	             redirectAttributes.addFlashAttribute("error", "An account of the same type already exists.");
	             return "redirect:/registerAccount?error=An_account_of_the_same_type_already_exists."; // Redirect back to the registration form
	         }

	         // Perform validation (e.g., check for null values, validate account type, validate balance)
	         // Save the account details
	         account_dao.saveAccount(account);
	         Integer user_id = (Integer) session.getAttribute("user_id");
	         if (user_id != null) {
	        	 return "redirect:/index/"+(user_id); 
	         }else {
		         return "redirect:/?Account=success"; // Redirect to the home page after successful registration	        	 
	         }

	     } catch (Exception e) {
	         // Log the exception for debugging purposes
	         e.printStackTrace();
	         // Add error message to be displayed on the registration page
	         redirectAttributes.addFlashAttribute("error", "An error occurred while registering the account. Please try again.");
	         return "redirect:/registerAccount"; // Redirect back to the registration form
	     }
	 }
	 
	 // -------> Accounts controller ends
	 
	 // 
	 
	 @RequestMapping("/index/{id}")
	 public String index(@PathVariable int id, Model model, HttpServletRequest request) {
	     HttpSession session = request.getSession(false);
	     if (session != null && session.getAttribute("email") != null) {
	         String name = (String) session.getAttribute("name"); // Move this line heres
	         Map<String, BigDecimal> accounts = account_dao.retrieveBalances(id);
	         model.addAttribute("accounts", accounts);
	         model.addAttribute("name", name);
	         return "index";
	     } else {
	         return "redirect:/";
	     }
	 }

	 // ------> Accounts controller ends
	 
	 // -------> Transaction controller starts
	 
	 @RequestMapping("/transactions/{accountType}")
	 public String transactions(@PathVariable String accountType, Model model, HttpServletRequest request) {
	     HttpSession session = request.getSession(false);
	     if (session != null && session.getAttribute("user_id") != null) {
	         Integer user_id = (Integer) session.getAttribute("user_id");
	         
	         List<Transaction> transactions = transaction_dao.getTransactions(user_id, accountType);
	         if (transactions.isEmpty()) {
	             return "redirect:/index/" + user_id;
	         } else {
	             model.addAttribute("transactions", transactions);
	             return "transactions";
	         }
	     } else {
	         // Redirect to the index page if the user is not logged in
	         return "redirect:/";
	     }
	 }
	 
	 @RequestMapping("/internalTransfer")
		public String showInternalTransfer(Model m) {
			m.addAttribute("command", new InternalFundTransfer()); // addAttributes() method add values in the Model that'll be identified globally.
			return "internalTransfer";
		}

	 @RequestMapping(value="/internalTransferAction", method = RequestMethod.POST)
	 public String registerAccountAction(@ModelAttribute("internalFundTransfer") InternalFundTransfer internalFund, RedirectAttributes redirectAttributes,  HttpServletRequest request) {
	     try {
	    	 HttpSession session = request.getSession(false);
		     if (session != null && session.getAttribute("user_id") != null) {
		    	 int owner_id = (int) session.getAttribute("user_id");
		    	
		    	 String fromAcc = account_dao.mapAccTypeToString(internalFund.getFromAccType());
		    	 String toAcc = account_dao.mapAccTypeToString(internalFund.getToAccType());
		    	 
		         // Check if the account of the same type already exists for the owner
		         boolean fromAccountExists = account_dao.checkAccountHasType(owner_id, fromAcc);
		         boolean toAccountExists = account_dao.checkAccountHasType(owner_id, toAcc);
		        
		         if (!fromAccountExists && !toAccountExists) {
		          
		             return "redirect:/index/"+owner_id+"?error=no_account_of_given_type_founds"; // Redirect back to the index
		         }
		         
		         
		         // Perform validation (e.g., check for null values, validate account type, validate balance)
		         // Save the account details
		         int flag = transaction_dao.internalTransfer(owner_id, fromAcc, toAcc, internalFund.getAmount());
		         if(flag == 0) {
		        	 return "redirect:/index/"+owner_id+"?message=insufficent_fund";
		         }
		         return "redirect:/index/"+owner_id; // Redirect to the home page after successful registration
		    	 
		     }else {
		    	  // Redirect to the index page if the user is not logged in
		         return "redirect:/?error=no_session";
		     }
	    	
	     } catch (Exception e) {
	         // Log the exception for debugging purposes
	         e.printStackTrace();
	         // Add error message to be displayed on the registration page
	         redirectAttributes.addFlashAttribute("error", "An error occurred while registering the account. Please try again.");
	         return "redirect:/"; // Redirect back to the registration form
	     }
	 }

	 

	 
	 // -------> Transaction controller ends
	
}
