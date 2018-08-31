package com.avionte.status.beepbeep.core.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import com.avionte.status.beepbeep.core.ResponseDataType;
import com.avionte.status.beepbeep.core.Data.OutputConfiguration;
import com.avionte.status.beepbeep.core.Data.OutputConfigurationException;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class PopulateOutputConfigurationService {
	public Collection<OutputConfiguration> PopulateOutputConfiguration(String outputConfigurationFile) throws IOException, OutputConfigurationException {
		Collection<OutputConfiguration> outputConfigurations = new HashSet<OutputConfiguration>();
		BufferedReader reader = null; 
		
		try {
			reader = new BufferedReader(new FileReader(outputConfigurationFile));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				
				if(line.isEmpty() || line.startsWith("#")) {
					//Ignore comment lines
					continue;
				}
				
				OutputConfiguration newConfig = this.CreateOutputConfiguration(line);
				
				if(newConfig != null) {
					outputConfigurations.add(newConfig);	
				}
			}
		}
		finally {
			if(reader != null) {
				reader.close();	
			}
		}
		
		Collection<Pin> uniquePins = new HashSet<Pin>();
		
		for(OutputConfiguration config : outputConfigurations) {
			uniquePins.add(config.getPinCode());
		}
		
		if(uniquePins.size() != outputConfigurations.size()) {
			throw new OutputConfigurationException("Only one pin may be used per configuration - update the configuration file.");
		}
		
		return outputConfigurations;
	}
	
	private OutputConfiguration CreateOutputConfiguration(String line) throws OutputConfigurationException {
		String[] inputs = line.split("|");
		
		// enabled?
		if(inputs[0].equals("false")) {
			return null;
		}
		
		Pin pin = GetRaspiPin(inputs[1]);
		
		if(pin == null) {
			throw new OutputConfigurationException("Pin not found: " + inputs[1]);
		}
		
		String baseUrl = inputs[2];
		
		ResponseDataType responseType = ResponseDataType.valueOf(inputs[3]);
		
		String responseProperty = inputs[4];
		
		String responsePositiveValue = inputs[5];
		
		boolean failOnBadResponse = Boolean.parseBoolean(inputs[6]);
		
		Collection<String> urls = new HashSet<String>();	
		
		for (String insert : inputs[7].split(",")) {
			urls.add(baseUrl.replace("{$}", insert));
		}
		
		return new OutputConfiguration(urls.toArray(new String[urls.size()]), responseType, responseProperty, responsePositiveValue, failOnBadResponse, pin);
	}
	
	private Pin GetRaspiPin(String pinCode) {
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
