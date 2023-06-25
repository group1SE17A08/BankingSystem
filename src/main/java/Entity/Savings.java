package Entity;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Savings {
	private String savingsId;
	private double savingsInterest;
	private Date savingsMaturityDay;
	private String savingsOwner;
	private double savingsAmount;
	private Date savingsStartDate;
	private boolean inProcess;

	public Savings() {
		// Default constructor
	}

	public Savings(String savingsId, double savingsInterest, Date savingsMaturityDay, String savingsOwner,
			double savingsAmount) {
		this.savingsId = savingsId;
		this.savingsInterest = savingsInterest;
		this.savingsMaturityDay = savingsMaturityDay;
		this.savingsOwner = savingsOwner;
		this.savingsAmount = savingsAmount;
	}

	public String getSavingsId() {
		return savingsId;
	}

	public void setSavingsId(String savingsId) {
		this.savingsId = savingsId;
	}

	public double getSavingsInterest() {
		return savingsInterest;
	}

	public void setSavingsInterest(double savingsInterest) {
		this.savingsInterest = savingsInterest;
	}

	public Date getSavingsMaturityDay() {
		return savingsMaturityDay;
	}

	public void setSavingsMaturityDay(Date savingsMaturityDay) {
		this.savingsMaturityDay = savingsMaturityDay;
	}

	public String getSavingsOwner() {
		return savingsOwner;
	}

	public void setSavingsOwner(String savingsOwner) {
		this.savingsOwner = savingsOwner;
	}

	public double getSavingsAmount() {
		return savingsAmount;
	}

	public void setSavingsAmount(double savingsAmount) {
		this.savingsAmount = savingsAmount;
	}

	@Override
	public String toString() {
		return "Savings{" + "savingsId=" + savingsId + ", savingsInterest=" + savingsInterest + ", savingsMaturityDay="
				+ savingsMaturityDay + ", savingsOwner='" + savingsOwner + '\'' + ", savingsAmount=" + savingsAmount
				+ '}';
	}

	public String generateRandomString() {
		String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int LENGTH = 5;
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < LENGTH; i++) {
			int randomIndex = random.nextInt(ALPHABET.length());
			char randomChar = ALPHABET.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	public Date getSavingsStartDate() {
		return savingsStartDate;
	}

	public void setSavingsStartDate(Date savingsStartDate) {
		this.savingsStartDate = savingsStartDate;
	}

	public boolean isInProcess() {
		return inProcess;
	}

	public void setInProcess(boolean inProcess) {
		this.inProcess = inProcess;
	}

	public double currentAmount() {
		Double currentAmount = this.savingsAmount;
		Date currentDate = Date.valueOf(LocalDate.now());
		Date startDate = this.savingsStartDate;

		if (currentAmount != null && startDate != null) {

			LocalDate localDate1 = startDate.toLocalDate();
			LocalDate localDate2 = currentDate.toLocalDate();

			long differenceInYears = ChronoUnit.YEARS.between(localDate1, localDate2);

			currentAmount = currentAmount * Math.pow((1 + this.savingsInterest), differenceInYears);
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String formattedNumber = decimalFormat.format(currentAmount);
			currentAmount = Double.parseDouble(formattedNumber);
			return currentAmount;
		} else {
			return 0;
		}
	}

	public double finalAmount() {
		Double currentAmount = this.savingsAmount;
		Date maturityDate = this.savingsMaturityDay;
		Date startDate = this.savingsStartDate;
		if (currentAmount != null && startDate != null) {
			LocalDate localDate1 = startDate.toLocalDate();
			LocalDate localDate2 = maturityDate.toLocalDate();

			long differenceInYears = ChronoUnit.YEARS.between(localDate1, localDate2);

			currentAmount = currentAmount * Math.pow((1 + this.savingsInterest), differenceInYears);
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String formattedNumber = decimalFormat.format(currentAmount);
			currentAmount = Double.parseDouble(formattedNumber);
			return currentAmount;
		} else {
			return 0;
		}
	}

	public double getPercentageToVip(Account acc) {
		double percentage = 0;
		if (acc.getAccountTotalSavings() >= 100000) {
			percentage = 1;
		} else if (acc.getAccountTotalSavings() < 100000) {
			percentage = acc.getAccountTotalSavings() / 100000 * 100;
		}

		return percentage;
	}

}