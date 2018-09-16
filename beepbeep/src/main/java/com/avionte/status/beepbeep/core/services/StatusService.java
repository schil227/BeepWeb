package com.avionte.status.beepbeep.core.services;

import java.util.HashMap;

public class StatusService implements IStatusService {

	private final IStatusRepository statusRepository;
	
	public StatusService(IStatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}
	
	@Override
	public String getStatus() {
		HashMap<String, Boolean> currentStatus = statusRepository.getCurrentStatuses();
		
		StringBuilder sb = new StringBuilder();
		
		for(String key : currentStatus.keySet()){
			String status = currentStatus.get(key) ? "GREEN" : "RED";
			
			sb.append(key + " : " + status + "\r\n");
		}
		
		return sb.toString();
	}

}
