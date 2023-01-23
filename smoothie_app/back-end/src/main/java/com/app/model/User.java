package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for application users such as owner and customer
 * @author Kamlesh
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APP_USER")
public class User {
	@Id
    private int id;
	
    private String userName;
    
    private String password;
    
    private String email;
    
    private String role;    
}
