package Bank_Term_Project.Bank_TP;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Bank_Term_Project.Bank_TP.config.DatabaseConfig;
import Bank_Term_Project.Bank_TP.dao.UserDao;
import Bank_Term_Project.Bank_TP.dao.UserDaoImpl;
import Bank_Term_Project.Bank_TP.model.User;
import Bank_Term_Project.Bank_TP.service.UserService;
import Bank_Term_Project.Bank_TP.service.UserServiceImpl;

public class UserServiceIntegrationTest {

    private UserService userService;
    private UserDao userDao;

    @Before
    public void setUp() {
        // Create a Spring application context with the DatabaseConfig
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        
        // Retrieve the DataSource bean from the context
        DataSource dataSource = context.getBean(DataSource.class);
        
        // Create a real UserDao implementation connected to the configured DataSource
        userDao = new UserDaoImpl(dataSource);
        
        // Create an instance of UserService using the real UserDao
        userService = new UserServiceImpl(userDao);
        
        // Close the context after the test setup is complete
        context.close();
    }
    
    @Test
    public void testGetUserById() {
        // Assuming there is a user with ID 1 in the database
        User user = userService.getUserById(1); 
        // Assert that the retrieved user's email matches the expected email
        assertEquals("admin@test.com", user.getEmail());
    }
    
    
    @Test
    public void testAddUser() {
        // Create a new user with dummy data
        User user = new User();
        user.setName("John Doe");
        user.setPassword("password");
        user.setEmail("john.doe@example.com");
        user.setPhone("1234567890");
        user.setAddress("123 Main St");
        user.setUser_type(User.UserType.ADMIN); // Set a user type to avoid NullPointerException
        
        // Call the createUser method to add the user
        User createdUser = userService.createUser(user);
        
        // Check if the user was successfully added
        assertNotNull(createdUser);
        
        // Optionally, assert specific properties of the created user
        assertEquals("John Doe", createdUser.getName());
        assertEquals(User.UserType.ADMIN, createdUser.getUser_type());
        // Add additional assertions if needed
    }

    
    @Test
    public void testGetByEmail() {
        
        User user = userService.getUserByEmail("john.doe@example.com");
        // Assert that the retrieved user's name matches the expected name
        assertEquals("John Doe", user.getName());
    }
    
    @Test
    public void testUpdateByEmail() {
        
        User user = userService.getUserByEmail("john.doe@example.com");
        // Update the user's name
        user.setName("Jane Doe");
        // Call the updateUser method to update the user
        User updatedUser = userService.updateUser(user.getId(), user);
        // Assert that the user was updated successfully
        assertNotNull(updatedUser);
        assertEquals("Jane Doe", updatedUser.getName());
        // Add additional assertions if needed
    }
    
    @Test 
    public void testDeleteUser() {
    	User user = userService.getUserByEmail("john.doe@example.com");
    	
        boolean isDeleted = userService.deleteUser(user.getId());
        // Assert that the user was deleted successfully
        assertTrue(isDeleted);
        // Add additional assertions if needed
    }
	
}
