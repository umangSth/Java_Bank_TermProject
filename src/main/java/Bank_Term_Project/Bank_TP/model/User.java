package Bank_Term_Project.Bank_TP.model;

public class User {
	private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private UserType user_type;
    
	public User(int id, String name, String password, String email, String phone, String address, UserType user_type) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.user_type = user_type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public UserType getUser_type() {
		return user_type;
	}
	public void setUser_type(UserType user_type) {
		this.user_type = user_type;
	}
	
	 public enum UserType {
	        ADMIN,
	        CLIENT
	    }

}
