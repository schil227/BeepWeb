package com.avionte.status.beepbeep.core.services.outputConfigurationProcessors;

import java.util.Properties;

import com.avionte.status.beepbeep.core.RequestType;
import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.services.responseProcessors.IProcessResponse;

public class ProcessGETOutputConfiguration implements IProcessOutputConfigurationService {

	private final IProcessResponse responseProcessorComposite; 
	
	public ProcessGETOutputConfiguration(IProcessResponse responseProcessorComposite) {
		this.responseProcessorComposite = responseProcessorComposite;
	}
	
	@Override
	public boolean processOutputConfiguration(OutputConfiguration config, Properties properties) {
		if(config.getRequestType() != RequestType.GET) {
			return false;
		}
		
		return false;
	}

}
