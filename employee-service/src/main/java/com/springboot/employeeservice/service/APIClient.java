package com.springboot.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import com.springboot.employeeservice.dto.DepartmentDto;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

	@GetMapping("/api/departments/{department-code}")
	DepartmentDto getDepartment(@PathVariable("department-code") String code);
}