package com.uttam.gitgpt.restclient;

import java.net.URI;
import java.util.Map;
import static java.util.Objects.nonNull;

import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public abstract class BaseRestClient {
	
	
	public URI buildUri(String host, String apiUrl, Map<String, Object> queryParam) {
		StringBuilder pathBuilder = new StringBuilder().append(apiUrl);
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(host).path(pathBuilder.toString());
		
		if(MapUtils.isNotEmpty(queryParam)) {
			queryParam.forEach((k,v) -> {
				if(nonNull(k) && nonNull(v)) {
					uriComponentsBuilder.queryParam(k, v);
				}
			
			});
		}
		return uriComponentsBuilder.build().toUri();
		
	}
	
	public<T> T get(String hostName, String apiUrl, Class<T> responseType, Map<String, String> headers, Map<String, Object> queryParam) {
		HttpEntity<String> entity = prepareEntityForJsonBody(null, headers);
		return makecall(buildUri(hostName, apiUrl, queryParam),HttpMethod.GET, entity, responseType); 
	
	}
	
	public<T> T post(String hostName, String apiUrl, Class<T> responseType, String jsonContent, Map<String, String> headers, Map<String, Object> queryParam) {
		HttpEntity<String> entity = prepareEntityForJsonBody(jsonContent, headers);
		return makecall(buildUri(hostName, apiUrl, queryParam),HttpMethod.POST, entity, responseType); 
	
	}
	
	public<T> T put(String hostName, String apiUrl, Class<T> responseType, String jsonContent, Map<String, String> headers, Map<String, Object> queryParam) {
		HttpEntity<String> entity = prepareEntityForJsonBody(jsonContent, headers);
		return makecall(buildUri(hostName, apiUrl, queryParam),HttpMethod.PUT, entity, responseType); 
	
	}
	
	public<T> T delete(String hostName, String apiUrl, Class<T> responseType, Map<String, String> headers, Map<String, Object> queryParam) {
		HttpEntity<String> entity = prepareEntityForJsonBody(null, headers);
		return makecall(buildUri(hostName, apiUrl, queryParam),HttpMethod.DELETE, entity, responseType); 
	
	}
	

	protected abstract <T> T makecall(URI buildUri, HttpMethod get, HttpEntity<String> entity, Class<T> responseType);

	protected abstract HttpEntity<String> prepareEntityForJsonBody(String bodyJson, Map<String, String> headers);

	public RestTemplate initialiseTemplate() {
		return new RestTemplate();
	}

}
