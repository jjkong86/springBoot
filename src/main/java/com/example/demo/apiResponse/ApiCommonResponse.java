package com.example.demo.apiResponse;

import lombok.Data;
import static com.example.demo.constant.Constant.*;
@Data
public class ApiCommonResponse {
    int code = CODE_SUCCESS;
    String error;
}
