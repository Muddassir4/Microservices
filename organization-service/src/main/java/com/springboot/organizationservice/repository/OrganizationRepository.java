package com.springboot.organizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.organizationservice.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	Organization findByOrganizationCode(String organizationCode);
}
