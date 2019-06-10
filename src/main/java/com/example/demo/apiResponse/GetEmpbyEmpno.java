package com.example.demo.apiResponse;

import com.example.demo.model.Emp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetEmpbyEmpno extends CommonResponse{
	private Result result;
	
	public GetEmpbyEmpno() {
		this.result = new Result();
	}
	
	public void setResultNull() {
		this.result = null;
	}
	
	public void setResult(Emp emp) {
		this.result.setEmpno(emp.getEmpno());
		this.result.setEname(emp.getEname());
		this.result.setJob(emp.getJob());
		this.result.setMgr(emp.getMgr());
		this.result.setHiredate(emp.getHiredate());
		this.result.setSal(emp.getSal());
		this.result.setComm(emp.getComm());
		this.result.setDeptno(emp.getDeptno());
		this.result.setDept(emp.getDept());
	}

	@Data
	@EqualsAndHashCode(callSuper = false)
	public class Result extends Emp{
		
	}
}
