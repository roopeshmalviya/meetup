package com.unicodez.meetup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicodez.meetup.entity.Role;
import com.unicodez.meetup.entity.User;
import com.unicodez.meetup.repository.RoleRepository;
import com.unicodez.meetup.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String singUp(Model model) {
		model.addAttribute("user", new User());
		return "signup"; 
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String singUp(@ModelAttribute("user") User user, Model model) {	
		//check if the form has any error
		System.out.println(user.getName());	
		
		if(!user.getPassword().equals(user.getVerifyPassword())) {
			model.addAttribute("confirmPass", true);
			System.out.println("If pass and vPass is not matched");
			return "signup";
		}  
		
		if(user.getName().length() <  6) {
			System.out.println("If name is less then 5 char");
			model.addAttribute("userLength", true);			
			return "signup";
		
		} 
		
		User existingUser = userRepository.findByEmail(user.getEmail());
		if(existingUser == null) {
			System.out.println("If pass and vPass is not matched"+user.getEmail());
			
			model.addAttribute("message", "Registration Success");			
			user.setLoginStatus(1);//1 For active and 2 For desabled		
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
			roleRepository.findById(2);
			Role role = new Role();
			role.setRoleId(2);
			role.setRoleName("USER");			
			user.setRole(role);	
			System.out.println(user.getRole());
			roleRepository.save(user.getRole());
			userRepository.save(user);
			
		}else if(!existingUser.getEmail().equals(user.getEmail())) {
			model.addAttribute("userAlreadyExist", true);
			model.addAttribute("userName", existingUser.getEmail());
			return "signup";
		}
				 
		return "signup"; 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String logIn(@ModelAttribute("user") User user, Model model) {	
		User existingUser = userRepository.findByEmail(user.getEmail());
		
		
		if(existingUser ==null) {		
			model.addAttribute("userNotExist", true);
			return "signup";
		}
		
		if(bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())) {		
			return "home";
		}else {
			System.out.println(existingUser.getPassword());	
			model.addAttribute("message", true);	
			return "signup";
		}
	}
	
	
	
}