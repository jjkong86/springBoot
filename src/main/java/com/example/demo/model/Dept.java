package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Dept {
	
	@Id
	private int deptno;
	private String dname;
	private String loc;

	@JsonIgnore
	@OneToMany(mappedBy = "dept", cascade=CascadeType.ALL)
	private List<Emp> emps = new ArrayList<>();
	
}
