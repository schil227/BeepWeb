package com.avionte.status.beepbeep;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avionte.status.beepbeep.core.services.PopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.UpdaterService;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.IProcessOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessGETOutputConfiguration;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessOutputConfigurationComposite;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessTeamCityOutputConfiguration;
import com.avionte.status.beepbeep.core.services.responseProcessors.IProcessResponse;
import com.avionte.status.beepbeep.core.services.responseProcessors.ResponseProcessorComposite;
import com.avionte.status.beepbeep.core.services.responseProcessors.XMLResponseProcessor;

@Configuration
public class CompositionRoot {
	@Bean
	public PopulateOutputConfigurationService getPopulateOutputConfigurationService(){
		return new PopulateOutputConfigurationService();
	}
	
	@Bean
	public UpdaterService getUpdatorService() {
		return new UpdaterService(getPopulateOutputConfigurationService(), getProcessOutputConfigurationComposite());
	}
	
	@Bean
	public ProcessTeamCityOutputConfiguration getProcessTeamCityOutputConfiguration() {
		return new ProcessTeamCityOutputConfiguration(getResponseProcessorComposite());
	}
	
	@Bean
	public ProcessGETOutputConfiguration getProcessGETOutputConfiguration() {
		return new ProcessGETOutputConfiguration(getResponseProcessorComposite());
	}
	
	@Bean
	public IProcessOutputConfigurationService getProcessOutputConfigurationComposite() {
		return new ProcessOutputConfigurationComposite(Arrays.asList(getProcessGETOutputConfiguration(), getProcessTeamCityOutputConfiguration()));
	}
	
	public XMLResponseProcessor getXMLResponseProcessor() {
		return new XMLResponseProcessor();
	}
	
	public IProcessResponse getResponseProcessorComposite() {
		return new ResponseProcessorComposite(Arrays.asList(getXMLResponseProcessor()));
	}
}