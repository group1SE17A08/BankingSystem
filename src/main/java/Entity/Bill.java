package Entity;

import java.sql.Date;
<<<<<<< HEAD
import java.sql.Timestamp;
=======
>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
import java.util.HashMap;
import java.util.Random;

public class Bill {
	private String billId;
	private String billCreatedBy;
	private String billAccountReceive;
	private String billAccountPaid;
<<<<<<< HEAD

	private Timestamp billPaidDate;
	private boolean billIsPaid;

=======

	private Date billPaidDate;
	private boolean billIsPaid;

>>>>>>> 582fe9be6fbf99551477911ce8ee6e63ff6ae2cc
	private Date billDueDate;
	
	private HashMap<String, Double> billDetails;
	private double billAmount;
	
	public void setBillAmount(double amount) {
		this.billAmount = amount;
	}
	
	public double getBillAmount() {
		return this.billAmount;
	}
	// Default constructor
	public Bill() {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
        StringBuilder sb = new StringBuilder(16);

        for (int i = 0; i < 16; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

		this.billId = sb.toString();
	}

	// Parameterized constructor
	public Bill(String billId, String billCreatedBy, String billAccountReceive, String billAccountPaid,
			double billAmount, Timestamp billPaidDate, boolean billIsPaid, String billContent, Date billDueDate) {
		this.billId = billId;
		this.billCreatedBy = billCreatedBy;
		this.billAccountReceive = billAccountReceive;
		this.billAccountPaid = billAccountPaid;

		this.billPaidDate = billPaidDate;
		this.billIsPaid = billIsPaid;

		this.billDueDate = billDueDate;
		
		
	}

	// Getter and setter methods
	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillCreatedBy() {
		return billCreatedBy;
	}

	public void setBillCreatedBy(String billCreatedBy) {
		this.billCreatedBy = billCreatedBy;
	}

	public String getBillAccountReceive() {
		return billAccountReceive;
	}

	public void setBillAccountReceive(String billAccountReceive) {
		this.billAccountReceive = billAccountReceive;
	}

	public String getBillAccountPaid() {
		return billAccountPaid;
	}

	public void setBillAccountPaid(String billAccountPaid) {
		this.billAccountPaid = billAccountPaid;
	}



	public Timestamp getBillPaidDate() {
		return billPaidDate;
	}

	public void setBillPaidDate(Timestamp billPaidTimestamp) {
		this.billPaidDate = billPaidTimestamp;
	}

	public boolean isBillIsPaid() {
		return billIsPaid;
	}

	public void setBillIsPaid(boolean billIsPaid) {
		this.billIsPaid = billIsPaid;
	}



	public Date getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(Date billDueTimestamp) {
		this.billDueDate = billDueTimestamp;
	}

	public HashMap<String, Double> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(HashMap<String, Double> billDetails) {
		this.billDetails = billDetails;
	}

	
}
