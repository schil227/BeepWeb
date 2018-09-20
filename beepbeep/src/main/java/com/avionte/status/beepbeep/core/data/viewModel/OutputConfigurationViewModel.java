package com.avionte.status.beepbeep.core.data.viewModel;

import com.fasterxml.jackson.annotation.JsonGetter;

public class OutputConfigurationViewModel {
	private String configName;
	private boolean isWorking;
	
	public OutputConfigurationViewModel(String configName, boolean isWorking){
		this.configName = configName;
		this.isWorking = isWorking;
	}
	
	@JsonGetter("configName")
	public String getConfigName() {
		return configName;
	}
	
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	
	@JsonGetter("isWorking")
	public boolean getIsWorking() {
		return isWorking;
	}
	
	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
}
