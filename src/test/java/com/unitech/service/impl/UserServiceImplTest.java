package com.unitech.service.impl;

import com.unitech.dto.UserDto;
import com.unitech.dto.UserResponse;
import com.unitech.entity.User;
import com.unitech.mapper.UserMapper;
import com.unitech.model.AuthenticationRequest;
import com.unitech.repository.UserRepository;
import com.unitech.security.details.CustomUserDetails;
import com.unitech.security.service.JwtService;
import com.unitech.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void register() {

        String pin = "AH65TY8";
        var userDto = UserDto.builder()
                .pin("AH65TY8")
                .build();

        var user = User.builder()
                .id(1L)
                .pin("AH65TY8")
                .build();

        Mockito.when(userRepository.findByPin(userDto.getPin())).thenReturn(Optional.ofNullable(user));
        Optional<User> userByPin = userService.getUserByPin(pin);
        UserResponse userResponse = UserMapper.INSTANCE.mapEntityToDto(userByPin.get());
        assertThat(userResponse.getPin()).isEqualTo(pin);
    }

    @Test
    void authentication() {

        var authRequest = AuthenticationRequest.builder()
                .pin("AH65TY8")
                .password("1234567")
                .build();

        var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        var customUserDetails = CustomUserDetails.builder()
                .user(User.builder()
                        .pin("AH65TY8")
                        .password("1234567")
                        .serial("AA658834767")
                        .firstName("Test")
                        .lastName("Testov")
                        .phone("994506778899")
                        .build())
                .build();

        Mockito.when(customUserDetailsService.loadUserByUsername(authRequest.getPin())).thenReturn(customUserDetails);
        Mockito.when(jwtService.generateToken(customUserDetails)).thenReturn(token);

        var response = userService.authentication(authRequest);
        assertThat(response.getToken()).isEqualTo(token);
    }
}