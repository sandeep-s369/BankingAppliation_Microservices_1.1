package com.nagarro.banking.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.nagarro.banking.customers.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
