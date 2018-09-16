package com.avionte.status.beepbeep.core.services;

import java.io.IOException;

import com.avionte.status.beepbeep.core.data.OutputConfigurationException;

public interface IUpdaterService {

	void update() throws IOException, OutputConfigurationException;

}