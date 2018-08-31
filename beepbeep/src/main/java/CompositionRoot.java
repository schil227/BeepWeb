import org.springframework.context.annotation.Configuration;

import com.avionte.status.beepbeep.core.services.PopulateOutputConfigurationService;
import com.avionte.status.beepbeep.core.services.UpdaterService;

@Configuration
public class CompositionRoot {
	public PopulateOutputConfigurationService getPopulateOutputConfigurationService(){
		return new PopulateOutputConfigurationService();
	}

	public UpdaterService getUpdatorService() {
		return new UpdaterService(getPopulateOutputConfigurationService());
	}
}