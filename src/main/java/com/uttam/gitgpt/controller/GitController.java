package com.uttam.gitgpt.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
@RequestMapping("/git")
public class GitController {

	
	@GetMapping
	public String getFromGit() {
		return "here bro ";
	}
}
