package com.darwin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwin.model.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {


}
