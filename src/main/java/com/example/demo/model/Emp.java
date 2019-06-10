package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Data
@Entity
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "deptno", nullable=true, insertable = false, updatable = false)
	private Dept dept;
	
	@Override
	public String toString() {
		return String.format("Emp[empno=%d, ename=%s, job=%s, mgr=%d, hiredate=%s, sal=%d, comm=%d, deptno=%d, dname=%s, loc=%s", 
				empno, ename, job, mgr, hiredate, sal, comm, deptno, dept.getDname(), dept.getLoc());
	}
}
