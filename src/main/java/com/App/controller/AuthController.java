package com.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.App.models.User;
import com.App.service.ApiService;

@Controller
public class AuthController {
	
	
	 private final ApiService apiService;

	    public AuthController (ApiService apiService) {
	        this.apiService = apiService;
	    }

	    
	    @RequestMapping("/login")
	    public String loginForm(Model model) {
	    	System.out.println("Received");
	        model.addAttribute("user", new User());
	        return "login";
	    }

	    @PostMapping("auth/login")
	    public String login(@ModelAttribute User user, Model model) {
	    	System.out.println("hello");
	        String token = apiService.authenticate(user);

	        if (token != null && !token.isEmpty()) {
	            model.addAttribute("token", token);
	            return "redirect:/customer-list";
	        } else {
	            // Handle authentication failure
	            return "login";
	        }
	    }
}
