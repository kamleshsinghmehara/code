package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

import java.util.ArrayList;

/**
 * Service for Custom User Details
 * @author Kamlesh
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
    @Autowired
    private UserRepository repository;

    /**
     *Method to get User by username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }
}