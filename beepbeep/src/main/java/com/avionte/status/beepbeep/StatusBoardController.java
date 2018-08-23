package com.avionte.status.beepbeep;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class StatusBoardController {
	private static GpioPinDigitalOutput pin;
	
	public StatusBoardController() {
		GpioController gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Pin 1", PinState.LOW);
	}
	
	@RequestMapping("status/")
	public String index() {
		return "You've reached the status controller\n";
	}
	
	@RequestMapping("status/toggle")
	public String toggle() {
		String message = "";
		
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
		
		return message;
	}
	
	@RequestMapping("status/blink")
	public String blink() throws InterruptedException {
		String message = "";
		
		pin.high();
		
		Thread.sleep(5000);
		
		pin.low();
		
		Thread.sleep(5000);
		
		pin.high();
		
		Thread.sleep(5000);
		
		pin.low();
		
		return "Blink!\n";
//		gpio.unprovisionPin(pin);
	}
}
