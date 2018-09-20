package com.avionte.status.beepbeep.core.services.outputConfigurationParsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.OutputConfigurationException;
import com.avionte.status.beepbeep.core.data.model.RequestType;
import com.avionte.status.beepbeep.core.data.model.ResponseDataType;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class PopulateOutputConfigurationService implements IPopulateOutputConfigurationService {
	
	private final IParseOutputConfigurationService parseOutputConfigurationService;
	
	public PopulateOutputConfigurationService(IParseOutputConfigurationService parseOutputConfigurationService) {
		this.parseOutputConfigurationService = parseOutputConfigurationService;
	}
	
	public Collection<OutputConfiguration> populateOutputConfiguration(String outputConfigurationFile) throws IOException, OutputConfigurationException {
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
				
				OutputConfiguration newConfig = this.parseOutputConfigurationService.parse(line);
				
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
			uniquePins.add(config.getPin());
		}
		
		if(uniquePins.size() != outputConfigurations.size()) {
			throw new OutputConfigurationException("Only one pin may be used per configuration - update the configuration file.");
		}
		
		return outputConfigurations;
	}
}
