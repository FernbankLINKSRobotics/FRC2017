package gears;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class GearSubsystem {
	
	public DoubleSolenoid intake = null;
	
	public String wingState = "Up";
	
	private boolean intakeButtonBeenPressed = false;
	
	
	

	public GearSubsystem(DoubleSolenoid solenoid){
		intake = solenoid;
	}
	
	/* Expelling Gears
	 * 
	 * 1. Drop Gear
	 * 2. Activate Rollers to turn in
	 * 3. Pancake Cylinders activate to clamp
	 */
	public void main(){
		adjustWings();
		
	}
	
	public void adjustWings(){
		if(CMap.auxStick.getTrigger()){
			intake.set(Value.kForward);
		} else {
			intake.set(Value.kReverse);
		}
	}
	
	
	public void depositGear(boolean button){
		if(button){
			intake.set(Value.kReverse); //Let go of the gear
		}
	}
	
}
