package Entity;

public class AdminUser {
	private String adminUser_userId;
	private String adminUser_username;
	private String adminUser_password;
	private String adminUser_userRole;

	public AdminUser() {
		
	}
	// Constructor
	public AdminUser(String adminUser_userId, String adminUser_username, String adminUser_password,
			String adminUser_userRole) {
		this.adminUser_userId = adminUser_userId;
		this.adminUser_username = adminUser_username;
		this.adminUser_password = adminUser_password;
		this.adminUser_userRole = adminUser_userRole;
	}

	// Getters
	public String getAdminUser_userId() {
		return adminUser_userId;
	}

	public String getAdminUser_username() {
		return adminUser_username;
	}

	public String getAdminUser_password() {
		return adminUser_password;
	}

	public String getAdminUser_userRole() {
		return adminUser_userRole;
	}

	// Setters
	public void setAdminUser_userId(String adminUser_userId) {
		this.adminUser_userId = adminUser_userId;
	}

	public void setAdminUser_username(String adminUser_username) {
		this.adminUser_username = adminUser_username;
	}

	public void setAdminUser_password(String adminUser_password) {
		this.adminUser_password = adminUser_password;
	}

	public void setAdminUser_userRole(String adminUser_userRole) {
		this.adminUser_userRole = adminUser_userRole;
	}

	// toString method
	@Override
	public String toString() {
		return "AdminUser{" + "adminUser_userId=" + adminUser_userId + ", adminUser_username='" + adminUser_username
				+ '\'' + ", adminUser_password='" + adminUser_password + '\'' + ", adminUser_userRole='"
				+ adminUser_userRole + '\'' + '}';
	}

}
