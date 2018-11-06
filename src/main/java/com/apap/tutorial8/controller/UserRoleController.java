package com.apap.tutorial8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tutorial8.model.PasswordUpdaterModel;
import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	private ModelAndView addUserSubmit(@RequestParam(value = "username") String username, @ModelAttribute PasswordUpdaterModel pass, RedirectAttributes redir) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserRoleModel user = userService.getUser(username);
		
		String message = "";
		if (pass.getPasswordBaru().equals(pass.getKonfirmasiPasswordBaru())) {
			if (passwordEncoder.matches(pass.getPasswordLama(), user.getPassword())) {
				userService.changePassword(user, pass.getPasswordBaru());
				message = "Password Berhasil Diubah";
			} else {
				message = "Password Lama Tidak Sesuai";
			}
		} else {
			message = "Konfirmasi password tidak sesuai";
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		redir.addFlashAttribute("message", message);
		return modelAndView;
	}

}
