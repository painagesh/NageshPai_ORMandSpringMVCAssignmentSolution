package com.greatlearning.cust_rel_mgmt_app.service;

import java.util.List;

import com.greatlearning.cust_rel_mgmt_app.entity.Customer;

public interface CustomerService {

   public List<Customer> getCustomers();
   
   public void saveCustomer(Customer theCustomer);
   
   public Customer getCustomer(int theId);
   
   public void deleteCustomer(int theId);
	
}
