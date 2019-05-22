package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer> {
	
	
}
