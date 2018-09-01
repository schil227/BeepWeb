package com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors;

import java.util.Collection;
import java.util.Properties;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;

public class ProcessOutputConfigurationComposite implements IProcessOutputConfigurationService {

	private final Collection<IProcessOutputConfigurationService> processOutputConfigurationServices;
	
	public ProcessOutputConfigurationComposite(Collection<IProcessOutputConfigurationService> processOutputConfigurationServices) {
		this.processOutputConfigurationServices = processOutputConfigurationServices;
	}
	
	@Override
	public boolean processOutputConfiguration(OutputConfiguration config, Properties properties) {
		for(IProcessOutputConfigurationService processor : this.processOutputConfigurationServices) {
			if(processor.processOutputConfiguration(config, null)) {
				return true;
			}
		}
		
		return false;
	}

}
