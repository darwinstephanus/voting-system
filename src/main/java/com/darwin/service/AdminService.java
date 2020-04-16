package com.darwin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.darwin.dao.AdminDAO;
import com.darwin.dao.CandidateDAO;
import com.darwin.dao.ComplainDAO;
import com.darwin.dao.ElectionDAO;
import com.darwin.dao.GovernmentSeatDAO;
import com.darwin.dao.ResultsDAO;
import com.darwin.dao.RoleDAO;
import com.darwin.dao.UserDAO;
import com.darwin.dao.VoterDAO;
import com.darwin.dto.AdminDTO;
import com.darwin.dto.CandidateDTO;
import com.darwin.dto.ElectionDTO;
import com.darwin.dto.GovernmentSeatDTO;
import com.darwin.dto.ResultsDTO;
import com.darwin.dto.VoterDTO;
import com.darwin.model.Admin;
import com.darwin.model.Candidate;
import com.darwin.model.Complain;
import com.darwin.model.Election;
import com.darwin.model.GovernmentSeat;
import com.darwin.model.Results;
import com.darwin.model.Role;
import com.darwin.model.User;
import com.darwin.model.Voter;

@Service
public class AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Autowired
	CandidateDAO candidateDAO;

	@Autowired
	VoterDAO voterDAO;

	@Autowired
	ElectionDAO electionDAO;

	@Autowired
	GovernmentSeatDAO governmentSeatDAO;

	@Autowired
	ResultsDAO resultsDAO;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ComplainDAO complainDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public Admin createAdmin(AdminDTO adminDTO) throws Exception {

		if (userDAO.findByUsername(adminDTO.getUsername()) != null) {
			throw new Exception("Username Is Already Used!");
		}

		Admin admin = new Admin().setAdminFirstName(adminDTO.getAdminFirstName())
				.setAdminLastName(adminDTO.getAdminLastName()).setUsername(adminDTO.getUsername())
				.setEmail(adminDTO.getEmail());
		Role role = roleDAO.getOne((long) 1);
		User user = new User().setUsername(adminDTO.getUsername()).setPassword(adminDTO.getPassword()).setRoleId(role);
		userDAO.save(user);
		adminDAO.save(admin);
		return admin;
	}

	public Candidate createCandidate(CandidateDTO candidateDTO) throws Exception {

		if (userDAO.findByUsername(candidateDTO.getUsername()) != null) {
			throw new Exception("Username Is Already Used!");
		}

		Candidate candidate = new Candidate();
		candidate.setCandidateFirstName(candidateDTO.getCandidateFirstName())
				.setCandidateLastName(candidateDTO.getCandidateLastName()).setCity(candidateDTO.getCity())
				.setState(candidateDTO.getState()).setContactNumber(candidateDTO.getContactNumber())
				.setEmail(candidateDTO.getEmail()).setUsername(candidateDTO.getUsername())
				.setParty(candidateDTO.getParty());

		Role role = roleDAO.getOne((long) 2);

		User newUser = new User();
		newUser.setUsername(candidateDTO.getUsername());
		newUser.setPassword(bcryptEncoder.encode(candidateDTO.getPassword()));
		newUser.setRoleId(role);

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(candidateDTO.getEmail());
		msg.setSubject("Your New Created Candidate Account for Election");
		msg.setText("Dear " + candidateDTO.getCandidateFirstName() + ",\r\n" + "\r\n"
				+ "Congratulations on your next step for the candidacy. Here we have your new personalized account for our e-voting website.\r\n"
				+ "\r\n" + "Email Address: " + candidateDTO.getEmail() + "\r\n" + "Password: " + candidateDTO.getPassword()  + "\r\n"
				+ "\r\n"
				+ "Here you will be able to access your own data in our website and also view the results of the voting. Please feel free to change your personalized data in the website. "
				+ "\r\n \r\n" + "Best regards,\r\n" + "\r\n" + "Voting Agency");

		javaMailSender.send(msg);

		userDAO.save(newUser);
		candidateDAO.save(candidate);

		return candidate;
	}

	public Voter createVoter(VoterDTO voterDTO) throws Exception {

		if (userDAO.findByUsername(voterDTO.getUsername()) != null) {
			throw new Exception("Username Is Already Used!");
		}

		Voter voter = new Voter().setVoterFirstName(voterDTO.getVoterFirstName())
				.setVoterLastName(voterDTO.getVoterLastName()).setEmail(voterDTO.getEmail())
				.setAddress(voterDTO.getAddress()).setBirthday(voterDTO.getBirthday()).setCity(voterDTO.getCity())
				.setSsn(voterDTO.getSsn()).setUsername(voterDTO.getUsername()).setState(voterDTO.getState());

		Role role = roleDAO.getOne((long) 3);

		User newUser = new User();
		newUser.setUsername(voterDTO.getUsername());
		newUser.setPassword(bcryptEncoder.encode(voterDTO.getPassword()));
		newUser.setRoleId(role);

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(voterDTO.getEmail());
		msg.setSubject("Your New Created Voter Account for Election");
		msg.setText("Dear " + voterDTO.getVoterFirstName() + ",\r\n" + "\r\n"
				+ "Thank you on registering for the election. Here we have created your new personalized account for our e-voting website.\r\n"
				+ "\r\n" + "Email Address: " + voterDTO.getEmail() + "\r\n" + "Password: " + voterDTO.getPassword() + "\r\n"
				+ "\r\n"
				+ "Here you will be able to access your own data in our website and also view the results of the voting. Please change your password immediately and feel free to change your personalized data in the website. "
				+ "\r\n \r\n" + "Best regards,\r\n" + "\r\n" + "Voting Agency");

		javaMailSender.send(msg);
		userDAO.save(newUser);
		voterDAO.save(voter);

		return voter;
	}

	public void updateAdmin(AdminDTO adminDTO) {

		Admin tempAdmin = adminDAO.getOne(adminDTO.getAdminId());
		tempAdmin.setAdminFirstName(adminDTO.getAdminFirstName()).setAdminLastName(adminDTO.getAdminLastName());
		adminDAO.save(tempAdmin);

	}

	public void updateCandidate(CandidateDTO candidateDTO) {
		Candidate tempCandidate = candidateDAO.getOne(candidateDTO.getCandidateId());
		tempCandidate.setCandidateFirstName(candidateDTO.getCandidateFirstName())
				.setCandidateLastName(candidateDTO.getCandidateLastName()).setCity(candidateDTO.getCity())
				.setState(candidateDTO.getState()).setContactNumber(candidateDTO.getContactNumber())
				.setParty(candidateDTO.getParty());
		candidateDAO.save(tempCandidate);
	}

	public void updateVoter(VoterDTO voterDTO) {
		Voter tempVoter = voterDAO.getOne(voterDTO.getVoterId());
		tempVoter.setVoterFirstName(voterDTO.getVoterFirstName()).setVoterLastName(voterDTO.getVoterLastName())
				.setAddress(voterDTO.getAddress()).setBirthday(voterDTO.getBirthday()).setCity(voterDTO.getCity())
				.setSsn(voterDTO.getSsn()).setState(voterDTO.getState());
		voterDAO.save(tempVoter);
	}

	public Admin deleteAdmin(long id) {
		Admin admin = adminDAO.findById(id).get();
		adminDAO.deleteById(id);
		return admin;
	}

	public Candidate deleteCandidate(long id) {
		Candidate candidate = candidateDAO.findById(id).get();
		candidateDAO.deleteById(id);
		return candidate;
	}

	public Voter deleteVoter(long id) {
		Voter voter = voterDAO.findById(id).get();
		voterDAO.deleteById(id);
		return voter;
	}

	public List<Admin> getAllAdmin() {
		return adminDAO.findAll();
	}

	public List<Candidate> getAllCandidate() {
		return candidateDAO.findAll();
	}

	public List<Voter> getAllVoter() {
		return voterDAO.findAll();
	}

	public Admin getAdminById(long id) {
		return adminDAO.getOne(id);
	}

	public Candidate getCandidateById(long id) {
		return candidateDAO.getOne(id);
	}

	public Voter getVoterById(long id) {
		return voterDAO.getOne(id);
	}

	/*
	 * GovernmentSeat
	 */
	public GovernmentSeat createGovernmentSeat(GovernmentSeatDTO governmentSeatDTO) {
		GovernmentSeat governmentSeat = new GovernmentSeat().setLocation(governmentSeatDTO.getLocation())
				.setState(governmentSeatDTO.getState()).setCity(governmentSeatDTO.getCity())
				.setTitle(governmentSeatDTO.getTitle())
				.setCompleted(false);
		governmentSeatDAO.save(governmentSeat);
		return governmentSeat;
	}

	public void updateGovernmentSeat(GovernmentSeatDTO governmentSeatDTO) {
		GovernmentSeat governmentSeat = governmentSeatDAO.getOne(governmentSeatDTO.getSeatId());
		governmentSeat.setLocation(governmentSeatDTO.getLocation()).setState(governmentSeatDTO.getState())
				.setCity(governmentSeatDTO.getCity()).setTitle(governmentSeatDTO.getTitle());
		governmentSeatDAO.save(governmentSeat);
	}

	public GovernmentSeat deleteGovernmentSeat(long id) {
		GovernmentSeat governmentSeat = governmentSeatDAO.getOne(id);
		governmentSeatDAO.deleteById(id);
		return governmentSeat;
	}

	public List<GovernmentSeat> getAllGovernmentSeats() {
		return governmentSeatDAO.findAll();
	}
	
	public boolean updateIsCompletedGovernmentSeat(long id) {
		GovernmentSeat governmentSeat = governmentSeatDAO.getOne(id);
		governmentSeat.setCompleted(!governmentSeat.isCompleted());
		governmentSeatDAO.save(governmentSeat);
		return governmentSeat.isCompleted();
	}

	/*
	 * Election
	 */
	public Election createElection(ElectionDTO electionDTO) throws Exception {
		
		Candidate candidate = candidateDAO.getOne(electionDTO.getCandidateId());
		GovernmentSeat governmentSeat = governmentSeatDAO.getOne(electionDTO.getSeatId());

		if(electionDAO.existsByCandidateIdAndSeatId(candidate, governmentSeat)) {
			throw new Exception ("Candidate with that title is already exist!");
		}

		Election election = new Election()
				.setSeatId(governmentSeat)
				.setCandidateId(candidate)
				.setCompleted(false)
				.setElectionDate(electionDTO.getElectionDate())
				.setTotalVoters(0L);
		electionDAO.save(election);
		return election;
		
	}

	public void updateElection(ElectionDTO electionDTO) {
		Election election = electionDAO.getOne(electionDTO.getElectionId());
		Candidate candidate = candidateDAO.getOne(electionDTO.getCandidateId());
		GovernmentSeat governmentSeat = governmentSeatDAO.getOne(electionDTO.getSeatId());

		election.setSeatId(governmentSeat).setCandidateId(candidate).setElectionDate(electionDTO.getElectionDate());
		electionDAO.save(election);
	}

	public boolean updateIsCompletedElection(Election election) {
		Election newElection = electionDAO.getOne(election.getElectionId());
		newElection.setCompleted(!newElection.isCompleted());
		electionDAO.save(newElection);
		
		if(newElection.isCompleted() == true) {
			resultsDAO.deleteByElectionId(election);
		}
		
		return newElection.isCompleted();
		
//		Election newElection = electionDAO.getOne(id);
//		newElection.setCompleted(!newElection.isCompleted());
//		electionDAO.save(newElection);
//		return newElection.isCompleted();
	}

	public Election deleteElection(long id) {
		Election election = electionDAO.findById(id).get();
		electionDAO.deleteById(id);
		return election;
	}

	public List<Election> getAllElections() {
		return electionDAO.findAll();
	}
	
	public List<Election> getAllCompletedElections() {
		return electionDAO.findAllByIsCompleted(true);
	}

	public List<Election> getAllOngoingElections() {
		return electionDAO.findAllByIsCompleted(false);
	}

	/*
	 * Results
	 */

	public void createResults(ResultsDTO resultsDTO) {
//		Candidate candidate = candidateDAO.getOne(resultsDTO.getCandidateId());
		Election election = electionDAO.getOne(resultsDTO.getElectionId());
		Voter voter = voterDAO.getOne(resultsDTO.getVoterId());

		Results results = new Results()
//				.setCandidateId(candidate)
				.setElectionId(election).setVoterId(voter);
		resultsDAO.save(results);
	}

	public void updateResults(ResultsDTO resultsDTO) {
//		Candidate candidate = candidateDAO.getOne(resultsDTO.getCandidateId());
		Election election = electionDAO.getOne(resultsDTO.getElectionId());
		Voter voter = voterDAO.getOne(resultsDTO.getVoterId());

		Results results = resultsDAO.getOne(resultsDTO.getResultsId());
		results.setElectionId(election).setVoterId(voter);
		resultsDAO.save(results);
	}

	public Results deleteResults(long id) {
		Results results = resultsDAO.findById(id).get();
		resultsDAO.deleteById(id);
		return results;
	}

	public List<Results> getAllResults() {
		return resultsDAO.findAll();
	}

	/*
	 * Complains
	 */

	public List<Complain> getAllComplains() {
		return complainDAO.findAll();
	}

	public List<Complain> getAllComplainsNotChecked() {
		return complainDAO.findAllByIsChecked(false);
	}

	/*
	 * User
	 */

	public List<User> getAllUsers() {
		return userDAO.findAll();
	}

	public AdminDTO getAdminByUsername(String username) {
		Admin admin = adminDAO.findByUsername(username);
		AdminDTO adminDTO = new AdminDTO()
				.setAdminFirstName(admin.getAdminFirstName())
				.setAdminLastName(admin.getAdminLastName())
				.setEmail(admin.getEmail())
				.setUsername(admin.getUsername())
				.setAdminId(admin.getAdminId());
		return adminDTO;
	}

	
}
