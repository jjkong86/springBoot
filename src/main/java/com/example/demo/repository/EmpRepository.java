package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	List<Emp> findByEnameAndSalLessThan(String ename, int sal);
	
	@Query("select t from Emp t where empno=:empno")
	Emp findByEmpnoSQL(@Param("empno") int deptno);
	
}
