package com.avionte.status.beepbeep.host;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avionte.status.beepbeep.core.data.viewModel.OutputConfigurationViewModel;
import com.avionte.status.beepbeep.core.services.IStatusService;
import com.avionte.status.beepbeep.core.services.IUpdaterService;
//import com.pi4j.io.gpio.GpioController;
//import com.pi4j.io.gpio.GpioFactory;
//import com.pi4j.io.gpio.GpioPinDigitalOutput;
//import com.pi4j.io.gpio.PinState;
//import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class StatusBoardController {
	
	private IUpdaterService updaterService;
	private IStatusService statusService;
	
	public StatusBoardController(
	
			IUpdaterService updaterService,
			IStatusService statusService
			) {
		this.updaterService = updaterService;
		this.statusService = statusService;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		// The name of the view to render
		return "index"; 
	}
	
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	@RequestMapping(value = "status/", method = {RequestMethod.GET})
	public List<OutputConfigurationViewModel> status() {
		List<OutputConfigurationViewModel> statuses = statusService.getStatus();
		
		statuses.add(new OutputConfigurationViewModel("Dummy", true));
		
		return statuses;
	}
	
	@RequestMapping("status/update")
	public String update() {
		System.out.println("Update Endpoint Triggered.");
		
		try {
			this.updaterService.update();
		} catch(Exception ex) {
			return "Failed to call update. Exception: " + ex.getMessage();
		}
		
		return "Update Succeeded!";
	}
	
	@Scheduled(fixedRate=60000)
	public void updateAgent() throws InterruptedException {
		System.out.println("Calling the scheduled task!");
		this.update();
	}
	
	@RequestMapping("status/toggle")
	public String toggle() throws InterruptedException {
		String message = "";
		
		GpioController gpioController = GpioFactory.getInstance();
		GpioPinDigitalOutput pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_01);
		
		PinState state = pin.getState();
		
		System.out.println("Pin State: " + state +" - isHigh: " + state.isHigh() + " - isLow: " + state.isLow());
		
		if(state.isHigh()) {
			message = "Setting PIN 1 output to low.\n";
			System.out.println(message);
			
			pin.low();
		} else {
			message = "Setting PIN 1 output to high.\n";
			System.out.println(message);
			
			pin.high();
		}
		
		gpioController.unprovisionPin(pin);
		
		return message;
	}
}
