package org.fernbanklinks.subsystems;

import org.fernbanklinks.main.CMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class GearManipulator {
	
	public DoubleSolenoid intake = null,
						  mainSolenoid = null;
	public VictorSP motor = null;
	
	
	public String[] states = {"Open", "Closed"};
	
	private boolean intakeButtonBeenPressed = false;
	private boolean toggle = false;
	private String state = "Up";
	
	

	public GearManipulator(VictorSP motorLift, DoubleSolenoid solenoid, DoubleSolenoid liftSolenoid){
		intake = liftSolenoid;
		mainSolenoid = solenoid;
		motor = motorLift;
	}
	//public VictorSP intakeMotor;
	

	public GearManipulator(DoubleSolenoid solenoid){
		intake = solenoid;
		//intakeMotor = motor;

	}
	
	
	
	public void main(boolean buttonForIntake, boolean buttonForMain){
		liftIntake(buttonForIntake);
		if(buttonForIntake){
			motor.set(1);
		} else {
			motor.set(0);
		}
		
		if(buttonForMain){
			if (!intakeButtonBeenPressed) {
				System.out.println("Switch");
				if(toggle == true){
					toggle = false;
				} else {
					toggle = true;
				}
			}
			
			intakeButtonBeenPressed = true;
		} else {
			intakeButtonBeenPressed = false;
		}
		
		
		System.out.println(toggle);

		if(toggle){
			mainSolenoid.set(Value.kForward);
		} else {
			mainSolenoid.set(Value.kReverse);
		}
		
	}
	
	public void spinRoller(boolean button1, boolean button2){
		if(button1){
			motor.set(1);
		} else if(button2){
			motor.set(-1);
		} else {
			motor.set(0);
		}
	}
	
	public void liftIntake(boolean button){
		if(button){
			state = "Down";
		} else {
			state = "Up";
		}
		
		if(state == "Up"){
			intake.set(Value.kReverse);
			//intakeMotor.set(.5);
		} else {
			intake.set(Value.kForward);
			//intakeMotor.set(0);
		}
	}
	
	
	
}