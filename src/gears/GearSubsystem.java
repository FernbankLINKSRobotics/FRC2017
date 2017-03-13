package gears;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class GearSubsystem {
	
	public DoubleSolenoid intake = null;
	public VictorSP motor = null;
	
	
	
	private boolean intakeButtonBeenPressed = false;
	private String state = "Up";
	
	

	public GearSubsystem(VictorSP motorLift, DoubleSolenoid solenoid){
		intake = solenoid;
		motor = motorLift;
	}
	
	/* Expelling Gears
	 * 
	 * 1. Drop Gear
	 * 2. Activate Rollers to turn in
	 * 3. Pancake Cylinders activate to clamp
	 */
	public void main(boolean buttonForLift, boolean buttonForRoller){
		liftIntake(buttonForLift);
		spinRoller(buttonForRoller);
		
	}
	
	public void spinRoller(boolean button){
		if(button){
			motor.set(1);
		} else {
			motor.set(0);
		}
	}
	
	public void liftIntake(boolean button){
		if(button){
			if(!intakeButtonBeenPressed){
				if(state == "Up"){
					state = "Down";
				} else {
					state = "Up";
				}
			}
			
			intakeButtonBeenPressed = true;
		} else {
			intakeButtonBeenPressed = false;
		}
		
		if(state == "Up"){
			intake.set(Value.kReverse);
		} else {
			intake.set(Value.kForward);
		}
	}
	
	
	
}
