package com.App.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.App.models.ApiResponse;
import com.App.models.Customer;
import com.App.models.User;

@Service
public interface ApiService {

	String authenticate(User user);

    ApiResponse createCustomer(String token, Customer customer);

    List<Customer> getCustomerList(String token);

    ApiResponse deleteCustomer(String token, String uuid);

    ApiResponse updateCustomer(String token, String uuid, Customer customer);
}
