package com.avionte.status.beepbeep.core.services.outputConfigurationParsers;

import java.io.IOException;
import java.util.Collection;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.OutputConfigurationException;

public interface IPopulateOutputConfigurationService {

	Collection<OutputConfiguration> populateOutputConfiguration(String outputConfigurationFile)
			throws IOException, OutputConfigurationException;

}