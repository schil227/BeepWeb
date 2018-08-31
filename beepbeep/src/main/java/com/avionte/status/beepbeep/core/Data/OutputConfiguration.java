package com.avionte.status.beepbeep.core.Data;

import com.avionte.status.beepbeep.core.ResponseDataType;
import com.pi4j.io.gpio.Pin;

public class OutputConfiguration {
	private final String[] urls;
	private final ResponseDataType responseType;
	private final String responseProperty;
	private final String responseSuccessValue;
	private final boolean failOnBadResponse;
	private final Pin pinCode; 
	
	public OutputConfiguration (String[] urls, ResponseDataType responseType, String responseProperty, String responseSuccessValue, boolean failOnBadResponse, Pin pinCode){
		this.urls = urls;
		this.responseType = responseType;
		this.responseProperty = responseProperty;
		this.responseSuccessValue = responseSuccessValue;
		this.failOnBadResponse = failOnBadResponse;
		this.pinCode = pinCode;
	}

	public String[] getUrls() {
		return urls;
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

	public Pin getPinCode() {
		return pinCode;
	}
}
