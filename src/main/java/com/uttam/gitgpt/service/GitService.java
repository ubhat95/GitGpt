package com.uttam.gitgpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uttam.gitgpt.restclient.GitRestClient;

@Service
public class GitService {

	@Autowired
	GitRestClient gitRestClient;
	
	
}
