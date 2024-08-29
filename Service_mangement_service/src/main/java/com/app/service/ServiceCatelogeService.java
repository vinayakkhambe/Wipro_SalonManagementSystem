package com.app.service;

import java.util.List;

import com.app.dto.ServicesRequestDto;
import com.app.dto.ServicesResponseDto;

public interface ServiceCatelogeService {

	 ServicesResponseDto getServiceById(long id);

	 List<ServicesResponseDto> getAllSevices();
	 
	 ServicesResponseDto createService(ServicesRequestDto requestDto);
	 
	 ServicesResponseDto updateService( long serviceId , ServicesRequestDto requestDto);
	 
	 ServicesResponseDto deleteService( long serviceId);
	 
	 
}
