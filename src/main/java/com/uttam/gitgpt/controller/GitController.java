package com.uttam.gitgpt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uttam.gitgpt.dto.GitUserInfoResponse;
import com.uttam.gitgpt.service.GitService;


@Component
@RestController
@RequestMapping("/github")
public class GitController {
	
	@Autowired
	GitService gitService;
	
	@GetMapping("/user")
    public GitUserInfoResponse getUserInfo(@RequestParam("username") String username){
		return gitService.getUserInfo(username);      
    }
}
