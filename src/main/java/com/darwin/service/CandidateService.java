package com.darwin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darwin.dao.CandidateDAO;
import com.darwin.dao.ElectionDAO;
import com.darwin.dao.GovernmentSeatDAO;
import com.darwin.dao.ResultsDAO;
import com.darwin.dao.UserDAO;
import com.darwin.dto.CalculationDTO;
import com.darwin.dto.CandidateDTO;
import com.darwin.dto.ChangePasswordDTO;
import com.darwin.model.Candidate;
import com.darwin.model.Election;
import com.darwin.model.GovernmentSeat;
import com.darwin.model.User;

@Service
public class CandidateService {

	@Autowired
	CandidateDAO candidateDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ResultsDAO resultsDAO;
	
	@Autowired
	ElectionDAO electionDAO;
	
	@Autowired
	GovernmentSeatDAO governmentSeatDAO;
	
	public Candidate updateCandidate(CandidateDTO candidateDTO) {
		Candidate candidate = candidateDAO.findById(candidateDTO.getCandidateId()).get();
		candidate.setCandidateFirstName(candidateDTO.getCandidateFirstName())
				.setCandidateLastName(candidateDTO.getCandidateLastName())
				.setCity(candidateDTO.getCity())
				.setState(candidateDTO.getState())
				.setContactNumber(candidateDTO.getContactNumber())
				.setParty(candidateDTO.getParty());
		candidateDAO.save(candidate);
		return candidate;
	}

	public void changePassword(ChangePasswordDTO changePasswordDTO) {
		User user = userDAO.findByUsername(changePasswordDTO.getUsername());
		if(user.getPassword() == changePasswordDTO.getPassword()) {
			user.setPassword(changePasswordDTO.getNewPassword());
		}
		userDAO.save(user);
	}

	public CandidateDTO getCandidateById(long id) {
		Candidate candidate = candidateDAO.getOne(id);
		CandidateDTO candidateDTO = new CandidateDTO()
				.setCandidateFirstName(candidate.getCandidateFirstName())
				.setCandidateLastName(candidate.getCandidateLastName())
				.setCity(candidate.getCity())
				.setContactNumber(candidate.getContactNumber())
				.setState(candidate.getState())
				.setEmail(candidate.getEmail())
				.setParty(candidate.getParty())
				.setUsername(candidate.getUsername());
		return candidateDTO;
	}

	public List<CalculationDTO> getResultsByElectionId(long electionId) {
//		List<Long> results = resultsDAO.findAllById(electionId);
		return null;
		
		//find all in results table with electionId == somethin
		//put it all into calculationDTO
		//calculate the total number of voters (Select Count(userId) from results where electionId=electionId))
		//select candidateId, count(userId) from results where electionId=electionId groupBy candidateId;
		//select count(userId) from results where electionId=electionId groupby candidateId;
		//select candidateId from results where electionId=electionId
	}

	public List<Election> getResults(long seatId) {
//		private Long electionId;
//		private Long candidateId;
//		private Long numberOfVoters;
//		private Boolean isCompleted;
		GovernmentSeat governmentSeat = governmentSeatDAO.findById(seatId).get();
		List<Election> electionList = electionDAO.findAllBySeatId(governmentSeat);
		return electionList;
	}

	public List<Election> getAllElections() {
		return electionDAO.findAll();
	}

	public CandidateDTO getCandidateByUsername(String username) {
		Candidate candidate = candidateDAO.findByUsername(username);
		CandidateDTO candidateDTO = new CandidateDTO()
				.setCandidateId(candidate.getCandidateId())
				.setCandidateFirstName(candidate.getCandidateFirstName())
				.setCandidateLastName(candidate.getCandidateLastName())
				.setCity(candidate.getCity())
				.setContactNumber(candidate.getContactNumber())
				.setState(candidate.getState())
				.setEmail(candidate.getEmail())
				.setParty(candidate.getParty())
				.setUsername(candidate.getUsername());
		return candidateDTO;
	}
	
	public List<Election> getAllCompletedElections() {
		return electionDAO.findAllByIsCompleted(true);
	}

	public List<Election> getAllOngoingElections() {
		return electionDAO.findAllByIsCompleted(false);
	}
}
