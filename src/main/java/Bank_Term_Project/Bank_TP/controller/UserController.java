package Bank_Term_Project.Bank_TP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Bank_Term_Project.Bank_TP.dao.UserDao;
import Bank_Term_Project.Bank_TP.model.User;


// this indicates theat this class is REST Controller
@RestController

// Specifies the base URL path for all endpoints in this controller
@RequestMapping("/api/users")

public class UserController {

	// this used to inject the "userService" dependency into the contorller
	@Autowired
	private UserDao userService;
	
	@RequestMapping("/login")
    public String login()
    {
    	return "login";
    }
	
	// Endpoint to handle user login
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
	    // Perform login logic using the userService
	    User user = userService.getUserByEmail(loginUser.getEmail());
	    
	    if (user != null && user.getPassword().equals(loginUser.getPassword()))  {
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	}	
	
	// Endpoint to get user by ID
		@GetMapping("/{id}")
		public ResponseEntity<User> getUserById(@PathVariable("id") int userId){
			User user = userService.getUserById(userId);
			if(user!= null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
}
