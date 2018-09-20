package com.avionte.status.beepbeep.core.services.responseProcessors;

import java.util.Collection;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;

public class ResponseProcessorComposite implements IProcessResponse {

	private final Collection<IProcessResponse> responseProcessors;
	
	public ResponseProcessorComposite(Collection<IProcessResponse> responseProcessors) {
		this.responseProcessors = responseProcessors;
	}
	
	@Override
	public boolean processResponse(OutputConfiguration config, String response) {
		for(IProcessResponse processor : this.responseProcessors) {
			if(processor.processResponse(config, response)) {
				return true;
			}
		}
		
		return false;
	}

}
