package com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.PinUpdateResultType;

public interface IOutputConfigurationResultHandlerService {
	PinUpdateResultType handle(OutputConfiguration config, boolean result);
}
