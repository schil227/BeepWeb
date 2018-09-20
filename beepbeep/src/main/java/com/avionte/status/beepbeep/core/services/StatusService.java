package com.avionte.status.beepbeep.core.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.avionte.status.beepbeep.core.data.viewModel.OutputConfigurationViewModel;

public class StatusService implements IStatusService {

	private final IStatusRepository statusRepository;
	
	public StatusService(IStatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}
	
	@Override
	public List<OutputConfigurationViewModel> getStatus() {
		HashMap<String, Boolean> currentStatus = statusRepository.getCurrentStatuses();
		
		List<OutputConfigurationViewModel> configurations = new ArrayList<OutputConfigurationViewModel>();
		
		for(String key : currentStatus.keySet()){
			configurations.add(new OutputConfigurationViewModel(key, currentStatus.get(key)));
		}
		
		return configurations;
	}

}
