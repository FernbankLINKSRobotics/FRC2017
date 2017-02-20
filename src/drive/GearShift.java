package drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team4468.robot.CMap;

import drive.*;

public class GearShift {
	public static LeftDriveTrain leftDrive;
	public static RightDriveTrain rightDrive;
	public static DoubleSolenoid shifter;
	public static boolean shiftButton;
	
	public static String state;
	private static boolean buttonBeenPressed = false;
	
	
	public GearShift(LeftDriveTrain leftDriver, RightDriveTrain rightDriver, DoubleSolenoid gearShift){
		leftDrive = leftDriver;
		rightDrive = rightDriver;
		shifter = gearShift;
		
		state = "Low";
	}
	
	public void shift(){
		shiftButton = CMap.leftStick.getTrigger();
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
		
		manageSolenoid();
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
