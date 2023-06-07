package Entity;

import java.sql.Date;
import java.util.Random;

public class Bill {
	private String billId;
	private String billCreatedBy;
	private String billAccountReceive;
	private String billAccountPaid;
	private double billAmount;
	private Date billPaidDate;
	private boolean billIsPaid;
	private String billContent;
	private Date billDueDate;

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
			double billAmount, Date billPaidDate, boolean billIsPaid, String billContent, Date billDueDate) {
		this.billId = billId;
		this.billCreatedBy = billCreatedBy;
		this.billAccountReceive = billAccountReceive;
		this.billAccountPaid = billAccountPaid;
		this.billAmount = billAmount;
		this.billPaidDate = billPaidDate;
		this.billIsPaid = billIsPaid;
		this.billContent = billContent;
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

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public Date getBillPaidDate() {
		return billPaidDate;
	}

	public void setBillPaidDate(Date billPaidDate) {
		this.billPaidDate = billPaidDate;
	}

	public boolean isBillIsPaid() {
		return billIsPaid;
	}

	public void setBillIsPaid(boolean billIsPaid) {
		this.billIsPaid = billIsPaid;
	}

	public String getBillContent() {
		return billContent;
	}

	public void setBillContent(String billContent) {
		this.billContent = billContent;
	}

	public Date getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}

	// toString method
	@Override
	public String toString() {
		return "Bill{" + "billId='" + billId + '\'' + ", billCreatedBy='" + billCreatedBy + '\''
				+ ", billAccountReceive='" + billAccountReceive + '\'' + ", billAccountPaid='" + billAccountPaid + '\''
				+ ", billAmount=" + billAmount + ", billPaidDate=" + billPaidDate + ", billIsPaid=" + billIsPaid
				+ ", billContent='" + billContent + '\'' + ", billDueDate=" + billDueDate + '}';
	}
}
