package com.example.SpringExmaple.Service;

import com.example.SpringExmaple.Configuration.CustomUserDetails;
import com.example.SpringExmaple.DTO.Users;
import com.example.SpringExmaple.Repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users appUser = repository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("Invalid username or password"));
        
        return new CustomUserDetails(appUser);
    }
}