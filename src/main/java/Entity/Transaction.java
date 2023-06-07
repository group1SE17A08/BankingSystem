package Entity;


import java.sql.Date;
import java.util.Random;

public class Transaction {
	private String transactionId;
	private String fromAccount;
	private String toAccount;
	private double amount;
	private Date date;
	private boolean status;
	private String content;
	private String transactionType;
	// Default constructor
	public Transaction() {
		StringBuilder sb = new StringBuilder();

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();

        // Generate 5 random letters or numbers
        for (int i = 0; i < 5; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            sb.append(randomChar);
        }
        this.transactionId = sb.toString();
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	// Constructor with all attributes
	public Transaction(String transactionId, String fromAccount, String toAccount, double amount, Date date, boolean status,
			String content, String transactionType) {
		this.transactionId = transactionId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.date = date;
		this.status = status;
		this.content = content;
		this.transactionType = transactionType;
	}

	// Getter and Setter methods for all attributes
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// toString method to represent the Transaction object as a string
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", amount=" + amount + ", date=" + date + ", status=" + status + ", content=" + content
				+ "]";
	}
}
