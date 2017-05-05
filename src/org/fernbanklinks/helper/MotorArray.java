package org.fernbanklinks.helper;

import edu.wpi.first.wpilibj.*;

public class MotorArray implements SpeedController, PIDOutput {
	//Holders for Motors
	Object[] motors;
	
	public MotorArray(Object[] motorsArray) {
		//Is it long enough?
		if(motors.length < 1){
			DriverStation.getInstance();
			DriverStation.reportError("Motors Array must contain at least one object.", false);
			return;
		}
		
		
		//Is each object in the array an actual motor?
		for(Object motor : motors){
			if(!(motor instanceof VictorSP) || !(motor instanceof Victor) ||
					!(motor instanceof Talon) || !(motor instanceof TalonSRX)){
				DriverStation.getInstance();
				DriverStation.reportError("Motors Array must only contain Motors", false);
			}	
			
		}
		
		motors = motorsArray;
	}
	
	@Override
	public void pidWrite(double output) {
		this.set(output);
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return ((PWMSpeedController) motors[0]).get();
	}

	@Override
	public void set(double speed) {
		for(Object motor : motors){
			((PWMSpeedController) motor).set(speed); 
		}

	}

	@Override
	public void setInverted(boolean isInverted) {
		for(Object motor : motors){
			((PWMSpeedController) motor).setInverted(isInverted); 
		}

	}

	@Override
	public boolean getInverted() {
		return ((PWMSpeedController) motors[0]).getInverted();
	}

	@Override
	public void disable() {
		for(Object motor : motors){
			((PWMSpeedController) motor).disable();
		}

	}

	@Override
	public void stopMotor() {
		for(Object motor : motors){
			((PWMSpeedController) motor).stopMotor();
		}

	}

}
