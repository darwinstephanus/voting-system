package com.darwin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwin.model.Complain;

public interface ComplainDAO extends JpaRepository<Complain, Long> {

	List<Complain> findAllByIsChecked(boolean b);

}
