package com.springboot.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByDepartmentCode(String departmentCode);
}
