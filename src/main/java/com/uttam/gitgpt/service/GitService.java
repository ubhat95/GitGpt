package com.uttam.gitgpt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uttam.gitgpt.dto.GitUserInfoResponse;
import com.uttam.gitgpt.restclient.GitRestClient;
import com.uttam.gitgpt.restclient.GptRestClient;

import jakarta.annotation.PostConstruct;
import lombok.Setter;

@Service
public class GitService {

	@Autowired
	GitRestClient restClient;
	
	@Value("${gpt.model}")
	private String gptModel;
	
	@Setter
	private ObjectMapper objectMapper;
	
	
	static final String USER_INFO_URL= "/users/%s";
	static final Map<String, String> EMPTY_HEADERS =null; 
	static final Map<String, Object> EMPTY_COMMON_PARAMS =null; 
	
	@PostConstruct
	protected void initMapper() {
		this.objectMapper = new ObjectMapper();
	}
	
	
	public GitUserInfoResponse getUserInfo(String username) {
		String url = String.format(USER_INFO_URL, username);
		return restClient.get(restClient.getHostname(), url, GitUserInfoResponse.class, EMPTY_HEADERS, EMPTY_COMMON_PARAMS);
		
	}
	
	
	
	/*
	 * public String getResponseFromGPT(String prompt) {
	 * 
	 * ChatGPTRequest chatGPTRequest = prepareGptRequest(prompt); String postPayload
	 * = createJsonPayload(chatGPTRequest);
	 * 
	 * ChatGptResponse response = restClient.post(restClient.getHostname(),
	 * CHAT_URL, ChatGptResponse.class, postPayload, EMPTY_HEADERS,
	 * EMPTY_COMMON_PARAMS); return
	 * response.getChoices().get(0).getMessage().getContent();
	 * 
	 * }
	 */
	
	
	private <T> String createJsonPayload(final T requestObject) {
		try {
			return objectMapper.writeValueAsString(requestObject);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * private ChatGPTRequest prepareGptRequest(String prompt) { List<Message>
	 * messages = new ArrayList<>(); messages.add(new Message(Roles.USER.getRole(),
	 * prompt)); return
	 * ChatGPTRequest.builder().model(gptModel).messages(messages).build(); }
	 */

	
	
}
