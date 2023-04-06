package com.unitech.service;

import com.unitech.dto.UserResponse;
import com.unitech.entity.User;
import com.unitech.model.AuthenticationRequest;
import com.unitech.model.AuthenticationResponse;
import com.unitech.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByPin(String pin);

    UserResponse register(UserDto userDto);

    AuthenticationResponse authentication(AuthenticationRequest request);
}
