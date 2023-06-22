package Entity;

import java.sql.Date;
import java.util.Random;

public class Savings {
    private String savingsId;
    private double savingsInterest;
    private Date savingsMaturityDay;
    private String savingsOwner;
    private double savingsAmount;
    
    public Savings() {
        // Default constructor
    }
    
    public Savings(String savingsId, double savingsInterest, Date savingsMaturityDay, String savingsOwner, double savingsAmount) {
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
        return "Savings{" +
                "savingsId=" + savingsId +
                ", savingsInterest=" + savingsInterest +
                ", savingsMaturityDay=" + savingsMaturityDay +
                ", savingsOwner='" + savingsOwner + '\'' +
                ", savingsAmount=" + savingsAmount +
                '}';
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
}