package com.nagarro.banking.accounts.entity;



import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

import java.math.BigDecimal;
import java.util.Random;

import jakarta.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
@Data
@Entity
@Transactional
public class Account {
	
	@Id
	@SequenceGenerator(name="seq", initialValue=10001, allocationSize=1000)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private long accountNumber;
	private long balance;
	
	
	private long pin = randomNumber();
	private long customerId;
//	@OneToOne(mappedBy = "account")
//	@JoinColumn(name="fk_id")
//	private Customer customer;
	
	
	
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	//	public Customer getCustomer() {
//		return customer;
//	}
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
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
	public long getPin() {
		return pin;
	}
	public void setPin(long pin) {
		this.pin = pin;
	}
	
	// to create random pin number of 4 digits 
	long randomNumber() {
		
		Random rand = new Random();
		long number=rand.nextInt(10000);
		return number;
	}
	
	
}
