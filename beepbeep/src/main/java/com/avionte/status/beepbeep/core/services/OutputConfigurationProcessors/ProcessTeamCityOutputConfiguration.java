package com.avionte.status.beepbeep.core.services.OutputConfigurationProcessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import com.avionte.status.beepbeep.core.RequestType;
import com.avionte.status.beepbeep.core.data.OutputConfiguration;

public class ProcessTeamCityOutputConfiguration implements IProcessOutputConfigurationService {

	@Override
	public boolean processOutputConfiguration(OutputConfiguration config, Properties properties) {
		if(config.getRequestType() != RequestType.TEAMCITY) {
			return false;
		}
		
		for(String url : config.getUrls()) {
			BufferedReader reader = null;
			InputStream inputStream = null;
			try {
				URL requestUrl = new URL(url);
				
				HttpsURLConnection connection = (HttpsURLConnection) requestUrl.openConnection();
				
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("GET");

				String userpass = properties.getProperty(config.getUsernamePropertyKey()) + ":" + properties.getProperty(config.getPasswordPropertyKey());
				String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
				connection.setRequestProperty ("Authorization", basicAuth);
				inputStream = connection.getInputStream();
				
				connection.connect();
				
				if(connection.getResponseCode() != 200) {
					if(config.isFailOnBadResponse()) {
						//handle pin change here
						return false;
					} else {
						continue;
					}
				}
				
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				int contentLength = connection.getContentLength();
				
				char[] response = new char[contentLength];
				
				reader.read(response);
				
				System.out.println("Response Content: " + String.valueOf(response));
			}
			catch (MalformedURLException ex) {
				ex.printStackTrace();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
			catch(Exception ex) {
				
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
			}
		}
		
		return false;
	}
}
