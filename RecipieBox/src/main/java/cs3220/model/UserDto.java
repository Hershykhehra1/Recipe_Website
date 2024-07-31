package cs3220.model;

public class UserDto {
	private Integer userId;
	private String email;
	private String userName;
	private String password;

	public UserDto() {
	}

	public UserDto(UserEntry user) {
		userId = user.getId();
		email = user.getEmail();
		userName = user.getName();
		password = user.getPassword();
	}

	public UserEntry newUser() {
		UserEntry user = new UserEntry();
		user.setId(userId);
		user.setEmail(email);
		user.setName(userName);
		user.setPassword(password);
		return user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
