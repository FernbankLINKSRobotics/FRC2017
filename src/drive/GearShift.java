package drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import drive.*;

public class GearShift {
	public static DriveTrain drive;
	public static DoubleSolenoid leftShift, rightShift;
	public static Joystick shiftStick;
	
	public static String state;
	private static boolean buttonBeenPressed = false;
	
	
	public GearShift(DriveTrain drivetrain, DoubleSolenoid leftGearShift, DoubleSolenoid rightGearShift, Joystick shiftStick){
		drive = drivetrain;
		leftShift = leftGearShift;
		rightShift = rightGearShift;
		
		state = "Low";
	}
	
	public void shift(){
		if(shiftStick.getTrigger()){
			if(!buttonBeenPressed){
				if(state == "Low"){
					state = "High";
				} else {
					state = "Low";
				}
				buttonBeenPressed = true;
			}
		} else {
			buttonBeenPressed = false;
		}
		
		manageSolenoid();
	}
	
	public static void manageSolenoid(){
		if(state == "High"){
			leftShift.set(DoubleSolenoid.Value.kForward);
			rightShift.set(DoubleSolenoid.Value.kForward);
		} else {
			leftShift.set(DoubleSolenoid.Value.kReverse);
			rightShift.set(DoubleSolenoid.Value.kReverse);
		}
	}
}
