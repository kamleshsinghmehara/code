package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.AuthRequest;
import com.app.service.AppUserService;
import com.app.util.JwtUtil;

/**
 * REST Controller class for handling request related to JWT authentication
 * @author Kamlesh
 *
 */
@RestController
@RequestMapping("/api/authenticate")
public class JwtAuthController {
	
	Logger logger = LoggerFactory.getLogger(JwtAuthController.class);

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AppUserService appUserService;
    
    /**
     * Method for generating JWT token based on the authRequest i.e. username and password
     * @param authRequest
     * @return JWT Token
     * @throws Exception
     */
    @PostMapping
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
    	String role = null;
        try {
        	role = appUserService.findRoleByUserName(authRequest.getUserName());
        	logger.debug("User role:", role);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (Exception ex) {
        	logger.error("inavalid username/password");
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName(), role);
    }
}