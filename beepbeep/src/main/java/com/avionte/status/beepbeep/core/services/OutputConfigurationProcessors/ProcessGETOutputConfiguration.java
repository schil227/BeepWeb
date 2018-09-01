package com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors;

import java.util.Properties;

import com.avionte.status.beepbeep.core.RequestType;
import com.avionte.status.beepbeep.core.data.OutputConfiguration;

public class ProcessGETOutputConfiguration implements IProcessOutputConfigurationService {

	@Override
	public boolean processOutputConfiguration(OutputConfiguration config, Properties properties) {
		if(config.getRequestType() != RequestType.GET) {
			return false;
		}
		
		return false;
	}

}
