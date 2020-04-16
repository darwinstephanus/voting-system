package com.darwin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwin.model.Candidate;
import com.darwin.model.Election;
import com.darwin.model.GovernmentSeat;

public interface ElectionDAO extends JpaRepository<Election, Long>{

	List<Election> findAllBySeatId(GovernmentSeat governmentSeat);

	boolean existsByCandidateIdAndSeatId(Candidate candidate, GovernmentSeat governmentSeat);

	List<Election> findAllByIsCompleted(boolean b);

}
