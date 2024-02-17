package com.nagarro.banking.accounts.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoneyDebit {

	private long accountNumber;
	private long pin;
	private long amount;
	
//	public MoneyDebit(long accountNumber, long pin, long amount) {
//		super();
//		this.accountNumber = accountNumber;
//		this.pin = pin;
//		this.amount = amount;
//	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getPin() {
		return pin;
	}
	public void setPin(Long pin) {
		this.pin = pin;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	
	
}
