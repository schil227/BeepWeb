package com.avionte.status.beepbeep.core.services.responseProcessors;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;

public interface IProcessResponse {
	boolean processResponse(OutputConfiguration config, String response);
}
