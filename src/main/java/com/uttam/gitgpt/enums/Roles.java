package com.uttam.gitgpt.enums;

import lombok.Getter;

@Getter
public enum Roles {
	USER("user"),
	SYSTEM("system");
	
	private String role;
	
	Roles(String role){
		this.role=role;
	}
}
