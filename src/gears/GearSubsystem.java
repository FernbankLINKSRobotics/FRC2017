package gears;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;

public class GearSubsystem {
	public SpeedController leftMotor = null,
						   rightMotor = null;
	
	public DoubleSolenoid wings = null,
						  claw = null;
	
	public String wingState = "Up";
	
	private boolean intakeButtonBeenPressed = false;
	
	
	

	public GearSubsystem(SpeedController leftRoller, SpeedController rightRoller, DoubleSolenoid wingsCylinder, DoubleSolenoid clawMotor){
		leftMotor = leftRoller;
		rightMotor = rightRoller;
		wings = wingsCylinder;
		claw = clawMotor;
	}
	
	/* Expelling Gears
	 * 
	 * 1. Drop Gear
	 * 2. Activate Rollers to turn in
	 * 3. Pancake Cylinders activate to clamp
	 */
	public void main(){
		intakeGear(CMap.auxStick.getRawButton(3));
		depositGear(CMap.auxStick.getTrigger());
		adjustWings(wingState);
		
	}
	
	public void intakeGear(boolean button){
		if(button){
			if(!intakeButtonBeenPressed){
				if(wingState == "Up"){
					wingState = "Down";
				} else {
					wingState = "Up";
				}
				intakeButtonBeenPressed = true;
			}
			
		} else {
			intakeButtonBeenPressed = false;
		}
		
	}
	
	public void adjustWings(String state){
		if(state == "Up"){
			wings.set(Value.kForward);
		} else {
			wings.set(Value.kForward);
		}
	}
	
	public void lowerRoller(){
		
	}
	
	public void depositGear(boolean button){
		if(button){
			wings.set(Value.kReverse); //Let go of the gear
		}
	}
	
}
