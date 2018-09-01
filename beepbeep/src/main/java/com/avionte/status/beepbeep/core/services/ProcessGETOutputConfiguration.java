package com.avionte.status.beepbeep.core.services;

import com.avionte.status.beepbeep.core.RequestType;
import com.avionte.status.beepbeep.core.data.OutputConfiguration;

public class ProcessGETOutputConfiguration implements IProcessOutputConfigurationService {

	@Override
	public boolean ProcessOutputConfiguration(OutputConfiguration config) {
		if(config.getRequestType() != RequestType.GET) {
			return false;
		}
		
		return false;
	}

}
