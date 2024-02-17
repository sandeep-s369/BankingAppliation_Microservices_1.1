package com.nagarro.banking.accounts.service;

import java.math.BigDecimal;

import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.banking.accounts.Dto.AccountDto;
import com.nagarro.banking.accounts.Dto.CustomerDto;
import com.nagarro.banking.accounts.Respository.AccountRepository;
import com.nagarro.banking.accounts.common.AccountCommon;
import com.nagarro.banking.accounts.entity.Account;


@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//create Account
	public AccountDto createAccount(long customerId){
		
		Account account = new Account();
		account.setCustomerId(customerId);
		accountRepository.save(account);
		AccountDto accountDto = this.modelMapper.map(account,AccountDto.class);
		return accountDto;
	}
	
	
	//adding money
	public Optional<Account> addMoneyToAccount(long accountNumber, long amount){
		//long accountNumber=Long.parseLong("accountNumberInString");
		Optional<Account> account_FromDatabase = accountRepository.findById(accountNumber);
	    return account_FromDatabase;
	}
	
	
	
	
	
	public Account dtoToAccount(AccountDto accountDto) {
		Account account = this.modelMapper.map(accountDto,Account.class);
		return account;		
	}
	
	public AccountDto accountToDto(Account account){
		AccountDto accountDto = this.modelMapper.map(account,AccountDto.class);
		return accountDto;		
	}
	
	//To generate random Id 
//	public Payment doPayment(Payment payment) {
//    	payment.setTransactionId(UUID.randomUUID().toString());
//    	return repository.save(payment);
//	}
	
	public void deleteCustomer(AccountCommon accountCommon) {
		
		long id = accountCommon.getCustomerId();
		//rest call to another Customer microservice and passing customer id
		//restTemplate.postForObject("http://CUSTOMER-SERVICE/bank_customers/customer",common,AccountCommon.class);
		
		restTemplate.delete("http://CUSTOMER-SERVICE/bank_customers/customers/"+id);
	}
	
	
	// Getting the customer details from Customer Microservices
	public CustomerDto getCustomerDetails(long id){
		CustomerDto customerDto = new CustomerDto();
		
		//rest call for getting customer details from customer microservices
		customerDto=restTemplate.getForObject("http://CUSTOMER-SERVICE/bank_customers/customers/"+id,CustomerDto.class);
		
		return customerDto;
	}
	
	public long addAmount(long currentAmount, long amountToAdd) {
		long total = (currentAmount + amountToAdd) ;
		return total;
	}
	
	public long substractAmount(long currentAmount, long amountToWithDraw) {
		long total = (currentAmount - amountToWithDraw) ;
		return total;
	}
	
}
