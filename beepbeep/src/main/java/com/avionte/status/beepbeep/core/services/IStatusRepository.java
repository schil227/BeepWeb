package com.avionte.status.beepbeep.core.services;

import java.util.HashMap;

public interface IStatusRepository {
	HashMap<String,Boolean> getCurrentStatuses();
	
	void updateCurrentStatus(String configuration, boolean isGood);
}