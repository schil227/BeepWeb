package com.avionte.status.beepbeep;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avionte.status.beepbeep.core.services.PopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.UpdaterService;
import com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors.IProcessOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors.ProcessGETOutputConfiguration;
import com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors.ProcessOutputConfigurationComposite;
import com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors.ProcessTeamCityOutputConfiguration;

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
		return new ProcessTeamCityOutputConfiguration();
	}
	
	@Bean
	public ProcessGETOutputConfiguration getProcessGETOutputConfiguration() {
		return new ProcessGETOutputConfiguration();
	}
	
	@Bean
	public IProcessOutputConfigurationService getProcessOutputConfigurationComposite() {
		return new ProcessOutputConfigurationComposite(Arrays.asList(getProcessGETOutputConfiguration(), getProcessTeamCityOutputConfiguration()));
	}
}