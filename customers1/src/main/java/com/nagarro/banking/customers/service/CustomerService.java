package com.nagarro.banking.customers.service;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.banking.customers.repository.CustomerRepository;
import com.nagarro.banking.customers.common.AccountCommon;
import com.nagarro.banking.customers.dto.CustomerDto;
import com.nagarro.banking.customers.entity.Customer;

@Service
public class CustomerService {
  
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	// create a customer 
	public CustomerDto createCustomer(CustomerDto customerDto){
				
		Customer customer = dtoToCustomer(customerDto);
		
		
		customerRepository.save(customer);
		
		AccountCommon accountCommon = new AccountCommon();
        accountCommon.setCustomerId(customer.getId());		
		//rest call to another Account microservice and passing customer id
		restTemplate.postForObject("http://ACCOUNT-SERVICE/bank_accounts/account/createAccount", accountCommon, AccountCommon.class);
		
		CustomerDto saved_CustomerDto = customerToDto(customer);
		return saved_CustomerDto;
	}
	
	//save a customer 
	public CustomerDto saveCustomer(CustomerDto customerDto){
		Customer customer = dtoToCustomer(customerDto);
		customerRepository.save(customer);
		CustomerDto saved_CustomerDto = customerToDto(customer);
		return saved_CustomerDto;
	}
	
	// get all the customers 
	public List<CustomerDto> getCustomers() {
		List<Customer> allCustomers = customerRepository.findAll();
		List<CustomerDto> allCustomersDto = new ArrayList<CustomerDto>();
		for(int i=0;i<allCustomers.size();i++) {
			CustomerDto customerDto = customerToDto(allCustomers.get(i));
			allCustomersDto.add(customerDto);
		}
		return allCustomersDto;
	}
	
	
	// get single customer
	public Optional<Customer> getCustomerById(long id){
		Optional<Customer> customer = customerRepository.findById(id);
        return customer;
	}
	
	// deleting the customer account in the accounts of Account Microservices
	public void deleteCustomerAccount(long customerId) {
		
		//rest call to Account Microservices to pass the customerId value 
		restTemplate.delete("http://ACCOUNT-SERVICE/bank_accounts/customer_account/"+customerId);
		
	}
	
	
	
	public Customer dtoToCustomer(CustomerDto customerDto){
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
//		customer.setId(customerDto.getId());
//		customer.setName(customerDto.getName());
//		customer.setPhone(customerDto.getPhone());
//		customer.setMail(customerDto.getMail());
//		customer.setPan(customerDto.getPan());
	    return customer;
	}    
	public CustomerDto customerToDto(Customer customer) {
		CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
//		customerDto.setId(customer.getId());
//		customerDto.setName(customer.getName());
//		customerDto.setPhone(customer.getPhone());
//		customerDto.setMail(customer.getMail());
//		customerDto.setPan(customer.getPan());
	    return customerDto;
	}     

	
	
}
