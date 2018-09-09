package com.avionte.status.beepbeep.core.services.outputConfigurationParsers;

import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.OutputConfigurationException;

public interface IParseOutputConfigurationService {
	OutputConfiguration parse(String line) throws OutputConfigurationException;
}
