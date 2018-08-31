package com.avionte.status.beepbeep.core.services;

import java.io.IOException;

import com.avionte.status.beepbeep.core.Data.OutputConfigurationException;

public class UpdaterService {
	private final PopulateOutputConfigurationService populateOutputConfigurationService;
	
	public UpdaterService(PopulateOutputConfigurationService populateOutputConfigurationService) {
		this.populateOutputConfigurationService = populateOutputConfigurationService;
	}
	
	public void Update() throws IOException, OutputConfigurationException {
		this.populateOutputConfigurationService.PopulateOutputConfiguration("");
	}
}
