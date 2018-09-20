package com.avionte.status.beepbeep.core.services.outputConfigurationParsers;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.OutputConfigurationException;

public interface IParseOutputConfigurationService {
	OutputConfiguration parse(String line) throws OutputConfigurationException;
}
