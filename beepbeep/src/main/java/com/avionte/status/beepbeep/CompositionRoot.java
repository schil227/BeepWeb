package com.avionte.status.beepbeep;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avionte.status.beepbeep.core.services.IStatusRepository;
import com.avionte.status.beepbeep.core.services.IStatusService;
import com.avionte.status.beepbeep.core.services.IUpdaterService;
import com.avionte.status.beepbeep.core.services.StatusRepository;
import com.avionte.status.beepbeep.core.services.StatusService;
import com.avionte.status.beepbeep.core.services.UpdaterService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.IParseOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.IPopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.ParseOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationParsers.PopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.IProcessOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessGETOutputConfiguration;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessOutputConfigurationComposite;
import com.avionte.status.beepbeep.core.services.outputConfigurationProcessors.ProcessTeamCityOutputConfiguration;
import com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors.IOutputConfigurationResultHandlerService;
import com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors.IPinOutputProcessor;
import com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors.OutputConfigurationResultHandlerService;
import com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors.PinOutputProcessor;
import com.avionte.status.beepbeep.core.services.responseProcessors.IProcessResponse;
import com.avionte.status.beepbeep.core.services.responseProcessors.ResponseProcessorComposite;
import com.avionte.status.beepbeep.core.services.responseProcessors.XMLResponseProcessor;

@Configuration
public class CompositionRoot {
	@Bean 
	IOutputConfigurationResultHandlerService getOutputConfigurationResultHandlerService() {
		return new OutputConfigurationResultHandlerService(getStatusRepository(), getPinOutputProcessor());
	}
	
	@Bean 
	IStatusRepository getStatusRepository() {
		return new StatusRepository();
	}
	
	@Bean 
	IStatusService getStatusService() {
		return new StatusService(getStatusRepository());
	}
	
	@Bean
	public IParseOutputConfigurationService getParseOutputConfigurationService() {
		return new ParseOutputConfigurationService();
	}
	
	@Bean
	public IPopulateOutputConfigurationService getPopulateOutputConfigurationService(){
		return new PopulateOutputConfigurationService(getParseOutputConfigurationService());
	}
	
	@Bean
	public IUpdaterService getUpdatorService() {
		return new UpdaterService(getPopulateOutputConfigurationService(), getProcessOutputConfigurationComposite(), getPinOutputProcessor());
	}
	
	public ProcessTeamCityOutputConfiguration getProcessTeamCityOutputConfiguration() {
		return new ProcessTeamCityOutputConfiguration(getResponseProcessorComposite(), getOutputConfigurationResultHandlerService());
	}
	
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
	
	@Bean
	public IProcessResponse getResponseProcessorComposite() {
		return new ResponseProcessorComposite(Arrays.asList(getXMLResponseProcessor()));
	}
	
	@Bean
	public IPinOutputProcessor getPinOutputProcessor() {
		return new PinOutputProcessor();
	}
}