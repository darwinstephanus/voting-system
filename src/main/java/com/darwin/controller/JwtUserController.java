package com.darwin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.darwin.config.JwtTokenUtil;
import com.darwin.dao.RoleDAO;
import com.darwin.dto.AdminDTO;
import com.darwin.dto.JwtRequest;
import com.darwin.dto.JwtResponse;
import com.darwin.dto.UserDTO;
import com.darwin.service.AdminService;
import com.darwin.service.JwtUserDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class JwtUserController {
	
	@Autowired
	private JwtUserDetailsService userService;
	
//	@Autowired
//	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	RoleDAO roleDAO;
    
//    @RequestMapping(value = "/sign_in_candidate", method = RequestMethod.POST)
//	public ResponseEntity<?> candidateSignIn(@RequestBody JwtRequest authenticationRequest)
//			throws Exception {
//		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//		final UserDetails userDetails = jwtInMemoryUserDetailsService
//				.loadUserByUsername(authenticationRequest.getEmail());
//		final String token = jwtTokenUtil.generateToken(userDetails);
//		return ResponseEntity.ok(new JwtResponse(token));
//	}
//	private void authenticate(String username, String password) throws Exception {
//		Objects.requireNonNull(username);
//		Objects.requireNonNull(password);
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
//    
//    @PostMapping("/sign_in_voter")
//    public String voterSignIn(@RequestBody JwtRequest signInDTO) {
//        return "aa";
//    }
//    
//    @PostMapping("/sign_in_user")
//    public String userSignIn(@RequestBody JwtRequest signInDTO) {
//        return "aa";
//    }
	
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping("/register/create_admin")
	public ResponseEntity<?> createAdmin(@RequestBody AdminDTO adminDTO) throws Exception {
		adminService.createAdmin(adminDTO);
		UserDTO user = new UserDTO();
		user.setUsername(adminDTO.getUsername());
		user.setPassword(adminDTO.getPassword());
		user.setRole(roleDAO.getOne((long) 1).getName());
		
		return ResponseEntity.ok(userService.save(user));
	}
	
//	@PostMapping("/register/create_candidate")
//	public ResponseEntity<?> createCandidate(@RequestBody CandidateDTO candidateDTO) throws Exception{
//		adminService.createCandidate(candidateDTO);
//		return ResponseEntity.ok(userService.save(user));
//	}
//	
//	@PostMapping("/register/create_voter")
//	public ResponseEntity<?> createVoter(@RequestBody VoterDTO voterDTO) throws Exception{
//		adminService.createVoter(voterDTO);
//		return ResponseEntity.ok(userService.save(user));
//	}
	
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/auth/login";
    }
}
