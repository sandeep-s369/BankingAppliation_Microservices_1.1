package com.nagarro.banking.accounts.entity;

import java.math.BigDecimal;

public class AccountAndCustomerDetails {

	private long accountNumber;
	private long balance;
	private String name;
    private long phone;
    private String mail;
    private String pan;
    
    
    
	public AccountAndCustomerDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountAndCustomerDetails(long accountNumber, long balance, String name, long phone, String mail,
			String pan) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.pan = pan;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	
}
