package com.avionte.status.beepbeep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avionte.status.beepbeep.core.services.PopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.UpdaterService;
import com.avionte.status.beepbeep.host.StatusBoardController;

@Configuration
public class CompositionRoot {
	@Bean
	public PopulateOutputConfigurationService getPopulateOutputConfigurationService(){
		return new PopulateOutputConfigurationService();
	}
	
	@Bean
	public UpdaterService getUpdatorService() {
		return new UpdaterService(getPopulateOutputConfigurationService());
	}
}