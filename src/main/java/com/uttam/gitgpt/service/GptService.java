package com.uttam.gitgpt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.NewCookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uttam.gitgpt.dto.ChatGPTRequest;
import com.uttam.gitgpt.dto.ChatGptResponse;
import com.uttam.gitgpt.dto.Message;
import com.uttam.gitgpt.enums.Roles;
import com.uttam.gitgpt.restclient.GptRestClient;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Setter;

@Service
@Data
public class GptService {
	
	@Value("${gpt.model}")
	private String gptModel;
	
	@Autowired
	private GptRestClient restClient;
	
	@Setter
	private ObjectMapper objectMapper;
	
	
	static final String CHAT_URL= "/v1/chat/completions";
	static final Map<String, String> EMPTY_HEADERS =null; 
	static final Map<String, Object> EMPTY_COMMON_PARAMS =null; 
	
	@PostConstruct
	protected void initMapper() {
		this.objectMapper = new ObjectMapper();
	}

	public String getResponseFromGPT(String prompt) {
		
		ChatGPTRequest chatGPTRequest = prepareGptRequest(prompt);
		String postPayload = createJsonPayload(chatGPTRequest);
		
		ChatGptResponse response = restClient.post(restClient.getHostname(), CHAT_URL, ChatGptResponse.class, postPayload, EMPTY_HEADERS, EMPTY_COMMON_PARAMS);
		return response.getChoices().get(0).getMessage().getContent();
		
	}

	private <T> String createJsonPayload(final T requestObject) {
		try {
			return objectMapper.writeValueAsString(requestObject);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private ChatGPTRequest prepareGptRequest(String prompt) {
		List<Message> messages = new ArrayList<>();
		messages.add(new Message(Roles.USER.getRole(), prompt));
		return ChatGPTRequest.builder().model(gptModel).messages(messages).build();
	}
	
	
}
