package com.triajiramadhan.quiz.constant;

public enum UserRole {

	ADMIN("Admin", "ADM"),
	CANDIDATE("Candidate", "CDT");

	private String roleName;
	private String roleCode;

	UserRole(String roleName, String roleCode) {
		this.roleName = roleName;
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}
	
	public String getRoleCode() {
		return roleCode;
	}
}
