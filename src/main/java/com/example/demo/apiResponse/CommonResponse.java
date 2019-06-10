package com.example.demo.apiResponse;

import com.example.demo.constant.Constant;

import lombok.Data;

@Data
public class CommonResponse implements Constant{
	int code = CODE_SUCCESS;
    String error = DESC_SUCCESS;
}
