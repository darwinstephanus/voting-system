package com.darwin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwin.model.Admin;

public interface AdminDAO extends JpaRepository<Admin, Long> {

	Admin findByUsername(String username);

}
