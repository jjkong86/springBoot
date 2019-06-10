package com.example.demo.apiResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.example.demo.objects.Transaction;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetTransactionsResponse extends ApiCommonResponse {

	private Result result;

    public GetTransactionsResponse() { }

	public GetTransactionsResponse(long totalCount, List<Transaction> txs) {
		this.result = new Result();
    	this.result.txs = txs;
	    this.result.totalCount = totalCount;
    }

	@Data
    public class Result {
    	private long totalCount;
		private List<Transaction> txs;
	}

}
