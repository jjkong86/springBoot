package com.example.demo.restApi;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ApiController {
	
	private RestTemplate restTemplate;

    @Autowired
    public ApiController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	
	@RequestMapping("/getblockcount")
    public String getblockcount() throws IOException {
    	JSONObject personJsonObject = new JSONObject();
    	HttpHeaders headers = new HttpHeaders();
    	ObjectMapper objectMapper = new ObjectMapper();
        headers.setContentType(MediaType.APPLICATION_JSON);;
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity("http://localhost:80/api/v1/getblockcount", request, String.class);
        return responseEntityStr.getBody();
    }
}
