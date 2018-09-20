package com.avionte.status.beepbeep.core.services;

import java.util.List;

import com.avionte.status.beepbeep.core.data.viewModel.OutputConfigurationViewModel;

public interface IStatusService {
	List<OutputConfigurationViewModel> getStatus();
}
