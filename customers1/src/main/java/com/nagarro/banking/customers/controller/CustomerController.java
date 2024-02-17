package com.nagarro.banking.customers.controller;

import java.util.List;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.banking.customers.common.AccountCommon;
import com.nagarro.banking.customers.dto.CustomerDto;
import com.nagarro.banking.customers.entity.Account;
import com.nagarro.banking.customers.entity.Customer;
import com.nagarro.banking.customers.repository.CustomerRepository;
import com.nagarro.banking.customers.service.CustomerService;

@RestController
@RequestMapping("/bank_customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;
	
	// Adding a customer
	@PostMapping("/customers")
	public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto){
		
		return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
		//do a rest call to account and pass the order id 
	}

	// Getting all customers
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> getCustomers() {
		return new ResponseEntity<>(customerService.getCustomers(),HttpStatus.OK);
	}
	
	// Get single customer details
    @GetMapping("customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable long id) {
    	
      //  Optional<Customer> customer = customerRepository.findById(id);
          Optional<Customer> customer = customerService.getCustomerById(id);
          if(customer.isPresent()){
              Customer customerObj = customer.get();
        	  CustomerDto customerDto = customerService.customerToDto(customerObj);
              return ResponseEntity.ok(customerDto);
          } else {
              return ResponseEntity.notFound().build();
          }
    }
	
 // Update customer details
    @PutMapping("customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id,@RequestBody CustomerDto customerDetails) {
    //  Optional<Customer> customer = customerRepository.findById(id);
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
        	Customer existingCustomerObj = customer.get();
      	    CustomerDto existingCustomerDto = customerService.customerToDto(existingCustomerObj);
      //    Customer existingCustomer = customer.get();
      	    existingCustomerDto.setName(customerDetails.getName());
      	    existingCustomerDto.setMail(customerDetails.getMail());
      	    existingCustomerDto.setPhone(customerDetails.getPhone());
      	    existingCustomerDto.setPan(customerDetails.getPan());
      	    CustomerDto updatedCustomerDto = customerService.saveCustomer(existingCustomerDto);
      	    return ResponseEntity.ok(updatedCustomerDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    
 // Delete customer
    @DeleteMapping("customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
    	
    //  Optional<Customer> customer = customerRepository.findById(id);
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
        	Customer customerFromDataBase = customer.get();
        	
        	long customerId = customerFromDataBase.getId();
        	
            customerRepository.delete(customer.get());
            
            // Call Account Management Service to delete the customer account
            customerService.deleteCustomerAccount(customerId);
            
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    //Deleting Customer with respect to Account Management [ Not Used Method just for reference purpose ]
//    @DeleteMapping("customer")
//    public void deleteCustomerByAccountManagement(@RequestBody AccountCommon accountCommon) {
//    	AccountCommon common = new AccountCommon();
//    	long id = accountCommon.getCustomerId();
//    	Optional<Customer> customer = customerService.getCustomerById(id);
//        if (customer.isPresent()) {
//            customerRepository.delete(customer.get());
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    	
//    }
    
    
    
    
    
	
}
