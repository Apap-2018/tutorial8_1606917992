package com.apap.tutorial8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.tutorial8.model.PasswordUpdaterModel;

@Controller
public class PageController {
	@RequestMapping("/")
	public String home(Model model) {
		PasswordUpdaterModel pass = new PasswordUpdaterModel();
		model.addAttribute("passwordUpdater", pass);
		return "home";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
