package com.uttam.gitgpt.dto;


import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatGPTRequest {
	
	private String model;
	private List<Message> messages;
}
