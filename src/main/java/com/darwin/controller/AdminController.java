package com.darwin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.darwin.model.User;
import com.darwin.model.Voter;
import com.darwin.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Slf4j
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/create_admin")
	public ResponseEntity<?> createAdmin(@RequestBody AdminDTO adminDTO) throws Exception{
		return ResponseEntity.ok(adminService.createAdmin(adminDTO));
	}
	
	@PostMapping("/create_candidate")
	public ResponseEntity<?> createCandidate(@RequestBody CandidateDTO candidateDTO) throws Exception{
		return ResponseEntity.ok(adminService.createCandidate(candidateDTO));
	}
	
	@PostMapping("/create_voter")
	public ResponseEntity<?> createVoter(@RequestBody VoterDTO voterDTO) throws Exception{
		return ResponseEntity.ok(adminService.createVoter(voterDTO));
	}
	
	@RequestMapping(value = "/update_admin", method = RequestMethod.PUT)
    public void updateAdmin(@RequestBody AdminDTO adminDTO) {
        adminService.updateAdmin(adminDTO);
    }
	
	@RequestMapping(value = "/update_candidate", method = RequestMethod.PUT)
    public void updateCandidate(@RequestBody CandidateDTO candidateDTO) {
		adminService.updateCandidate(candidateDTO);
    }
	
	@RequestMapping(value = "/update_voter", method = RequestMethod.PUT)
    public void updateVoter(@RequestBody VoterDTO voterDTO) {
        adminService.updateVoter(voterDTO);
    }
	
	@RequestMapping(value = "/delete_admin/{id}", method = RequestMethod.DELETE)
    public Admin deleteAdmin(@PathVariable("id") long id) {
		Admin admin = adminService.deleteAdmin(id);
        return admin;
    }
	
	@RequestMapping(value = "/delete_candidate/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCandidate(@PathVariable("id") long id) {
        return ResponseEntity.ok(adminService.deleteCandidate(id));
    }
	
	@RequestMapping(value = "/delete_voter/{id}", method = RequestMethod.DELETE)
    public Voter deleteVoter(@PathVariable("id") long id) {
        Voter voter = adminService.deleteVoter(id);
        return voter;
    }
	
	@GetMapping(value= "/all_admin")
	public List<Admin> getAdmins() {
		log.info("Someone Get Admin");
		return adminService.getAllAdmin(); 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/information")
    public AdminDTO getAdminByUsername(@RequestParam String username) {
        AdminDTO adminDTO = adminService.getAdminByUsername(username);
        return adminDTO;
    }
	
	@GetMapping(value= "/all_candidates")
	public List<Candidate> getCandidates() {
		return adminService.getAllCandidate(); 
	}
	
	@GetMapping(value= "/all_voters")
	public List<Voter> getVoters() {
		return adminService.getAllVoter(); 
	}
	
	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
    public Admin readAdmin(@PathVariable("id") long id) {
        return adminService.getAdminById(id);
    }
	
	@RequestMapping(value = "/candidate/{id}", method = RequestMethod.GET)
    public Candidate readCandidate(@PathVariable("id") long id) {
        return adminService.getCandidateById(id);
    }
	
	@RequestMapping(value = "/voter/{id}", method = RequestMethod.GET)
    public Voter readVoter(@PathVariable("id") long id) {
        return adminService.getVoterById(id);
    }

	/*
	 * GovernmentSeat
	 */
	@PostMapping(value="/create_government_seat")
	public ResponseEntity<?> createGovernmentSeat(@RequestBody GovernmentSeatDTO governmentSeatDTO) {
		return ResponseEntity.ok(adminService.createGovernmentSeat(governmentSeatDTO));
	}
	
	@RequestMapping(value = "/update_government_seat", method = RequestMethod.PUT)
	public String updateGovernmentSeat(@RequestBody GovernmentSeatDTO governmentSeatDTO) {
		adminService.updateGovernmentSeat(governmentSeatDTO);
		return "Government Seat updated";
	}
	
	@RequestMapping(value = "/delete_government_seat/{id}", method = RequestMethod.DELETE)
	public GovernmentSeat deleteGovernmentSeat(@PathVariable("id") long id) {
		GovernmentSeat governmentSeat = adminService.deleteGovernmentSeat(id);
		return governmentSeat;
	}
	
	@GetMapping(value= "/all_government_seat")
	public List<GovernmentSeat> getAllGovernmentSeats() {
		return adminService.getAllGovernmentSeats(); 
	}
	
	@RequestMapping(value = "/update_completed_seat/{id}", method = RequestMethod.PUT)
	public boolean updateCompletedSeat(@PathVariable("id") long id) {
		boolean isCompleted = adminService.updateIsCompletedGovernmentSeat(id);
		return isCompleted;
	}

	/*
	 * Election
	 */
	@PostMapping(value="/create_election")
	public ResponseEntity<?> createElection(@RequestBody ElectionDTO electionDTO) throws Exception {
		return ResponseEntity.ok(adminService.createElection(electionDTO));
	}
	
	@RequestMapping(value = "/update_election", method = RequestMethod.PUT)
	public String updateElection(@RequestBody ElectionDTO electionDTO) {
		adminService.updateElection(electionDTO);
		return "Election created";
	}
	
	@RequestMapping(value = "/delete_election/{id}", method = RequestMethod.DELETE)
	public Election deleteElection(@PathVariable("id") long id) {
		Election election = adminService.deleteElection(id);
		return election;
	}
	
	@Transactional
	@RequestMapping(value = "/update_completed_election", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompletedElection(@RequestBody Election electionDTO) {
		return ResponseEntity.ok(adminService.updateIsCompletedElection(electionDTO));
	}

	@GetMapping(value= "/all_elections")
	public List<Election> getAllElections() {
		return adminService.getAllElections(); 
	}
	
	@GetMapping(value= "/ongoing_elections")
	public List<Election> getAllOngoingElections() {
		return adminService.getAllOngoingElections(); 
	}
	
	@GetMapping(value= "/past_elections")
	public List<Election> getAllCompletedElections() {
		return adminService.getAllCompletedElections(); 
	}
	
	/*
	 * Results
	 */
	@PostMapping(value="/create_results")
	public String createResults(@RequestBody ResultsDTO resultsDTO) {
		adminService.createResults(resultsDTO);
		return "Results created";
	}
	
	@RequestMapping(value = "/update_results", method = RequestMethod.PUT)
	public String updateResults(@RequestBody ResultsDTO resultsDTO) {
		adminService.updateResults(resultsDTO);
		return "Results updated";
	}
	
	@RequestMapping(value = "/delete_results/{id}", method = RequestMethod.DELETE)
	public Results deleteResults(@PathVariable("id") long id) {
		Results votings = adminService.deleteResults(id);
		return votings;
	}
	
	@GetMapping(value= "/all_results")
	public List<Results> getAllResults() {
		return adminService.getAllResults(); 
	}

	/*
	 * Complain
	 */
	
	@GetMapping(value= "/all_complains")
	public List<Complain> getAllComplains() {
		return adminService.getAllComplains(); 
	}
	
	/*
	 * User
	 */
	
	@GetMapping(value= "/all_users")
	public List<User> getAllUsers() {
		return adminService.getAllUsers(); 
	}
	
}
