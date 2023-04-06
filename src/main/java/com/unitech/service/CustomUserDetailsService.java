package com.unitech.service;

import com.unitech.entity.User;
import com.unitech.repository.UserRepository;
import com.unitech.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByPin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return new CustomUserDetails(user);
    }
}
