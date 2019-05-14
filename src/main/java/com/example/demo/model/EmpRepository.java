package com.example.demo.model;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Emp;

public interface EmpRepository extends CrudRepository<Emp, Integer> {

//	List<Emp> findByNameAndAgeLessThan(String name, int age);
	
	@Query("select t from Emp t where empno=:empno")
	Emp findByEmpnoSQL(@Param("empno") int deptno);
	
}
