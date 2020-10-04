package com.service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.entity.Customer;
//import net.javaguides.springmvc.exception.ResourceNotFoundException;

@Service
public interface CustomerService {
	public List < Customer > getCustomers();

    public void saveCustomer(Customer theCustomer);

    public Optional<Customer> getCustomer(int theId);

    public void deleteCustomer(int theId);
    public CompletableFuture<String> getEmployeeName(int id);
    
	 public List jdbcTemplateData() ; 

  
}