package com.galaxe.metrics.ServiceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumeExternalApiService {
	private final String apiUrl = "https://jsonplaceholder.typicode.com/posts";

	private final RestTemplate restTemplate;

	@Autowired
	public ConsumeExternalApiService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public List<Object> fetchUserData() {
		String userApiEndpoint = "/api/Users/fetch";
		Object[] result = restTemplate.getForObject(apiUrl + userApiEndpoint, Object[].class);
		return Arrays.asList(result);
	}

}
