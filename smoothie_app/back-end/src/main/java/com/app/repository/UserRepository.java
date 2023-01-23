package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.User;

/**
 * Repository for User entity
 * @author Kamlesh
 *
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}