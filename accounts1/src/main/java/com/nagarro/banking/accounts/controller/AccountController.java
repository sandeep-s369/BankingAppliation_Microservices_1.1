package com.nagarro.banking.accounts.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nagarro.banking.accounts.Dto.AccountDto;
import com.nagarro.banking.accounts.Dto.CustomerDto;
import com.nagarro.banking.accounts.Respository.AccountRepository;
import com.nagarro.banking.accounts.common.AccountCommon;
import com.nagarro.banking.accounts.entity.Account;
import com.nagarro.banking.accounts.entity.AccountAndCustomerDetails;
import com.nagarro.banking.accounts.entity.MoneyCredit;
import com.nagarro.banking.accounts.entity.MoneyDebit;
import com.nagarro.banking.accounts.service.AccountService;
import com.nagarro.banking.accounts.service.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/bank_accounts")
public class AccountController{

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	// create Account
	@PostMapping("/account/createAccount")
	public AccountDto createAccount(@RequestBody AccountCommon accountCommon) {
		long customerId = accountCommon.getCustomerId();
		AccountDto accountDto = accountService.createAccount(customerId);
		return accountDto;
	}
	
	
	//POST /account/add-money
	@PutMapping("/account/add_money/{accountNumber}")
	public ResponseEntity<AccountDto> addMoneyToAccount(
	@PathVariable long accountNumber, @RequestBody MoneyCredit moneyRequest){
	//	long accountNumber=Long.parseLong("accountNumberInString");
		Optional<Account> account_FromDatabase = accountRepository.findById(accountNumber);
		if(account_FromDatabase.isPresent()) {
			long amountToAdd = moneyRequest.getAmount();
			Account account = account_FromDatabase.get();
			AccountDto accountDto = accountService.accountToDto(account);
			long currentAmount = account.getBalance();
			accountDto.setBalance(accountService.addAmount(currentAmount,amountToAdd));
			account.setBalance(accountDto.getBalance());
		    accountRepository.save(account);
		    AccountDto updatedAccountDto = accountService.accountToDto(account);
		    return ResponseEntity.ok(updatedAccountDto);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	
	//POST/account/withdraw-money
	@PutMapping("/account/withdraw_money")
	public ResponseEntity<AccountDto> withDrawMoneyToAccount(@RequestBody MoneyDebit moneyRequest){
	    	long accountNumber = moneyRequest.getAccountNumber();
	    	long pin = moneyRequest.getPin();
	    	long amountToWithDraw = moneyRequest.getAmount();
	    	Account account_FromDatabase = accountRepository.findByAccountNumberAndPin(accountNumber,pin);
			AccountDto accountDto = accountService.accountToDto(account_FromDatabase);
			long currentAmount = accountDto.getBalance();
			accountDto.setBalance(accountService.substractAmount(currentAmount,amountToWithDraw));
			account_FromDatabase.setBalance(accountDto.getBalance());
		    accountRepository.save(account_FromDatabase);
		    AccountDto updatedAccountDto = accountService.accountToDto(account_FromDatabase);
		    return ResponseEntity.ok(updatedAccountDto);
	}

	
	
	//GET /account/{accountNumber}:
	//Getting Account and Customer Details By Account Number
	@GetMapping("/account/{accountNumber}")
	public AccountAndCustomerDetails getDetails(@PathVariable long accountNumber) {
		Optional<Account> account_FromDatabase = accountRepository.findById(accountNumber);
		Account account = account_FromDatabase.get();
		AccountAndCustomerDetails accountantDetails = new AccountAndCustomerDetails();
		accountantDetails.setAccountNumber(account.getAccountNumber());
		accountantDetails.setBalance(account.getBalance());
		long customerId = account.getCustomerId();
	// get the details from the customer which is in customer microservices
	    CustomerDto customerDto = new CustomerDto(); 
	    customerDto = accountService.getCustomerDetails(customerId);
		accountantDetails.setName(customerDto.getName());
		accountantDetails.setPhone(customerDto.getPhone());
		accountantDetails.setMail(customerDto.getMail());
		accountantDetails.setPan(customerDto.getPan());
		return accountantDetails;
	}
	
	
//	   Delete account by Account Managaement 
    @DeleteMapping("/account/{accountNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
        	AccountCommon accountCommon = new AccountCommon();
        	Account accountFromDataBase = account.get();
        	accountCommon.setCustomerId(accountFromDataBase.getCustomerId());
 
        	// Call Customer Management Service to delete the customer
        	accountService.deleteCustomer(accountCommon);
        	
        	accountRepository.delete(account.get());
            
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }    
	
    
    // Deleting Account of a Customer by using the id sent from CUSTOMER-SERVICE microservices 
    @DeleteMapping("/customer_account/{customerId}")
    public void deleteCustomerAccount(@PathVariable long customerId) {
        Account account = accountRepository.findByCustomerId(customerId);
        
        long accountNumber = account.getAccountNumber();
        
        accountRepository.deleteById(accountNumber);
 
    }    
    
    
    
}
