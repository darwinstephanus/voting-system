package com.darwin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.darwin.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
