package Controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CustomerDAO_Impl;
import Entity.Customer;

public class CustomerServices {
	public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
	}

	public String resetCustomerPassword(String email) {
		CustomerDAO_Impl bankDao = new CustomerDAO_Impl();
	    Customer customer = bankDao.getCustomerByEmail(email);
	     
	    String randomPassword = generateRandomString();
	     
	    customer.setCustomerPassword(randomPassword);
	    bankDao.updateCustomerPassword(customer);
	     
	    return randomPassword;
	}
	
	public String generateRandomString() {
	    String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder sb = new StringBuilder(10);
	    Random random = new Random();

	    for (int i = 0; i < 10; i++) {
	        int index = random.nextInt(alphanumeric.length());
	        char randomChar = alphanumeric.charAt(index);
	        sb.append(randomChar);
	    }

	    return sb.toString();
	}
}
