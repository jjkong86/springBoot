package com.example.demo.restApi;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Emp;
import com.example.demo.repository.EmpRepository;
import com.example.demo.response.GetEmpbyEmpno;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmpController {

	@Autowired
	EmpRepository empRepository;
	
	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public List<Emp> empList(ModelAndView mav) {
		log.info("find all emp list");
		List<Emp> empList = Lists.newArrayList(empRepository.findAll());
		return empList;
	}
	
	@RequestMapping(value = "/emp/{empno}", method = RequestMethod.GET)
	public GetEmpbyEmpno empToEmpno(@PathVariable("empno") int empno ) {
		log.info("start find empno : {}", empno);
		
		GetEmpbyEmpno res = new GetEmpbyEmpno();
		try {
			res.setResult(empRepository.findById(empno).get());	
			log.info(res.toString());
		} catch (Exception e) {
			res.setCode(404);
			res.setError("NOT FOUND");
			res.setResultNull();
		}
		return res;
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
	
	@RequestMapping(value = "/empJoinList", method = RequestMethod.GET)
	public List<Emp> empJoinDeptList() {
		log.info("find emp join dept list");
		List<Emp> empList = Lists.newArrayList(empRepository.findAll());
		if (CollectionUtils.isEmpty(empList)) {
			log.info("empList is empty");
			return Collections.<Emp>emptyList();
		}
		return empList;
	}
}
