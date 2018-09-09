package com.avionte.status.beepbeep;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avionte.status.beepbeep.core.services.UpdaterService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.IParseOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.IPopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.ParseOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.PopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.IProcessOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessGETOutputConfiguration;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessOutputConfigurationComposite;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessTeamCityOutputConfiguration;
import com.avionte.status.beepbeep.core.services.responseProcessors.IProcessResponse;
import com.avionte.status.beepbeep.core.services.responseProcessors.ResponseProcessorComposite;
import com.avionte.status.beepbeep.core.services.responseProcessors.XMLResponseProcessor;
import com.avionte.status.beepbeep.core.services.responseProcessors.pinOutputProcessors.IPinOutputProcessor;
import com.avionte.status.beepbeep.core.services.responseProcessors.pinOutputProcessors.PinOutputProcessor;

@Configuration
public class CompositionRoot {
	@Bean
	public IParseOutputConfigurationService getParseOutputConfigurationService() {
		return new ParseOutputConfigurationService();
	}
	
	@Bean
	public IPopulateOutputConfigurationService getPopulateOutputConfigurationService(){
		return new PopulateOutputConfigurationService(getParseOutputConfigurationService());
	}
	
	@Bean
	public UpdaterService getUpdatorService() {
		return new UpdaterService(getPopulateOutputConfigurationService(), getProcessOutputConfigurationComposite(), getPinOutputProcessor());
	}
	
	@Bean
	public ProcessTeamCityOutputConfiguration getProcessTeamCityOutputConfiguration() {
		return new ProcessTeamCityOutputConfiguration(getResponseProcessorComposite(), getPinOutputProcessor());
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
	
	public IPinOutputProcessor getPinOutputProcessor() {
		return new PinOutputProcessor();
	}
}