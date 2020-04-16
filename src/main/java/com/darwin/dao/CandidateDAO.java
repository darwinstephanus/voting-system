package com.darwin.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwin.model.Candidate;
import com.darwin.model.GovernmentSeat;

public interface CandidateDAO extends JpaRepository<Candidate, Long> {

	Candidate findByEmail(String email);

	Candidate findByUsername(String username);

}
