package com.myweather.user_service.dto;

public class UpdateUserRequest {

	private String name;
	private String oldPassword;
	private String newPassword;

	public UpdateUserRequest() {}

	public UpdateUserRequest(String name, String oldPassword, String newPassword) {
		this.name = name;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}