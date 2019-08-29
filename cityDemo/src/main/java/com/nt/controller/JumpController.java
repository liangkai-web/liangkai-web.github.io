package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class JumpController {
	
	@RequestMapping("/index")
	public String jumpToindex() {
		return "indextemp";
	}
	@RequestMapping("/register")
	public String jumpToRegister() {
		return "register";
	}
	@RequestMapping("/login")
	public String jumpToLogin() {
		return "login";
	}
	@RequestMapping("/left")
	public String jumpToLeft() {
		return "left";
	}
	
	@RequestMapping("/main2")
	public String jumpToMain() {
		return "main2";
	}
	
	@RequestMapping("/jumpUpdate")
	public String jumpUpdate() {
		return "update";
	}

}
