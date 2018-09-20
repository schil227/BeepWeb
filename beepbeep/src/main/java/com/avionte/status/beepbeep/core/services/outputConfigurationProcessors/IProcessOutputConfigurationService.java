package com.avionte.status.beepbeep.core.services.outputConfigurationProcessors;

import java.util.Properties;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.PinUpdateResultType;

public interface IProcessOutputConfigurationService {
	PinUpdateResultType processOutputConfiguration(OutputConfiguration config, Properties properties);
}
