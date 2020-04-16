package com.darwin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darwin.dto.ChangePasswordDTO;
import com.darwin.dto.ComplainDTO;
import com.darwin.dto.ResultsDTO;
import com.darwin.dto.VoterDTO;
import com.darwin.model.Complain;
import com.darwin.model.Election;
import com.darwin.model.Voter;
import com.darwin.service.VoterService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/voter")
@PreAuthorize("hasRole('ROLE_VOTER')")
public class VoterController {

	@Autowired
	VoterService voterService;
	
	@GetMapping(value="/{id}")
    public Voter readVoter(@PathVariable("id") long id) {
        return voterService.getVoterById(id);
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/information")
    public Voter getCandidateByUsername(@RequestParam String username) {
        Voter voter = voterService.getVoterByUsername(username);
        return voter;
    }
	
	@RequestMapping(value="/update_voter" ,method = RequestMethod.PUT)
    public void updateVoter(@RequestBody VoterDTO voterDTO) {
        voterService.updateVoter(voterDTO);
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/changePassword")
    public void changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		voterService.changePassword(changePasswordDTO);
    }
	
	@RequestMapping(value="/file_complain" ,method = RequestMethod.POST)
    public Complain fileComplain(@RequestBody ComplainDTO complainDTO) {
        Complain complain = voterService.fileComplain(complainDTO);
        return complain;
    }
	
	@PostMapping("/create_vote")
    public ResponseEntity<?> createVote(@RequestBody ResultsDTO resultsDTO) throws Exception {
        return ResponseEntity.ok(voterService.createVote(resultsDTO));
    }
	
	@GetMapping(value= "/view_all_results/{id}")
	public List<Election> getResults(@PathVariable("id") long seatId) {
		return voterService.getResults(seatId); 
	}
	
	@GetMapping(value= "/all_elections")
	public List<Election> getAllElections() {
		return voterService.getAllElections(); 
	}
	
	@GetMapping(value= "/past_elections")
	public List<Election> getAllCompletedElections() {
		return voterService.getAllCompletedElections(); 
	}
	
	@GetMapping(value= "/ongoing_elections")
	public List<Election> getAllOngoingElections() {
		return voterService.getAllOngoingElections(); 
	}
	
//	@GetMapping(value= "/ongoing_elections")
//	public List<Election> getAllOngoingElections() {
//		return voterService.getAllOngoingElections(); 
//	}
}
