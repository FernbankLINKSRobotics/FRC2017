package org.fernbanklinks.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

import org.fernbanklinks.main.CMap;

import drive.*;

public class GearShift {
	public static DoubleSolenoid shifter;
	public static boolean shiftButton;
	
	public static String state;
	private static boolean buttonBeenPressed = false;
	
	
	public GearShift(DoubleSolenoid gearShift){
		shifter = gearShift;
		
		state = "Low";
	}
	
	public void main(boolean button){
		shift(button);
		manageSolenoid();
	}
	
	public static void shift(boolean button){
		shiftButton = button;
		if(shiftButton){
			if(!buttonBeenPressed){
				if(state == "Low"){
					state = "High";
				
				} else {
					state = "Low";
				}
				buttonBeenPressed = true;
				System.out.println("Gears shifted to " + state + ".");
			}
		} else {
			buttonBeenPressed = false;
		}
		
	}
	
	//This will be used if we need to shift states in 
	//autonomous. Just type in the state
	public void autoShift(String state){
		manageSolenoid();
	}
	
	public static void manageSolenoid(){
		if(state == "High"){
			shifter.set(DoubleSolenoid.Value.kForward);
		} else {
			shifter.set(DoubleSolenoid.Value.kReverse);
		}
	}
}
