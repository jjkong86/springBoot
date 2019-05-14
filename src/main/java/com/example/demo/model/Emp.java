package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Emp{
	
	@Id
	private Integer empno;
	
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	@Override
	public String toString() {
		return String.format("Emp[empno=%d, ename=%s, job=%s, mgr=%d, hiredate=%s, sal=%d, comm=%d, deptno=%d", 
				empno, ename, job, mgr, hiredate, sal, comm, deptno);
	}
}
