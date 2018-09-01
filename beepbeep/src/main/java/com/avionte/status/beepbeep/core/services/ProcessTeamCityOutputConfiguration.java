package com.avionte.status.beepbeep.core.services;

import com.avionte.status.beepbeep.core.RequestType;
import com.avionte.status.beepbeep.core.data.OutputConfiguration;

public class ProcessTeamCityOutputConfiguration implements IProcessOutputConfigurationService {

	@Override
	public boolean ProcessOutputConfiguration(OutputConfiguration config) {
		if(config.getRequestType() != RequestType.TEAMCITY) {
			return false;
		}
		
		return false;
	}

}
