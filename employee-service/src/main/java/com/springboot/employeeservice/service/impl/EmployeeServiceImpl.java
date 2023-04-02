package com.springboot.employeeservice.service.impl;

import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.springboot.employeeservice.dto.APIResponseDto;
import com.springboot.employeeservice.dto.DepartmentDto;
import com.springboot.employeeservice.dto.EmployeeDto;
import com.springboot.employeeservice.dto.OrganizationDto;
import com.springboot.employeeservice.entity.Employee;
import com.springboot.employeeservice.repository.EmployeeRepository;
import com.springboot.employeeservice.service.APIClient;
import com.springboot.employeeservice.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	private WebClient webClient;
	
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);

		Employee savedEmployee = employeeRepository.save(employee);
		
		EmployeeDto savedDto = new EmployeeDto();
		BeanUtils.copyProperties(savedEmployee, savedDto);

		return savedDto;
	}

	@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {
		
	Employee employee =	employeeRepository.findById(employeeId).get();
	
	DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
	
	OrganizationDto organizationDto = webClient.get()
			.uri("http://localhost:8083/api/organizations/"+employee.getOrganizationCode())
			.retrieve()
			.bodyToMono(OrganizationDto.class)
			.block();
	
	EmployeeDto employeeDto = new EmployeeDto();
	BeanUtils.copyProperties(employee, employeeDto);
	
	APIResponseDto apiResponseDto = new APIResponseDto();
	apiResponseDto.setEmployeeDto(employeeDto);
	apiResponseDto.setDepartmentDto(departmentDto);
	apiResponseDto.setOrganizationDto(organizationDto);
	
	return apiResponseDto;
	}

	public APIResponseDto getDefaultDepartment(Long employeeId,Exception exception) {
		Employee employee =	employeeRepository.findById(employeeId).get();
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentCode("RD001");
		departmentDto.setDepartmentName("R&D Department");
		departmentDto.setDepartmentDescription("Research and Development Department");
		
		EmployeeDto employeeDto = new EmployeeDto();
		BeanUtils.copyProperties(employee, employeeDto);
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployeeDto(employeeDto);
		apiResponseDto.setDepartmentDto(departmentDto);
		
		return apiResponseDto;
	}
	
}
