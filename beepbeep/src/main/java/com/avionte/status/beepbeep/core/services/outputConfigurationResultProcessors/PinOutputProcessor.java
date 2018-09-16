package com.avionte.status.beepbeep.core.services.outputConfigurationResultProcessors;

import com.avionte.status.beepbeep.core.data.PinUpdateResultType;
import com.avionte.status.beepbeep.core.services.IStatusRepository;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class PinOutputProcessor implements IPinOutputProcessor {

	private final Pin alarmPin = RaspiPin.GPIO_00;
	
	@Override
	public PinUpdateResultType updatePin(Pin pin, boolean isHighOutput) {
		GpioController gpioController = GpioFactory.getInstance();
		GpioPinDigitalOutput gpioPin = gpioController.provisionDigitalOutputPin(pin);
		
		PinState previousState = gpioPin.getState();
		PinState currentState = isHighOutput ? PinState.HIGH : PinState.LOW;
		
		if(previousState == currentState) {
			// No change.
			gpioController.unprovisionPin(gpioPin);
			return PinUpdateResultType.NO_CHANGE;
		} 
		
		gpioPin.setState(currentState);
		
		gpioController.unprovisionPin(gpioPin);

		return isHighOutput ? PinUpdateResultType.POSITIVE_CHANGE : PinUpdateResultType.NEGATIVE_CHANGE;
	}

	@Override
	public void processUpdateResult(PinUpdateResultType updateResult) {
		switch(updateResult) {
		case POSITIVE_CHANGE:
			try {
				this.handlePositiveChange();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
			
		case NEGATIVE_CHANGE:
			try {
				this.handleNegativeChange();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
			
		default:
			System.out.println("No status change.");
			break;
		}
	}
	
	private void handlePositiveChange() throws InterruptedException {
		System.out.println("Yay! Things got better!");
		
		GpioController gpioController = GpioFactory.getInstance();
		GpioPinDigitalOutput gpioPin = gpioController.provisionDigitalOutputPin(this.alarmPin, PinState.HIGH);
		
		Thread.sleep(250);
		
		gpioPin.setState(PinState.LOW);
		
		Thread.sleep(250);
		
		gpioPin.setState(PinState.HIGH);
		
		Thread.sleep(250);
		
		gpioPin.setState(PinState.LOW);
		
		Thread.sleep(250);
		
		gpioPin.setState(PinState.HIGH);
		
		Thread.sleep(250);
		
		gpioPin.setState(PinState.LOW);
		
		Thread.sleep(250);
		
		gpioController.unprovisionPin(gpioPin);
		
	}
	
	private void handleNegativeChange() throws InterruptedException {
		System.out.println("Boo! Things got worse!");
		
		GpioController gpioController = GpioFactory.getInstance();
		GpioPinDigitalOutput gpioPin = gpioController.provisionDigitalOutputPin(this.alarmPin, PinState.HIGH);
		
		Thread.sleep(2000);
		
		gpioPin.setState(PinState.LOW);
		
		gpioController.unprovisionPin(gpioPin);
	}

}
