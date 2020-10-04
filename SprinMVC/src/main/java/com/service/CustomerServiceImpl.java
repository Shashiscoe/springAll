package com.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.entity.Customer;
import com.exception.CustomerNotFoundException;
import com.repository.CustomerDao;
import com.repository.CustomerRepository;


@Configuration
@EnableScheduling
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
    private RestTemplate restTemplate;
	
	
	  @Autowired 
	  private CustomerDao customerDao;
	 
 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    private CustomerRepository customerRepository;

    
    @Transactional
    public List<Customer>  getCustomers() {
    	customerDao.getJdbcTemplateData();
    	
        return customerRepository.findAll();
    }

    @Transactional
    public void saveCustomer(Customer theCustomer) {
        customerRepository.save(theCustomer);
    }

    @Transactional
	public Optional<Customer> getCustomer(int theId) {
 
		return customerRepository.findById(theId);
	}


	public void deleteCustomer(int theId) {
		customerRepository.deleteById(theId);
		
	}

	/*
	 * @Scheduled(fixedRate = 5000) public void executeTask1() {
	 * System.out.println(Thread.currentThread().getName()+" The Task1 executed at "
	 * + new Date()); try { Thread.sleep(1000); System.out.println(temp++); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
	@Async("asyncExecutor")
    public CompletableFuture<String> getEmployeeName(int id) 
    {
		try {
			 long start = System.currentTimeMillis();
		
			System.out.println("Inside getEmployeeName "+id);
			Optional<Customer> res= customerRepository.findById(id);
        	Thread.sleep(100L);
        	System.out.println(res);
            logger.info("Elapsed time: " + (System.currentTimeMillis() - start) +" ms");
    		
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return CompletableFuture.completedFuture("");
    }


	
	  @Override 
	  public List jdbcTemplateData() {
		  // TODO Auto-generated method stub
	  List result=customerDao.getJdbcTemplateData(); 
	  return result; 
	  }
	 
}