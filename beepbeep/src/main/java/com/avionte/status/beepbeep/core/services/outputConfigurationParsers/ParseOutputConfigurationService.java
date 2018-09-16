package com.avionte.status.beepbeep.core.services.outputConfigurationParsers;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.OutputConfigurationException;
import com.avionte.status.beepbeep.core.data.RequestType;
import com.avionte.status.beepbeep.core.data.ResponseDataType;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class ParseOutputConfigurationService implements IParseOutputConfigurationService {

	@Override
	public OutputConfiguration parse(String line) throws OutputConfigurationException {
		String[] inputs = line.split("\\|");

		if(inputs.length < 11 || inputs.length > 12 || inputs[0].equals("false")) {
			return null;
		}
		
		Pin pin = getPin(inputs[1]);
		
		if(pin == null) {
			throw new OutputConfigurationException("Pin not found: " + inputs[1]);
		}
		
		String name = inputs[2];
		
		String baseUrl = inputs[3];
		
		RequestType requestType = RequestType.valueOf(inputs[4]);
		
		ResponseDataType responseType = ResponseDataType.valueOf(inputs[5]);
		
		String responseProperty = inputs[6];
		
		String responsePositiveValue = inputs[7];
		
		boolean failOnBadResponse = Boolean.parseBoolean(inputs[8]);
		
		String usernamePropertyKey = inputs[9];
		
		String passwordPropertyKey = inputs[10];
		
		Collection<String> urls = new HashSet<String>();	
		
		String inserts = inputs[11];
		
		if(inserts != null) {
			for (String insert : inputs[11].split(",")) {
				urls.add(baseUrl.replace("{$}", insert));
			}	
		}else {
			urls.add(baseUrl);
		}
		
		return new OutputConfiguration(name,
				urls.toArray(new String[urls.size()]), 
				requestType, 
				responseType, 
				responseProperty, 
				responsePositiveValue, 
				failOnBadResponse, 
				pin, 
				usernamePropertyKey, 
				passwordPropertyKey);
	}
	
	private Pin getPin(String pinCode) {
		switch(pinCode) {
		case "1":
			return RaspiPin.GPIO_01;
		case "2":
			return RaspiPin.GPIO_02;
		case "3":
			return RaspiPin.GPIO_03;
		case "4":
			return RaspiPin.GPIO_04;
		case "5":
			return RaspiPin.GPIO_05;
		case "6":
			return RaspiPin.GPIO_06;
		case "7":
			return RaspiPin.GPIO_07;
		case "8":
			return RaspiPin.GPIO_08;
		case "9":
			return RaspiPin.GPIO_09;
		case "10":
			return RaspiPin.GPIO_10;
		case "11":
			return RaspiPin.GPIO_11;
		case "12":
			return RaspiPin.GPIO_12;
		case "13":
			return RaspiPin.GPIO_13;
		case "14":
			return RaspiPin.GPIO_14;
		case "15":
			return RaspiPin.GPIO_15;
		case "16":
			return RaspiPin.GPIO_16;
		default:
			return null;
		}
	}
}
