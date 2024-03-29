package com.nagarro.banking.customers.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

	private long accountNumber;
	private long balance;
	private long pin;
	private long customerId;
}
