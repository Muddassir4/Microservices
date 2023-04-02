package com.springboot.organizationservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.springboot.organizationservice.dto.OrganizationDto;
import com.springboot.organizationservice.entity.Organization;
import com.springboot.organizationservice.repository.OrganizationRepository;
import com.springboot.organizationservice.service.OrganizationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

	private OrganizationRepository organizationRepository;
	
	@Override
	public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
		Organization organization = new Organization();
		BeanUtils.copyProperties(organizationDto, organization);
		
		Organization savedOrganization =  organizationRepository.save(organization);
		OrganizationDto savedOrganizationDto = new OrganizationDto();
		BeanUtils.copyProperties(savedOrganization, savedOrganizationDto);
		
		return savedOrganizationDto;
	}

	@Override
	public OrganizationDto getOrganizationByCode(String organizationCode) {
		Organization organization =organizationRepository.findByOrganizationCode(organizationCode);
		
		OrganizationDto dto =new OrganizationDto();
		BeanUtils.copyProperties(organization, dto);
		return dto;
	}

	
}
