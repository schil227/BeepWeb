package com.avionte.status.beepbeep.core.services.outputConfigurationProcessors;

import java.util.Collection;
import java.util.Properties;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.PinUpdateResultType;

public class ProcessOutputConfigurationComposite implements IProcessOutputConfigurationService {

	private final Collection<IProcessOutputConfigurationService> processOutputConfigurationServices;
	
	public ProcessOutputConfigurationComposite(Collection<IProcessOutputConfigurationService> processOutputConfigurationServices) {
		this.processOutputConfigurationServices = processOutputConfigurationServices;
	}
	
	@Override
	public PinUpdateResultType processOutputConfiguration(OutputConfiguration config, Properties properties) {
		PinUpdateResultType aggregateResult = PinUpdateResultType.NO_CHANGE;
		
		for(IProcessOutputConfigurationService processor : this.processOutputConfigurationServices) {
			PinUpdateResultType processorResult = processor.processOutputConfiguration(config, properties);
			
			if(processorResult != PinUpdateResultType.NO_CHANGE) {
				aggregateResult = processorResult;
				break;
			}
		}
		
		return aggregateResult;
	}

}
