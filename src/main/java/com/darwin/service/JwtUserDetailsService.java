package com.darwin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.darwin.dao.RoleDAO;
import com.darwin.dao.UserDAO;
import com.darwin.dto.UserDTO;
import com.darwin.model.Role;
import com.darwin.model.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
//	public boolean isUsernameAvailable(SignInDTO signInDTO) {
//		User user = userDao.findByUsername(signInDTO.getUsername());
//        if(user != null){
//            System.out.println("Username does not exist");
//            return false;
//        }
//        else{
//            return true;
//        }
//	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("saran".equals(username)) {
//			return new User("saran", "$2a$10$ymbH8XmHLuEaVAdaRVunf.erQqHgOy8ju.a4r8J7EIZ/wOiXiksc.",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}
	
//	public Collection<? extends GrantedAuthority> getRoles(User user) {
//		Collection<? extends GrantedAuthority> authorities = new HashSet<>();
//		Role role = user.getRoleId();
//		role.map(r -> {
//			return new SimpleGrantedAuthority(r.getRole());\
//		}
//		return authorities;
//		
////		Role roles = user.getRoleId();
////		return new SimpleGrantedAuthority(roles);
//	}

	private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        Role roles = user.getRoleId();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getName()));
		return authorities;
	}
	
	public User save(UserDTO user) {
		
		Role role = roleDAO.getOne((long) 1);
		
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRoleId(role);
		
		return userDAO.save(newUser);
	}

}
