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
import java.util.Base64;
import java.util.Properties;

import com.avionte.status.beepbeep.core.RequestType;
import com.avionte.status.beepbeep.core.data.OutputConfiguration;
import com.avionte.status.beepbeep.core.services.responseProcessors.IProcessResponse;

public class ProcessTeamCityOutputConfiguration implements IProcessOutputConfigurationService {

	private final IProcessResponse responseProcessorComposite; 
	
	public ProcessTeamCityOutputConfiguration(IProcessResponse responseProcessorComposite) {
		this.responseProcessorComposite = responseProcessorComposite;
	}
	
	@Override
	public boolean processOutputConfiguration(OutputConfiguration config, Properties properties) {
		if(config.getRequestType() != RequestType.TEAMCITY) {
			return false;
		}
		
		for(String url : config.getUrls()) {
			BufferedReader reader = null;
			InputStream inputStream = null;
			URLConnection connection = null;
			
			try {
				Authenticator.setDefault (new Authenticator() {
				    protected PasswordAuthentication getPasswordAuthentication() {
				        return new PasswordAuthentication (properties.getProperty(config.getUsernamePropertyKey()), properties.getProperty(config.getPasswordPropertyKey()).toCharArray());
				    }
				});
				
				URL requestUrl = new URL(url);
				
				connection = (URLConnection)requestUrl.openConnection();
				
				connection.setConnectTimeout(30000);
				connection.setReadTimeout(30000);
				
				connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
				InputStream in = connection.getInputStream();
				
				reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				
				int contentLength = connection.getContentLength();
				
				char[] response = new char[contentLength];
				
				reader.read(response);
				
				System.out.println("Response Content: " + String.valueOf(response));
				
				if(!responseProcessorComposite.processResponse(config, String.valueOf(response))) {
					System.out.println("Got a failure: " + url);
					return false;
				}
				
				System.out.println("Success for " + url);
			}
			catch (MalformedURLException ex) {
				ex.printStackTrace();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			finally{
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				
//				if(connection != null) {
//					connection.disconnect();
//				}
			}
		}
		
		return true;
	}
}
