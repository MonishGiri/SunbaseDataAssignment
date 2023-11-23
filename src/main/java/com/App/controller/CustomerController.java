package com.App.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.App.models.ApiResponse;
import com.App.models.Customer;
import com.App.service.ApiService;

@Controller
public class CustomerController {
	
	 private final ApiService apiService;

	    public CustomerController(ApiService apiService) {
	        this.apiService = apiService;
	    }

	    @GetMapping("/customer-list")
	    public String getCustomerList(@ModelAttribute("token") String token, Model model) {
	        List<Customer> customers = apiService.getCustomerList(token);
	        model.addAttribute("customers", customers);
	        return "customer-list";
	    }

	    @GetMapping("/add")
	    public String addCustomerForm(Model model) {
	        model.addAttribute("customer", new Customer());
	        return "add-customer";
	    }

	    @PostMapping("/add")
	    public String addCustomer(@ModelAttribute("token") String token, @ModelAttribute Customer customer, Model model) {
	        ApiResponse response = apiService.createCustomer(token, customer);

	        if (response.getStatus() == 201) {
	            return "redirect:/customer-list";
	        } else {
	            // Handle customer creation failure
	            return "add-customer";
	        }
	    }

	    @GetMapping("/delete/{uuid}")
	    public String deleteCustomer(@ModelAttribute("token") String token, @PathVariable String uuid, Model model) {
	        ApiResponse response = apiService.deleteCustomer(token, uuid);

	        // Handle response and update model accordingly

	        return "redirect:/customer-list";
	    }

	    @GetMapping("/update/{uuid}")
	    public String updateCustomerForm(@ModelAttribute("token") String token, @PathVariable String uuid, Model model) {
	        // Retrieve customer details using uuid and add to the model
	        // ...

	        return "update-customer";
	    }

	    @PostMapping("/update/{uuid}")
	    public String updateCustomer(@ModelAttribute("token") String token, @PathVariable String uuid,
	                                 @ModelAttribute Customer customer, Model model) {
	        ApiResponse response = apiService.updateCustomer(token, uuid, customer);

	        // Handle response and update model accordingly

	        return "redirect:/customer-list";
	    }

}

