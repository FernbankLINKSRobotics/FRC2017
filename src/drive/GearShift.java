package drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team4468.robot.CMap;

import drive.*;

public class GearShift {
	public static DriveTrain drive;
	public static DoubleSolenoid leftShift, rightShift;
	public static boolean shiftButton;
	
	public static String state;
	private static boolean buttonBeenPressed = false;
	
	
	public GearShift(DriveTrain drivetrain, DoubleSolenoid leftGearShift, DoubleSolenoid rightGearShift, boolean button){
		drive = drivetrain;
		leftShift = leftGearShift;
		rightShift = rightGearShift;
		
		state = "Low";
	}
	
	public void shift(){
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
			leftShift.set(DoubleSolenoid.Value.kForward);
			rightShift.set(DoubleSolenoid.Value.kForward);
			CMap.leftEncoder.setDistancePerPulse(drive.highDistancePerPulse);
			CMap.rightEncoder.setDistancePerPulse(drive.highDistancePerPulse);
		} else {
			leftShift.set(DoubleSolenoid.Value.kReverse);
			rightShift.set(DoubleSolenoid.Value.kReverse);
			CMap.leftEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
			CMap.rightEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		}
	}
}
