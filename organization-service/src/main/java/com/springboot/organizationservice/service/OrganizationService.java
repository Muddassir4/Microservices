package com.springboot.organizationservice.service;

import com.springboot.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

	OrganizationDto saveOrganization(OrganizationDto organizationDto);
	
	OrganizationDto getOrganizationByCode(String organizationCode);
	
}
