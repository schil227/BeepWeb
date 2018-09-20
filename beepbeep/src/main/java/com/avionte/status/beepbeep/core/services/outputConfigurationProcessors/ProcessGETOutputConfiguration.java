package com.avionte.status.beepbeep.core.services.outputConfigurationProcessors;

import java.util.Properties;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.PinUpdateResultType;
import com.avionte.status.beepbeep.core.data.model.RequestType;
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
