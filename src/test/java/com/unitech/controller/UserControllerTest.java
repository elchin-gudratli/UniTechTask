package com.unitech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unitech.dto.UserDto;
import com.unitech.dto.UserResponse;
import com.unitech.entity.User;
import com.unitech.model.AuthenticationRequest;
import com.unitech.model.AuthenticationResponse;
import com.unitech.security.details.CustomUserDetails;
import com.unitech.security.service.JwtService;
import com.unitech.service.CustomUserDetailsService;
import com.unitech.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void register() throws Exception{
        var userRequest = UserDto.builder()
                .pin("Q45FSGO")
                .password("1234567")
                .serial("AA658834767")
                .firstName("Test")
                .lastName("Testov")
                .phone("994506778899")
                .build();

        var userResponse = UserResponse.builder()
                .pin("Q45FSGO")
                .serial("AA658834767")
                .firstName("Test")
                .lastName("Testov")
                .phone("994506778899")
                .build();

        var requestJson = objectMapper.writeValueAsString(userRequest);
        var responseJson = objectMapper.writeValueAsString(userResponse);

        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/user/register")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        Mockito.when(userService.register(userRequest)).thenReturn(userResponse);

        var response = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated()).andReturn().getResponse();

        JSONAssert.assertEquals(responseJson, response.getContentAsString(), false);
    }

    @Test
    void authentication() throws Exception{
        var authRequest = AuthenticationRequest.builder()
                .pin("Q45FSGO")
                .password("1234567")
                .build();

        var authResponse = AuthenticationResponse.builder()
                .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
                .build();

        var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        var customUserDetails = CustomUserDetails.builder()
                .user(User.builder()
                        .pin("Q45FSGO")
                        .password("1234567")
                        .serial("AA658834767")
                        .firstName("Test")
                        .lastName("Testov")
                        .phone("994506778899")
                        .build())
                .build();

        var requestJson = objectMapper.writeValueAsString(authRequest);
        var responseJson = objectMapper.writeValueAsString(authResponse);

        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/user/authentication")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        Mockito.when(customUserDetailsService.loadUserByUsername(authRequest.getPin())).thenReturn(customUserDetails);
        Mockito.when(jwtService.generateToken(customUserDetails)).thenReturn(token);
        Mockito.when(userService.authentication(authRequest)).thenReturn(authResponse);

        var response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn().getResponse();

        JSONAssert.assertEquals(responseJson, response.getContentAsString(), false);
    }
}