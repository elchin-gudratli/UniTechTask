package com.unitech.service.impl;

import com.unitech.dto.*;
import com.unitech.entity.User;
import com.unitech.enums.Role;
import com.unitech.mapper.UserMapper;
import com.unitech.model.AuthenticationRequest;
import com.unitech.model.AuthenticationResponse;
import com.unitech.repository.UserRepository;
import com.unitech.security.service.JwtService;
import com.unitech.service.CustomUserDetailsService;
import com.unitech.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * unitech
 * Elchin
 * 4/4/2023 12:28 PM
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private static final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public Optional<User> getUserByPin(String pin) {
        return userRepository.findByPin(pin);
    }

    @Override
    public UserResponse register(UserDto userDto) {
        log.info("ActionLog.register.start userDto: {}", userDto);

        var user = userRepository.findByPin(userDto.getPin());
        if (user.isPresent())
            throw new RuntimeException("User is exist for user pin: " + user.get().getPin());

        var newUser = User.builder()
                .pin(userDto.getPin())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .serial(userDto.getSerial())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phone(userDto.getPhone())
                .role(Role.USER)
                .build();

        log.info("ActionLog.register.end newUser: {}", newUser);
        return userMapper.mapEntityToDto(userRepository.save(newUser));
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPin(),
                request.getPassword()));

        var customUserDetails = customUserDetailsService.loadUserByUsername(request.getPin());
        var jwtToken = jwtService.generateToken(customUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
