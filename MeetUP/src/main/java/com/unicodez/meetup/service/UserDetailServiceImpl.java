package com.unicodez.meetup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.unicodez.meetup.entity.User;
import com.unicodez.meetup.repository.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		CustomUserDetail userDetails=null;
		
		
		if(user!=null) {
			userDetails = new CustomUserDetail();
			userDetails.setUser(user);
			
		}
		else {
			throw new UsernameNotFoundException("User not found for email : "+username);
		}
		
		
		
		return userDetails;
	}

}
