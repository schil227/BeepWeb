package com.avionte.status.beepbeep.core.data;

import com.pi4j.io.gpio.Pin;

public class OutputConfiguration {
	private final String name;
	private final String[] urls;
	private final RequestType requestType;
	private final ResponseDataType responseType;
	private final String responseProperty;
	private final String responseSuccessValue;
	private final boolean failOnBadResponse;
	private final String usernamePropertyKey;
	private final String passwordPropertyKey;
	private final Pin pin; 
	
	public OutputConfiguration (
			String name,
			String[] urls, 
			RequestType requestType, 
			ResponseDataType responseType, 
			String responseProperty, 
			String responseSuccessValue, 
			boolean failOnBadResponse, 
			Pin pin, 
			String usernamePropertyKey,
			String passwordPropertyKey){
		this.name = name;
		this.urls = urls;
		this.requestType = requestType;
		this.responseType = responseType;
		this.responseProperty = responseProperty;
		this.responseSuccessValue = responseSuccessValue;
		this.failOnBadResponse = failOnBadResponse;
		this.pin = pin;
		this.usernamePropertyKey = usernamePropertyKey;
		this.passwordPropertyKey = passwordPropertyKey;
	}

	public String getName() {
		return name;
	}
	
	public String[] getUrls() {
		return urls;
	}

	public RequestType getRequestType() {
		return requestType;
	}
	
	public ResponseDataType getResponseType() {
		return responseType;
	}

	public String getResponseSuccessValue() {
		return responseSuccessValue;
	}

	public String getResponseProperty() {
		return responseProperty;
	}

	public boolean isFailOnBadResponse() {
		return failOnBadResponse;
	}

	public Pin getPin() {
		return pin;
	}

	public String getUsernamePropertyKey() {
		return usernamePropertyKey;
	}

	public String getPasswordPropertyKey() {
		return passwordPropertyKey;
	}
}
