package com.greatlearning.cust_rel_mgmt_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.cust_rel_mgmt_app.entity.Customer;
import com.greatlearning.cust_rel_mgmt_app.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//injecting customer service object
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get customers from the service
		List<Customer> customers = customerService.getCustomers();
		
		theModel.addAttribute("customers", customers);
		
		return "list-customers";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//get customer to update or add
		
		Customer theCustomer = new Customer();
		theModel.addAttribute(theCustomer);
		return "customer-form";
		
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save customer 
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate (@RequestParam("customerId") int theId, Model theModel) {
		
		//get customer for id
		Customer theCustomer = customerService.getCustomer(theId);
		
		//set customer as model attribute to pre-populate the form
		theModel.addAttribute(theCustomer);
		
		//send over to our form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		//get customer for id
		customerService.deleteCustomer(theId);
		
		//open form for all customers after deletion
		return "redirect:/customer/list";
	}

}
