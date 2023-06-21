package Entity;

import java.sql.Date;
import java.time.LocalDate;

public class Customer {

	    private String customerId;
	    private String customerName;
	    private Date customerDob;
	    private String customerAddress;
	    private String customerPhoneNumber;
	    private String customerEmail;
	    private String customerUsername;
	    private String customerPassword;
	    private String customerBankAccount;
	    private int loginAttempts;
	    private boolean vipStatus;
	    
		public int getLoginAttempts() {
			return loginAttempts;
		}

		public void setLoginAttempts(int loginAttempts) {
			this.loginAttempts = loginAttempts;
		}

		public Customer() {
	    	
	    }
	    
	    public Customer(String customerId, String customerName, Date customerDob, String customerAddress,
	                    String customerPhoneNumber, String customerEmail, String customerUsername,
	                    String customerPassword, String customerBankAccount, int loginAttempts) {
	        this.customerId = customerId;
	        this.customerName = customerName;
	        this.customerDob = customerDob;
	        this.customerAddress = customerAddress;
	        this.customerPhoneNumber = customerPhoneNumber;
	        this.customerEmail = customerEmail;
	        this.customerUsername = customerUsername;
	        this.customerPassword = customerPassword;
	        this.customerBankAccount = customerBankAccount;
	        this.loginAttempts = loginAttempts;
	    }

	    // Getters and Setters

	    public String getCustomerId() {
	        return customerId;
	    }

	    public void setCustomerId(String customerId) {
	        this.customerId = customerId;
	    }

	    public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public Date getCustomerDob() {
	        return customerDob;
	    }

	    public void setCustomerDob(Date customerDob) {
	        this.customerDob = customerDob;
	    }

	    public String getCustomerAddress() {
	        return customerAddress;
	    }

	    public void setCustomerAddress(String customerAddress) {
	        this.customerAddress = customerAddress;
	    }

	    public String getCustomerPhoneNumber() {
	        return customerPhoneNumber;
	    }

	    public void setCustomerPhoneNumber(String customerPhoneNumber) {
	        this.customerPhoneNumber = customerPhoneNumber;
	    }

	    public String getCustomerEmail() {
	        return customerEmail;
	    }

	    public void setCustomerEmail(String customerEmail) {
	        this.customerEmail = customerEmail;
	    }

	    public String getCustomerUsername() {
	        return customerUsername;
	    }

	    public void setCustomerUsername(String customerUsername) {
	        this.customerUsername = customerUsername;
	    }

	    public String getCustomerPassword() {
	        return customerPassword;
	    }

	    public void setCustomerPassword(String customerPassword) {
	        this.customerPassword = customerPassword;
	    }

	    public String getCustomerBankAccount() {
	        return customerBankAccount;
	    }

	    public void setCustomerBankAccount(String customerBankAccount) {
	        this.customerBankAccount = customerBankAccount;
	    }

		@Override
		public String toString() {
			return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerDob="
					+ customerDob + ", customerAddress=" + customerAddress + ", customerPhoneNumber="
					+ customerPhoneNumber + ", customerEmail=" + customerEmail + ", customerUsername="
					+ customerUsername + ", customerPassword=" + customerPassword + ", customerBankAccount="
					+ customerBankAccount + "]";
		}

		public boolean isVipStatus() {
			return vipStatus;
		}

		public void setVipStatus(boolean vipStatus) {
			this.vipStatus = vipStatus;
		}
	

	    
}
