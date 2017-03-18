package gears;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class GearSubsystem {
	
	public DoubleSolenoid intake = null,
						  mainSolenoid = null;
	public VictorSP motor = null;
	
	
	
	private boolean intakeButtonBeenPressed = false;
	private String state = "Up";
	
	

	public GearSubsystem(VictorSP motorLift, DoubleSolenoid solenoid, DoubleSolenoid liftSolenoid){
		intake = liftSolenoid;
		mainSolenoid = solenoid;
		motor = motorLift;
	}
	
	
	
	public void main(boolean buttonForIntake, boolean buttonForMain){
		liftIntake(buttonForIntake);
		if(buttonForIntake){
			motor.set(1);
		} else {
			motor.set(0);
		}
		
		if(buttonForMain){
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
		} else {
			intake.set(Value.kForward);
		}
	}
	
	
	
}
