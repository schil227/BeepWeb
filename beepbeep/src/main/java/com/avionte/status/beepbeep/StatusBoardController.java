package com.avionte.status.beepbeep;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class StatusBoardController {
	@RequestMapping("status/")
	public String index() {
		return "You've reached the status controller";
	}
	
	@RequestMapping("status/toggle")
	public void toggle() {
		System.out.println("toggling...");
		
		GpioController gpio = GpioFactory.getInstance();
		
		GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
		
		if(pin.isHigh()) {
			pin.low();
		} else {
			pin.high();
		}
		
		gpio.unprovisionPin(pin);
	}
}
