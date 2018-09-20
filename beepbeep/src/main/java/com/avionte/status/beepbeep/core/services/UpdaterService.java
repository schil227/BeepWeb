package com.avionte.status.beepbeep.core.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.OutputConfigurationException;
import com.avionte.status.beepbeep.core.data.model.PinUpdateResultType;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.IPopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.IProcessOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors.IPinOutputProcessor;

public class UpdaterService implements IUpdaterService {
	
	private final IPopulateOutputConfigurationService populateOutputConfigurationService;
	private final IProcessOutputConfigurationService processOutputConfigurationServiceComposite;
	private final IPinOutputProcessor pinOutputProcessor;
	
	public UpdaterService(
			IPopulateOutputConfigurationService populateOutputConfigurationService,
			 IProcessOutputConfigurationService processOutputConfigurationServiceComposite,
			 IPinOutputProcessor pinOutputProcessor) {
		this.populateOutputConfigurationService = populateOutputConfigurationService;
		this.processOutputConfigurationServiceComposite = processOutputConfigurationServiceComposite;
		this.pinOutputProcessor = pinOutputProcessor;
	}
	
	/* (non-Javadoc)
	 * @see com.avionte.status.beepbeep.core.services.IUpdaterService#update()
	 */
	@Override
	public void update() throws IOException, OutputConfigurationException {
		InputStream input = null;
		
		try {
			input = new FileInputStream("config.properties");
			
			Properties props = new Properties();
			props.load(input);
			
			Collection<OutputConfiguration> configs = this.populateOutputConfigurationService.populateOutputConfiguration(props.getProperty("outputConfigurationFile"));
			
			System.out.println("Created " + configs.size() + " configurations.");
			
			PinUpdateResultType aggregatePinUpdateResult = PinUpdateResultType.NO_CHANGE;
			
			for(OutputConfiguration config : configs) {
				PinUpdateResultType configResult = this.processOutputConfigurationServiceComposite.processOutputConfiguration(config, props);
				
				aggregatePinUpdateResult = this.setPinUpdateResult(configResult, aggregatePinUpdateResult);
			}
			
			pinOutputProcessor.processUpdateResult(aggregatePinUpdateResult);
		}
		finally {
			if(input != null) {
				input.close();
			}
		}
	}
	
	private PinUpdateResultType setPinUpdateResult(PinUpdateResultType newResult, PinUpdateResultType existingResult) {
		if(newResult == PinUpdateResultType.POSITIVE_CHANGE && existingResult != PinUpdateResultType.NEGATIVE_CHANGE) {
			return PinUpdateResultType.POSITIVE_CHANGE;
		} else if(newResult == PinUpdateResultType.NEGATIVE_CHANGE) {
			return PinUpdateResultType.NEGATIVE_CHANGE;
		}
		
		return existingResult;
	}
}
