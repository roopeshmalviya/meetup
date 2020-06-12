package com.unicodez.meetup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicodez.meetup.entity.User;
import com.unicodez.meetup.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		userRepository.save(user);

	}

}
