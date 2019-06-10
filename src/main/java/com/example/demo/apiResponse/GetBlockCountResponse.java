package com.example.demo.apiResponse;

import static com.example.demo.constant.Constant.CODE_SUCCESS;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data public class GetBlockCountResponse extends ApiCommonResponse {
    private long result;
    int code = CODE_SUCCESS;
    String error;
    
    public void setResponse(GetBlockCountResponse b) {
    	this.result = b.getResult();
    	this.code = b.getCode();
    	this.error = b.getError();
    }
}
