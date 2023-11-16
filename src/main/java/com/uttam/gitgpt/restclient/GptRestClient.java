package com.uttam.gitgpt.restclient;

import static javax.ws.rs.core.HttpHeaders.CACHE_CONTROL;

import java.net.URI;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GptRestClient extends BaseRestClient{
	
	@Value("$gpt.hostname")
	private String hostname;
	
	private RestTemplate restTemplate;

	@Override
	protected <T> T makecall(URI uri, HttpMethod method, HttpEntity<String> entity, Class<T> responseType) {
		log.info("Git call : {} {} {}", method, uri,entity.getBody() );
		try {
		ResponseEntity<T> responseEntity = restTemplate.exchange(hostname, method, entity, responseType);
		return  responseEntity.getBody();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected HttpEntity<String> prepareEntityForJsonBody(String bodyJson, Map<String, String> headers) {
		MultiValueMap<String, String> finalHeaders = getDeafaultHeaders();
		if(MapUtils.isNotEmpty(headers)) {
			finalHeaders.setAll(headers);
		}
		
		return StringUtils.hasLength(bodyJson)? new HttpEntity<>(finalHeaders)
												: new HttpEntity<>(bodyJson, finalHeaders);
	}

	private MultiValueMap<String, String> getDeafaultHeaders() {
		//All default headers - ideally authorization tokens;
		HttpHeaders headers =  new HttpHeaders();
		headers.add(CACHE_CONTROL, hostname);
		return headers;
	}


}
