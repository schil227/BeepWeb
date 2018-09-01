package com.avionte.status.beepbeep.core.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.OutputConfigurationException;

public class UpdaterService {
	private final PopulateOutputConfigurationService populateOutputConfigurationService;
	
	public UpdaterService(PopulateOutputConfigurationService populateOutputConfigurationService) {
		this.populateOutputConfigurationService = populateOutputConfigurationService;
	}
	
	public void update() throws IOException, OutputConfigurationException {
		InputStream input = null;
		
		try {
			input = new FileInputStream("config.properties");
			
			Properties props = new Properties();
			props.load(input);
			
			Collection<OutputConfiguration> configs = this.populateOutputConfigurationService.populateOutputConfiguration(props.getProperty("outputConfigurationFile"));
			
			System.out.println("Created " + configs.size() + " configurations.");
		}
		finally {
			if(input != null) {
				input.close();
			}
		}
		
	}
}
