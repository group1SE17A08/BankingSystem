package Entity;

public class Account {
	private String accountNumber;
	private String accountOTP;
	private double accountBalance;
	private boolean accountVIPStatus;
	private double accountTotalSavings;
	private String accountSavingsId;
	private boolean isAccountLocked;

	// Constructors, getters, and setters
	public Account() {

	}

	public boolean isAccountLocked() {
		return isAccountLocked;
	}

	public void setAccountLocked(boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public Account(String accountNumber, String accountOTP, double accountBalance, boolean accountVIPStatus,
			double accountTotalSavings, String accountSavingsId, boolean isAccountLocked) {
		super();
		this.accountNumber = accountNumber;
		this.accountOTP = accountOTP;
		this.accountBalance = accountBalance;
		this.accountVIPStatus = accountVIPStatus;
		this.accountTotalSavings = accountTotalSavings;
		this.accountSavingsId = accountSavingsId;
		this.isAccountLocked = isAccountLocked;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountOTP() {
		return accountOTP;
	}

	public void setAccountOTP(String accountOTP) {
		this.accountOTP = accountOTP;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public boolean isAccountVIPStatus() {
		return accountVIPStatus;
	}

	public void setAccountVIPStatus(boolean accountVIPStatus) {
		this.accountVIPStatus = accountVIPStatus;
	}

	public double getAccountTotalSavings() {
		return accountTotalSavings;
	}

	public void setAccountTotalSavings(int accountTotalSavings) {
		this.accountTotalSavings = accountTotalSavings;
	}

	public String getAccountSavingsId() {
		return accountSavingsId;
	}

	public void setAccountSavingsId(String accountSavingsId) {
		this.accountSavingsId = accountSavingsId;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountOTP=" + accountOTP + ", accountBalance="
				+ accountBalance + ", accountVIPStatus=" + accountVIPStatus + ", accountTotalSavings="
				+ accountTotalSavings + ", accountSavingsId=" + accountSavingsId + "]";
	}

	public String formatAccountNumber() {
		String accNum = this.getAccountNumber();
		String formattedString = accNum.substring(0, 4) + " " + "****" + " " + "****"
				+ " " + accNum.substring(12, 16);

		return formattedString;
	}
}
