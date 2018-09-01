package com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors;

import java.util.Properties;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;

public interface IProcessOutputConfigurationService {
	boolean processOutputConfiguration(OutputConfiguration config, Properties properties);
}
