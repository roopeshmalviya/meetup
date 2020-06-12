package com.unicodez.meetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unicodez.meetup.entity.User;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index(Model model ) {
		User user = new User();
		model.addAttribute(user);
		System.out.println("Index Page Callings....");
		return "index";
	}
	
	
	
	
	@RequestMapping("/userdetails")
	public String index1(Model model ) {
		User user = new User();
		model.addAttribute(user);
		System.out.println("Index Page Callings....");
		return "index";
	}
} 
