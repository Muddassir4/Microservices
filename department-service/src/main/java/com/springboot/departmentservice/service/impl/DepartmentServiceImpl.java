package com.springboot.departmentservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.springboot.departmentservice.dto.DepartmentDto;
import com.springboot.departmentservice.entity.Department;
import com.springboot.departmentservice.repository.DepartmentRepository;
import com.springboot.departmentservice.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

		Department department = new Department();
		BeanUtils.copyProperties(departmentDto, department);

		Department savedDepartment = departmentRepository.save(department);

		DepartmentDto savedDepartmentDto = new DepartmentDto();
		BeanUtils.copyProperties(savedDepartment, savedDepartmentDto);

		return savedDepartmentDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {

		Department department = departmentRepository.findByDepartmentCode(departmentCode);

		DepartmentDto departmentDto = new DepartmentDto();
		
		BeanUtils.copyProperties(department, departmentDto);
		return departmentDto;
	}

}
