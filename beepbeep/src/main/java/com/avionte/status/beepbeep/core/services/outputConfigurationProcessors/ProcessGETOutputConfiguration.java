package com.avionte.status.beepbeep.core.services.outputConfigurationProcessors;

import java.util.Properties;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.PinUpdateResultType;
import com.avionte.status.beepbeep.core.data.RequestType;
import com.avionte.status.beepbeep.core.services.responseProcessors.IProcessResponse;

public class ProcessGETOutputConfiguration implements IProcessOutputConfigurationService {

	private final IProcessResponse responseProcessorComposite; 
	
	public ProcessGETOutputConfiguration(IProcessResponse responseProcessorComposite) {
		this.responseProcessorComposite = responseProcessorComposite;
	}
	
	@Override
	public PinUpdateResultType processOutputConfiguration(OutputConfiguration config, Properties properties) {
		if(config.getRequestType() != RequestType.GET) {
			return PinUpdateResultType.NO_CHANGE;
		}
		
		return PinUpdateResultType.NO_CHANGE;
	}

}
