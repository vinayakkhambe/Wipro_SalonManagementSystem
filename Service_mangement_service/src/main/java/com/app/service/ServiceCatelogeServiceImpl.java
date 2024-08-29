package com.app.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ServicesRequestDto;
import com.app.dto.ServicesResponseDto;
import com.app.entity.Services;
import com.app.repository.ServiceRepository;

@Service
public class ServiceCatelogeServiceImpl implements ServiceCatelogeService{
	
	@Autowired
	ServiceRepository srepository;
	
	@Autowired
	ModelMapper mapper;
	
	public ServicesResponseDto getServiceById(long id)
	{
		Services service =srepository.findById(id).orElseThrow(() -> new RuntimeException());
	    return mapper.map(service, ServicesResponseDto.class);	
	}

	@Override
	public List<ServicesResponseDto> getAllSevices() {
		
		
		List<ServicesResponseDto> list = Arrays.asList(mapper.map(srepository.findAll(), ServicesResponseDto[].class));
		return list;
	}

	@Override
	public ServicesResponseDto createService(ServicesRequestDto requestDto) {
		Services newService = mapper.map(requestDto, Services.class);
		 srepository.save(newService);
		 
		return mapper.map(newService, ServicesResponseDto.class);
	}

	@Override
	public ServicesResponseDto updateService(long serviceId, ServicesRequestDto requestDto) {
		
		Services persistService = srepository.findById(serviceId).orElseThrow(() -> new RuntimeException());
		persistService.setDetails(requestDto.getDetails());
		persistService.setName(requestDto.getName());
		persistService.setPrice(requestDto.getPrice());
		persistService.setType(requestDto.getType());
		
		Services updatedService = srepository.save(persistService);
		return mapper.map(updatedService, ServicesResponseDto.class);
	}

	@Override
	public ServicesResponseDto deleteService(long serviceId) {
		
		Services persistService  = srepository.findById(serviceId).orElseThrow(() -> new RuntimeException());
		srepository.delete(persistService);
		return mapper.map(persistService, ServicesResponseDto.class);
	}

}
