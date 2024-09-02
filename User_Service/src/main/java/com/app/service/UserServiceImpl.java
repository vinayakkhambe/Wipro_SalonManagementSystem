package com.app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.UserDto;
import com.app.dto.UserDto2;
import com.app.entity.Gender;
import com.app.entity.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	ModelMapper mapper;
	
	public UserDto validUser(String email ,String password)
	{
		User u = urepo.findByEmail(email).get();
		
//		if(u.getPassword().equals(password))
//		{
//			return mapper.map(u, UserDto.class);
//		}
		if(passwordEncoder.matches(password, u.getPassword()))
		{
			return mapper.map(u, UserDto.class);
		}
		else
		{
			throw new IllegalArgumentException("Invalid password.");
		}
	}
	
	//GetByID
	public UserDto getUserByid(long id)
	{
		User u  =urepo.findById(id).get();
		UserDto dto = mapper.map(u, UserDto.class);
		return dto;
	}
	
	//GetByEmail
	@Override
	public UserDto getUserByEmail(String email) {
		User u  =urepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Not user present with email"+email));
		UserDto dto = mapper.map(u, UserDto.class);
		return dto;
	}


	//GetALL
	@Override
	public List<UserDto> getAllUser() {
		return Arrays.asList(mapper.map(urepo.findAll(),UserDto[].class));
	}

	//Create
	@Override
	public UserDto createUser(UserDto2 dto) 
	{
		String userEmail = dto.getEmail();
//		if (urepo.findByEmail(userEmail).isPresent())
//		{
//			return 
//		}
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
		User u = mapper.map(dto, User.class);
		u.setPassword(encodedPassword);
		User saveduder = urepo.save(u);
		return mapper.map(saveduder, UserDto.class);
       
	}

	//Update
	@Override
	public UserDto updateUser(long id, UserDto2 dto) {
		
		Optional<User> founduser = urepo.findById(id);
		if(founduser.isPresent())
		{
			User persistUser = founduser.get();
			persistUser.setAddress(dto.getAddress());
			persistUser.setEmail(dto.getEmail());
			persistUser.setName(dto.getName());
			persistUser.setGender(dto.getGender());
			persistUser.setMobile_no(dto.getMobile_no());
			persistUser.setPassword(dto.getPassword());
			persistUser.setRole_name(dto.getRole_name());
			
			User updateduser =urepo.save(persistUser);
			
			return mapper.map(updateduser, UserDto.class);
			
		}
		 
		return null;
	}

	//Delete
	@Override
	public UserDto deleteUser(long id) {
		
	User u = urepo.findById(id).orElseThrow(() -> new RuntimeException());	
	  urepo.deleteById(id);
		return mapper.map(u, UserDto.class);
	}



	

}
