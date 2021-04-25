package model;

public class UserAccount {
	private String username;
	private String user_type;
	
	public UserAccount(String username, String user_type) {
		super();
		this.username = username;
		this.user_type = user_type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	
}
