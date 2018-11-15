package com.avionte.status.beepbeep.core.services.outputConfigurationProcessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.avionte.status.beepbeep.core.data.model.OutputConfiguration;
import com.avionte.status.beepbeep.core.data.model.PinUpdateResultType;
import com.avionte.status.beepbeep.core.data.model.RequestType;
import com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors.IOutputConfigurationResultHandlerService;
import com.avionte.status.beepbeep.core.services.responseProcessors.IProcessResponse;

public class ProcessHealthCheckOutputConfiguration implements IProcessOutputConfigurationService {

	private final IOutputConfigurationResultHandlerService outputConfigurationResultHandlerService;
	
	public ProcessHealthCheckOutputConfiguration(IOutputConfigurationResultHandlerService outputConfigurationResultHandlerService) {
		this.outputConfigurationResultHandlerService = outputConfigurationResultHandlerService;
	}
	
	@Override
	public PinUpdateResultType processOutputConfiguration(OutputConfiguration config, Properties properties) {
		if(config.getRequestType() != RequestType.HEALTH_CHECK) {
			return PinUpdateResultType.NO_CHANGE;
		}
		
		boolean isSuccessful = true;
		
		for(String url : config.getUrls()) {
			HttpURLConnection connection = null;
			
			try {
				String passwordPropertyKey = config.getPasswordPropertyKey();
				String usernamePropertyKey = config.getUsernamePropertyKey();
				
				if("none".equalsIgnoreCase(passwordPropertyKey) && "none".equalsIgnoreCase(usernamePropertyKey)) {
					Authenticator.setDefault (new Authenticator() {
					    protected PasswordAuthentication getPasswordAuthentication() {
					        return new PasswordAuthentication (properties.getProperty(config.getUsernamePropertyKey()), properties.getProperty(config.getPasswordPropertyKey()).toCharArray());
					    }
					});	
				}
				
				URL requestUrl = new URL(url);
				
				connection = (HttpURLConnection)requestUrl.openConnection();
				
				if(!(String.valueOf(connection.getResponseCode()).charAt(0) == '2')) {
					System.out.println("Got a failure: " + url);
					return this.outputConfigurationResultHandlerService.handle(config, false);
				}
				
				System.out.println("Success for " + url);
			}
			catch(IOException ex) {
				ex.printStackTrace();
				isSuccessful = false;
				break;
			}
			catch(Exception ex) {
				ex.printStackTrace();
				isSuccessful = false;
				break;
			}
		}
		
		return this.outputConfigurationResultHandlerService.handle(config, isSuccessful);
	}

}
