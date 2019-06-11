package com.example.demo.resttemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.demo.ApplicationTests;
import com.example.demo.apiParameter.GetTransactionsRequest;
import com.example.demo.apiResponse.GetBlockCountResponse;
import com.example.demo.apiResponse.GetTransactionsResponse;
import com.example.demo.objects.Transaction;
import com.example.demo.web.handler.RestTemplateResponseErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTests.class)
public class ApiTestTemplate {
	
    private static final Logger logger = LoggerFactory.getLogger(ApiTestTemplate.class);
    
    private static String getblockcount;
    private static String gettransactions;
    
    private static RestTemplate restTemplate;
    private static String commonUrl = "http://localhost:80/api/v1";
    
    ObjectMapper objectMapper = new ObjectMapper();
    static HttpHeaders headers;
    static JSONObject personJsonObject;

    @BeforeClass
    public static void runBeforeAllTestMethods() throws JSONException {
    	logger.info("--- beforeClass start ---");
    	getblockcount = commonUrl + "/getblockcount";
    	gettransactions = commonUrl + "/gettransactions";
    	
    	restTemplate = new RestTemplate();
    	restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    	
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        personJsonObject = new JSONObject();
        
        logger.info("--- beforeClass end ---");
    }
    
    @Test
    public void getblockcount() throws IOException {
    	GetBlockCountResponse ret = new GetBlockCountResponse();
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        ResponseEntity<GetBlockCountResponse> responseEntityStr = restTemplate.postForEntity(getblockcount, request, GetBlockCountResponse.class);
        
        assertThat(responseEntityStr.getStatusCode(), equalTo(HttpStatus.OK));
        
        ret.setResponse(responseEntityStr.getBody());
        logger.debug(ret.toString());
    }
    
    @Test
    public void gettransactions() throws Exception {
        GetTransactionsRequest param = new GetTransactionsRequest(" ", 0, 0);
        HttpEntity<GetTransactionsRequest> request = new HttpEntity<GetTransactionsRequest>(param, headers);
        ResponseEntity<GetTransactionsResponse> responseEntityStr = restTemplate.postForEntity(gettransactions, request, GetTransactionsResponse.class);
        assertThat(responseEntityStr.getStatusCode(), equalTo(HttpStatus.OK));
        
        GetTransactionsResponse txs = responseEntityStr.getBody();
        logger.info("===== tansaction count : {} =====", txs.getResult().getTxs().size());
        for (Transaction tran : txs.getResult().getTxs()) {
        	logger.debug(tran.toString());
		}
    }
}
