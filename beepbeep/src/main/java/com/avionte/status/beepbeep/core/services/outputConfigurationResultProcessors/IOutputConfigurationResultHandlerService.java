package com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.PinUpdateResultType;

public interface IOutputConfigurationResultHandlerService {
	PinUpdateResultType handle(OutputConfiguration config, boolean result);
}
