package com.example.demo.restApi;

import java.io.IOException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.apiParameter.GetTransactionsRequest;
import com.example.demo.apiResponse.GetTransactionsResponse;
import com.example.demo.objects.Transaction;
import com.example.demo.resttemplate.ApiTestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	private RestTemplate restTemplate;

    @Autowired
    public ApiController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
	
	@RequestMapping("/getblockcount")
    public String getblockcount() throws IOException {
    	JSONObject personJsonObject = new JSONObject();
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);;
        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        ResponseEntity<String> responseEntityStr = restTemplate.postForEntity("http://localhost:80/api/v1/getblockcount", request, String.class);
        return responseEntityStr.getBody();
    }
	
	
	@RequestMapping("/gettransactions")
	public GetTransactionsResponse gettransactions() throws Exception {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        GetTransactionsRequest param = new GetTransactionsRequest(" ", 0, 0);
        HttpEntity<GetTransactionsRequest> request = new HttpEntity<GetTransactionsRequest>(param, headers);
        ResponseEntity<GetTransactionsResponse> responseEntityStr = restTemplate.postForEntity("http://localhost:80/api/v1/gettransactions", request, GetTransactionsResponse.class);
        GetTransactionsResponse txs = responseEntityStr.getBody();
        logger.info("===== tansaction count : {} =====", txs.getResult().getTxs().size());
        for (Transaction tran : txs.getResult().getTxs()) {
        	logger.debug(tran.toString());
		}
		return txs;
    }
}
