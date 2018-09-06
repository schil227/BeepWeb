package com.avionte.status.beepbeep.core.services.responseProcessors.pinOutputProcessors;

import com.avionte.status.beepbeep.core.data.PinUpdateResultType;
import com.pi4j.io.gpio.Pin;

public interface IPinOutputProcessor {
	PinUpdateResultType updatePin(Pin pin, boolean isHighOutput);
	
	void processUpdateResult(PinUpdateResultType updateResult);
}
