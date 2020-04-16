package com.darwin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwin.model.Voter;

public interface VoterDAO extends JpaRepository<Voter, Long> {

	Voter findByEmail(String email);

	Voter findByUsername(String username);

	List<Voter> findAllByCity(String city);

}
