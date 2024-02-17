package com.nagarro.banking.accounts.Respository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.banking.accounts.entity.Account;



public interface AccountRepository extends JpaRepository<Account,Long> {

	public Account findByAccountNumberAndPin(long accountNumber,long pin);
	public Account findByCustomerId(long customerId);
	
}
