package com.spring.postme.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/dashboard")
	public String showPostDashboard() {
		return "postDashboard";
	}

	@GetMapping("/main")
	public String showMainPage() {
		return "main"; 
	}

	@GetMapping("/login")
	public String showLoginPage(HttpSession session) {
		if (session.getAttribute("user") != null) {
			return "redirect: /";
		}
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}

	@GetMapping("/postdetail")
	public String showPostDetailPage() {
		return "postDetail";
	}

	@GetMapping("/admin/settings")
	public String showAdminSettingPage() {
		return "AdminSetting";
	}
}
