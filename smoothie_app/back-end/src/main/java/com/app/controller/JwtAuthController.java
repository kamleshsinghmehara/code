package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName(), role);
    }
}