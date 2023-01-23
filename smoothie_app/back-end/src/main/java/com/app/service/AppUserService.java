package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;

/**
 * Service for AppUser Entity
 * @author Kamlesh
 *
 */
@Service
public class AppUserService {
	
    @Autowired
    private UserRepository repository;

    
    /**
     * Method to get user role by username
     * @param username
     * @return user role
     */
    public String findRoleByUserName(String username) {
        User user = repository.findByUserName(username);
        return user.getRole();
    }
}