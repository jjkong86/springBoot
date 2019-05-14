package com.example.demo.restApi;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Emp;
import com.example.demo.model.EmpRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class HelloController {
	private static final Logger log = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	EmpRepository empRepository;
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("/index");
		return mav;
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/emp")
	public List<Emp> empList(ModelAndView mav) {
		log.info("find All emp List");
		List<Emp> empList = Lists.newArrayList(empRepository.findAll());
		return empList;
	}
	
	@RequestMapping("/emp/{empno}")
	public Emp empToEmpno(@PathVariable("empno") int empno ) {
		log.info("find empno : {}", empno);
		Emp emp = empRepository.findByEmpnoSQL(empno);
		log.info(emp.toString());
		return emp;
	}
	
	@RequestMapping(value ="/emp/add", method=RequestMethod.GET)
	public ModelAndView empAdd(@Valid Emp emp, BindingResult result, ModelAndView mav) {
		log.info(emp.toString());
        if (result.hasErrors()) {
        	mav.setViewName("/emp");
            return mav;
        } else {
            Emp empData = empRepository.save(emp);
            mav.setViewName("redirect:/emp/" + empData.getEmpno());
            return mav;
        }
	}
}
