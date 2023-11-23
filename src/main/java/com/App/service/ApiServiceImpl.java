package com.App.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.App.models.ApiResponse;
import com.App.models.Customer;
import com.App.models.User;

@Service
public class ApiServiceImpl implements ApiService {

	@Value("${api.base-url}")
    private String apiUrl;

    @Value("${api.access-token}")
 	private String accessToken;
    
    private final RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
	@Override
	public String authenticate(User user) {
		System.out.println("Authenticating");
		String authUrl = apiUrl + "/assignment_auth.jsp";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        System.out.println("I am here...");
        ResponseEntity<String> response = restTemplate.postForEntity(authUrl, request, String.class);
        
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
        	System.out.println("Token not accessed");
            return null;
        }
	}

	@Override
	public ApiResponse createCustomer(String token, Customer customer) {
		String createCustomerUrl = apiUrl + "/assignment.jsp?cmd=create";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

        return restTemplate.postForObject(createCustomerUrl, request, ApiResponse.class);
	}

	@Override
	public List<Customer> getCustomerList(String token) {
		String getCustomerListUrl = apiUrl + "/assignment.jsp?cmd=get_customer_list";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Customer[]> response = restTemplate.exchange(
                getCustomerListUrl,
                HttpMethod.GET,
                request,
                Customer[].class
        );

        return Arrays.asList(response.getBody());
	}

	@Override
	public ApiResponse deleteCustomer(String token, String uuid) {
		String deleteCustomerUrl = apiUrl + "/assignment.jsp?cmd=delete&uuid=" + uuid;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                deleteCustomerUrl,
                HttpMethod.POST,
                request,
                ApiResponse.class
        ).getBody();
	}

	@Override
	public ApiResponse updateCustomer(String token, String uuid, Customer customer) {
		String updateCustomerUrl = apiUrl + "/assignment.jsp?cmd=update&uuid=" + uuid;
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);

        return restTemplate.exchange(
                updateCustomerUrl,
                HttpMethod.POST,
                request,
                ApiResponse.class
        ).getBody();
    }
}


	
