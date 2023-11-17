package com.uttam.gitgpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uttam.gitgpt.dto.ChatGPTRequest;
import com.uttam.gitgpt.dto.ChatGptResponse;
import com.uttam.gitgpt.service.GptService;



@Component
@RestController
@RequestMapping("/gpt")
public class GptController {
	
	@Autowired
	GptService gptService;
	
	
	@GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt){
		
		
		return gptService.getResponseFromGPT(prompt);
        
    }

}
