package com.avionte.status.beepbeep.core.services;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StatusRepository implements IStatusRepository {
	private HashMap<String, Boolean> currentStatuses;
	private final Lock currentStatusToken = new ReentrantLock();
	
	public StatusRepository() {
		currentStatuses = new HashMap<String, Boolean>();
	}
	
	public HashMap<String,Boolean> getCurrentStatuses(){
		HashMap<String,Boolean> statusSnapshot = new HashMap<String, Boolean>();
		
		try {
			currentStatusToken.lock();
			statusSnapshot.putAll(currentStatuses);
		} finally {
			currentStatusToken.unlock();
		}
		
		return statusSnapshot;
	}
	
	public void updateCurrentStatus(String configuration, boolean isGood) {
		try {
			currentStatusToken.lock();
			currentStatuses.put(configuration, isGood);
		} finally {
			currentStatusToken.unlock();	
		}
	}
}
