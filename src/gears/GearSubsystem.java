package gears;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;

public class GearSubsystem {
	public SpeedController leftMotor = null,
						   rightMotor = null;
	
	public DoubleSolenoid cylinders = null;
	
	private boolean dropButtonBeenPressed = false,
					intakeButtonBeenPressed = false;
	

	public GearSubsystem(SpeedController leftRoller, SpeedController rightRoller, DoubleSolenoid cylinder){
		leftMotor = leftRoller;
		rightMotor = rightRoller;
		cylinders = cylinder;
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
	}
	
	public void intakeGear(boolean button){
		if(button){
			if(!intakeButtonBeenPressed){
				if(leftMotor.get() != 0){
					leftMotor.set(1); //Set the Rollers to INtake the Gear
					rightMotor.set(-1);
					System.out.println("The rollers have been activated.");
				} else {
					leftMotor.set(0); //Set the Rollers to do nothing
					rightMotor.set(0);
				}
			}
			intakeButtonBeenPressed = true;
			cylinders.set(Value.kForward); //Clamp on the Gear
			System.out.println("The gear has been clamped.");
		} else {
			intakeButtonBeenPressed = false;
		}
	}
	
	public void depositGear(boolean button){
		if(button){
			cylinders.set(Value.kReverse); //Let go of the gear
			System.out.println("The gears have been let go.");
		}
	}
	
}
