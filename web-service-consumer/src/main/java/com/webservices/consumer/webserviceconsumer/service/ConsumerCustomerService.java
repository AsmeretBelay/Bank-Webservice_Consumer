package com.webservices.consumer.webserviceconsumer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.webservices.consumer.webserviceconsumer.model.Customer;

@Service
public class ConsumerCustomerService {
	
	
	 @Autowired
	  private RestTemplate restTemplate;
	
	 
	 @Scheduled(fixedDelay=10000000)
	   public void  getAllCustomers() {	 
		   
		ResponseEntity<Customer[]>  customersResult=  restTemplate.getForEntity("http://localhost:8090/customers", Customer[].class);
		System.out.println("Status Code="+ customersResult.getStatusCode());
		
		Customer[] cusArray=customersResult.getBody();
		//search Id 21 and name ==
		System.out.println("customers size= "+ cusArray.length + "name --" +cusArray[0].getName());
		
	   }
	 
	 
	 @Scheduled(fixedDelay=10000000)
	   public void  getCustomerById() {	 
		   
		ResponseEntity<Customer>  customersResult=  restTemplate.getForEntity("http://localhost:8090/customer/2", Customer.class);
		System.out.println("Status Code="+ customersResult.getStatusCode());		
		Customer customer=customersResult.getBody();
		System.out.println("name - "+ customer.getName() + "-----Gender=="+customer.getGender());
		
	   }
	 
	  //@RequestParam implementation
	   @Scheduled(fixedDelay=10000000)
	   public void  getCustomerByName() {	 
		   
		ResponseEntity<Customer[]>  customersResult=  restTemplate.getForEntity("http://localhost:8090/customer?name=Hanna", Customer[].class);
		System.out.println("Status Code="+ customersResult.getStatusCode());		
		Customer[] customerArray=customersResult.getBody();
		System.out.println("getCustomerByName()  size of customer list returned is "+ customerArray.length);
		
	   }
	 
	 
	 
	 @Scheduled(fixedDelay=1000000000)
	   public void  createCustomer() {	 
		 
		 HttpHeaders headers= new HttpHeaders(); 
		 
		 Customer customer=new Customer();
		 customer.setGender("Female");
		 customer.setName("Hanna");
		 HttpEntity<Customer> customerRequestEntity=new HttpEntity<>(customer,headers); 
		 
		 
		ResponseEntity<Customer>  customersResult=restTemplate.exchange("http://localhost:8090/customer", HttpMethod.POST,customerRequestEntity, Customer.class );
		System.out.println("Status Code="+ customersResult.getStatusCode());		
		Customer cust=customersResult.getBody();
		System.out.println("New resource is created with ID--"+ cust.getId());
		
	   }
	 
	
	 @Scheduled(fixedDelay=10000000)
	   public void  deleteCustomerById() {	 
		   Map < String, String > params = new HashMap < String, String > ();
		   params.put("id", "7");
		  restTemplate.delete("http://localhost:8090/customer/{id}",params );	
    System.out.println("Resource with Id 5 is successfullly deleted");
		
	   }
	 
	 // calling Update customer by passing customer object as request payload.
	 @Scheduled(fixedDelay=10000000)
	 public void  updateCustomer() {
		 
		 Customer customer =new Customer();
		 customer.setName("John");
		 customer.setGender("Male");
		 
		 
		 HttpHeaders headers=new HttpHeaders();		 
		 HttpEntity<Customer> customerUpdateRequestEntity= new HttpEntity<>(customer,headers );
		 
		 
		 ResponseEntity<Customer> customerResponseEntity=restTemplate.exchange("http://localhost:8090/customer/2", HttpMethod.PUT,customerUpdateRequestEntity, Customer.class);
		 System.out.println(customerResponseEntity.getStatusCode());
		 
		  Customer newCustomer=customerResponseEntity.getBody();
		 System.out.println("Updated customer name=="+newCustomer.getName());
		 
	 }
	 
	 


}
