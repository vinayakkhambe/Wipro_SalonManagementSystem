package com.app.userservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.app.dto.UserDto;
import com.app.dto.UserDto2;
import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper; 
    
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private User user;
    private UserDto userDto;
    private UserDto2 userDto2;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("test@example.com");

        userDto2 = new UserDto2();
        userDto2.setEmail("test@example.com");
        userDto2.setPassword("password");
    }



    @Test
    void testGetUserById() {
        // Mock the repository to return a User when findById is called
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // Mock the model mapper to map User to UserDto
        when(modelMapper.map(any(User.class), any(Class.class))).thenReturn(userDto);

        // Call the service method
        UserDto result = userServiceImpl.getUserByid(1L);

        // Assert the result is not null and check other properties if necessary
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }



    @Test
    void testDeleteUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        
     // Mock the model mapper to map User to UserDto
        when(modelMapper.map(any(User.class), any(Class.class))).thenReturn(userDto);
        UserDto result = userServiceImpl.deleteUser(1L);
        
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }
 

}
