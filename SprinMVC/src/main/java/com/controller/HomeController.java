package com.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.entity.Customer;
import com.entity.CustomerErrorResponse;
import com.exception.CustomerNotFoundException;
import com.service.CustomerService;


@RestController
public class HomeController {

	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final Logger logger = Logger.getLogger(HomeController.class); 
	@Autowired
	 private CustomerService customerService;
	 
	
	@GetMapping(value="/AllCustomer",produces = "application/json; charset=UTF-8")
	public List<Customer> Homepage() throws InterruptedException
	{	
		 long start = System.currentTimeMillis();
		 
		 ThreadContext.push("key",this);
		List<Customer> list =customerService.getCustomers();
		
		/*
		 * if(list.size()>0) { throw new
		 * CustomerNotFoundException("Data Found.. kjfsk"); }
		 */
		
	//	  if(list.size()>0) { throw new CustomerNotFoundException("Expetion occur "); }
		 
		customerService.getEmployeeName(1);
	    logger.info("Elapsed time: " + (System.currentTimeMillis() - start) +" ms");  
	    System.out.println("URL1-"+ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
	    System.out.println("URL2-"+ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
	    
		return list;
	}
	
	///using path variable
	@GetMapping(path="/home/{str}")
	public String home(@PathVariable String str)
	{
		
		return "WELCOME " + str;
	}

	///using Request parameter
	@GetMapping(path="/home1")
	public String home1(@RequestParam(name="id",required = false) String str)
	{
		return "WELCOME " + str;
	
	}
	@PostMapping("/createCustomer")
    public List<Customer> createCustomer(@RequestBody Customer theCustomer) {
        customerService.saveCustomer(theCustomer);
        List<Customer> list =customerService.getCustomers();
		return list;
    }

	@PutMapping("/updateCustomer")
    public List<Customer> updateCustomer(@RequestParam(name = "id",required = true) int id,@RequestBody Customer theCustomer) {
		List<Customer> list=null;
		System.out.println("URL1-"+ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
	    System.out.println("URL2-"+ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
	    
		if(customerService.getCustomer(id).isPresent())
		{
			System.out.println("Data Found");

			customerService.saveCustomer(theCustomer);
			 list=customerService.getCustomers();
		}
		else
		{
			System.out.println("Data Not Found..Please enter valid ID :");
			list=null;
			throw new CustomerNotFoundException("Data Not Found..Please enter valid ID :");
			
		}
		return list;
	}
	@DeleteMapping("/deleteCustomer")
    public List<Customer> deleteCustomer(@RequestParam(name = "id",required = true) int id) throws CustomerNotFoundException {
		List<Customer> list=null;
		if(customerService.getCustomer(id).isPresent())
		{	
			customerService.deleteCustomer(id);
			System.out.println("Deleted SuccessFully");
	
			list=customerService.getCustomers();
		}
		else
		{
			/*
			 * System.out.println("Data Not Found..Please enter valid ID :"); list=null;
			 */
			throw new CustomerNotFoundException("Data Not Found..Please enter valid ID :");
		}
		return list;
	}
	@GetMapping(value="/listData",produces = "application/json; charset=UTF-8")
	  public List jdbcTemplateData() 
	 {
		 List result=customerService.jdbcTemplateData();
	  return result;
	  
	 }

	//Local Exception handler
	
	
	/*
	 * @ExceptionHandler public ResponseEntity<CustomerErrorResponse>
	 * handleException(CustomerNotFoundException exc) { //CustomerErrorResponse is
	 * actual response type and CustomerNotFoundException actual exception type //
	 * create a StudentErrorResponse
	 * 
	 * CustomerErrorResponse error = new CustomerErrorResponse();
	 * 
	 * error.setStatus(HttpStatus.NOT_FOUND.value());
	 * error.setMessage(exc.getMessage());
	 * error.setTimeStamp(System.currentTimeMillis());
	 * 
	 * // return ResponseEntity
	 * 
	 * 
	 * return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //error is body of
	 * the response and HttpStatus.NOT_FOUND is actual status code }
	 * 
	 * 
	 * //Generic exception which
	 */
	
	/*
	 * @ExceptionHandler public ResponseEntity<CustomerErrorResponse>
	 * handleException(Exception exc) { //CustomerErrorResponse is actual response
	 * // type and Exception actual exception type // create a StudentErrorResponse
	 * 
	 * CustomerErrorResponse error = new CustomerErrorResponse();
	 * 
	 * error.setStatus(HttpStatus.BAD_REQUEST.value());
	 * error.setMessage(exc.getMessage());
	 * error.setTimeStamp(System.currentTimeMillis());
	 * 
	 * // return ResponseEntity
	 * 
	 * 
	 * return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); //error is body
	 * of the response and HttpStatus.NOT_FOUND is actual status code }
	 */
	  
	 	/*
	 * @RequestMapping(value ="/",produces = "application/json") public String
	 * getURLValue(HttpServletRequest request){ String test =
	 * request.getRequestURI(); return test; }
	 */
	
}
