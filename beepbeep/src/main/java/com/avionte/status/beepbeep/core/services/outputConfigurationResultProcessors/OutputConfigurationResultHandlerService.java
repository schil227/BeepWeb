package com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.PinUpdateResultType;
import com.avionte.status.beepbeep.core.services.IStatusRepository;

public class OutputConfigurationResultHandlerService implements IOutputConfigurationResultHandlerService {

	private final IStatusRepository statusRepository;
	private final IPinOutputProcessor pinOutputProcessor;
	
	public OutputConfigurationResultHandlerService(
			IStatusRepository statusRepository,
			IPinOutputProcessor pinOutputProcessor) {
		this.statusRepository = statusRepository;
		this.pinOutputProcessor = pinOutputProcessor;
	}
	
	@Override
	public PinUpdateResultType handle(OutputConfiguration config, boolean result) {
		statusRepository.updateCurrentStatus(config.getName(), result);
		return pinOutputProcessor.updatePin(config.getPin(), result);
	}

}
