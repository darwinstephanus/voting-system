package com.darwin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darwin.dao.CandidateDAO;
import com.darwin.dao.ComplainDAO;
import com.darwin.dao.ElectionDAO;
import com.darwin.dao.GovernmentSeatDAO;
import com.darwin.dao.ResultsDAO;
import com.darwin.dao.UserDAO;
import com.darwin.dao.VoterDAO;
import com.darwin.dto.CandidateDTO;
import com.darwin.dto.ChangePasswordDTO;
import com.darwin.dto.ComplainDTO;
import com.darwin.dto.ResultsDTO;
import com.darwin.dto.VoterDTO;
import com.darwin.model.Admin;
import com.darwin.model.Candidate;
import com.darwin.model.Complain;
import com.darwin.model.Election;
import com.darwin.model.GovernmentSeat;
import com.darwin.model.Results;
import com.darwin.model.User;
import com.darwin.model.Voter;

@Service
public class VoterService {

	@Autowired
	VoterDAO voterDAO;
	
	@Autowired
	ComplainDAO complainDAO;
	
	@Autowired
	CandidateDAO candidateDAO;
	
	@Autowired
	ElectionDAO electionDAO;
	
	@Autowired
	ResultsDAO resultsDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	GovernmentSeatDAO governmentSeatDAO;
	

	public void updateVoter(VoterDTO voterDTO) {
		Voter voter = voterDAO.findByEmail(voterDTO.getEmail());
		voter.setVoterFirstName(voterDTO.getVoterFirstName())
				.setVoterLastName(voterDTO.getVoterLastName())
				.setAddress(voterDTO.getAddress())
				.setBirthday(voterDTO.getBirthday())
				.setCity(voterDTO.getCity())
				.setSsn(voterDTO.getSsn())
				.setState(voterDTO.getState());
		voterDAO.save(voter);
	}

	public void changePassword(ChangePasswordDTO changePasswordDTO) {
		User user = userDAO.findByUsername(changePasswordDTO.getUsername());
		if(user.getPassword() == changePasswordDTO.getPassword()) {
			user.setPassword(changePasswordDTO.getNewPassword());
		}
		userDAO.save(user);
	}

	public Complain fileComplain(ComplainDTO complainDTO) {
		Voter voter = voterDAO.findById(complainDTO.getVoterId()).get();
		Complain complain = new Complain()
				.setVoterId(voter)
				.setType(complainDTO.getType())
				.setComplain(complainDTO.getComplain())
				.setIsChecked(false);
		complainDAO.save(complain);
		return complain;
	}

	public Results createVote(ResultsDTO resultsDTO) throws Exception {
//		if(resultsDAO.existsByVoterIdAndElectionId(resultsDTO.getVoterId(), resultsDTO.getElectionId())) {
//			System.out.println("Voter Already Exists");
//			return;
//		}
		
		Date testDate = new Date();
		if(!isSameDay(electionDAO.getOne(resultsDTO.getElectionId()).getElectionDate(), testDate)) {
			System.out.println("Not the Election Date");
			throw new Exception("Not the Election Date");
		}
		
//		Candidate candidate = electionDAO.findById(resultsDTO.getElectionId()).get().getCandidateId();
		Election election = electionDAO.findById(resultsDTO.getElectionId()).get();
		Voter voter = voterDAO.getOne(resultsDTO.getVoterId());
		
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
		if(resultsDAO.existsByVoterIdAndElectionId(voter, election)) {
			System.out.println("Voter Already Exists");
			throw new Exception("Voter Already Exists");
		}
		
		
		System.out.println("Total voters: " +election.getTotalVoters());
		
		
		Results results = new Results()
//				.setCandidateId(candidate)
				.setElectionId(election)
				.setVoterId(voter);
		resultsDAO.save(results);
		
		election.setTotalVoters(election.getTotalVoters() + 1);
		electionDAO.save(election);
		
		
		System.out.println(results);
		return results;
		
	}
	
	public boolean isSameDay(Date date1, Date date2) {
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	    return fmt.format(date1).equals(fmt.format(date2));
	}

	public List<Election> getResults(long seatId) {
		GovernmentSeat governmentSeat = governmentSeatDAO.findById(seatId).get();
		List<Election> electionList = electionDAO.findAllBySeatId(governmentSeat);
		return electionList;
	}

	public Voter getVoterById(long id) {
		return voterDAO.getOne(id);
	}

	public List<Election> getAllElections() {
		return electionDAO.findAll();
	}

	public Voter getVoterByUsername(String username) {
		Voter voter = voterDAO.findByUsername(username);
		return voter;
	}

	public List<Election> getAllCompletedElections() {
		return electionDAO.findAllByIsCompleted(true);
	}

	public List<Election> getAllOngoingElections() {
		return electionDAO.findAllByIsCompleted(false);
	}

	public List<Voter> getVoterByCity(String city) {
		return voterDAO.findAllByCity(city);
	}
	
}
