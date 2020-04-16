package com.darwin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darwin.model.Election;
import com.darwin.model.Results;
import com.darwin.model.Voter;

public interface ResultsDAO extends JpaRepository<Results, Long> {

	boolean existsByVoterId(Long voterId);

	Results findByVoterId(Long voterId);

	boolean existsByVoterIdAndElectionId(Voter voterId, Election electionId);

	boolean existsByVoterIdOrElectionId(Long voterId, Long electionId);

	void deleteAllByElectionId(Election electionId);

	void deleteByElectionId(Election electionId);

}
