package com.darwin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darwin.dto.CalculationDTO;
import com.darwin.dto.CandidateDTO;
import com.darwin.dto.ChangePasswordDTO;
import com.darwin.model.Election;
import com.darwin.service.CandidateService;

@RestController
@RequestMapping(value="/candidate")
@PreAuthorize("hasRole('ROLE_CANDIDATE')")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CandidateController {

	@Autowired
	CandidateService candidateService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CandidateDTO getCandidateById(@PathVariable("id") long id) {
        CandidateDTO candidateDTO = candidateService.getCandidateById(id);
        return candidateDTO;
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/information")
    public CandidateDTO getCandidateByUsername(@RequestParam String username) {
        CandidateDTO candidateDTO = candidateService.getCandidateByUsername(username);
        return candidateDTO;
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update_candidate")
    public ResponseEntity<?> updateCandidate(@RequestBody CandidateDTO candidateDTO) {
        return ResponseEntity.ok(candidateService.updateCandidate(candidateDTO));
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/change_password")
    public void changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		candidateService.changePassword(changePasswordDTO);
    }

	@GetMapping(value= "/view_candidate_result/{id}")
	public List<CalculationDTO> getResultsByElectionId(@PathVariable("id") long electionId) {
		return candidateService.getResultsByElectionId(electionId); 
	}
	
	@GetMapping(value= "/view_all_results/{id}")
	public List<Election> getResults(@PathVariable("id") long seatId) {
		return candidateService.getResults(seatId); 
	}
	
	@GetMapping(value= "/all_elections")
	public List<Election> getAllElections() {
		return candidateService.getAllElections(); 
	}
	
	@GetMapping(value= "/all_ongoing_elections")
	public List<Election> getAllOngoingElections() {
		return candidateService.getAllOngoingElections(); 
	}
	
	@GetMapping(value= "/all_past_elections")
	public List<Election> getAllPastElections() {
		return candidateService.getAllCompletedElections(); 
	}
}
