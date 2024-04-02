package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.beans.Product;
import com.spring.beans.User;
import com.spring.dao.UserDao;

@Controller
public class BankContoller {

	@Autowired
	UserDao user_dao;
	
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
	            return "redirect:/index"; // Redirect to the home page after successful login
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
		 System.out.println((!isEmailUnique(user.getEmail())));
		 System.out.println(user.getEmail());
		    if (!isEmailUnique(user.getEmail())) {
		        return "redirect:/registration?error=not_unique_email";
		    }
		    // If email is unique, proceed with user registration
		    user_dao.save(user);
		    return "redirect:/"; // Redirect to login page after successful registration
		 }
	 
	 
	 @RequestMapping("/index")
	 public String index(Model m, HttpServletRequest request) {
	     HttpSession session = request.getSession(false);
	     if (session != null && session.getAttribute("email") != null) {
	         return "index";
	     } else {
	         return "redirect:/";
	     }
	 }
	 
	 
	 private boolean isEmailUnique(String email) {
		    // Query the database to check if the email is unique
		   
		    return user_dao.isEmailUnique(email);
		}
	 
	
	
}
