package drive;

import edu.wpi.first.wpilibj.*;


public class RightDriveTrain {
	public static VictorSP 
	rightTopDrive,
	rightMiddleDrive,
	rightBottomDrive;
	
	public void initialize() {
		
		System.out.println("Motors have been initialized.");
	}

	public RightDriveTrain(VictorSP motor4, VictorSP motor5, VictorSP motor6){
		rightTopDrive=motor4;
		rightMiddleDrive=motor5;
		rightBottomDrive=motor6;
		
	}
	public void rightDriveTrain(VictorSP rightTopDrive, VictorSP rightMiddleDrive, VictorSP rightBottomDrive){
		
	}
	public void Set(double speed) {
		rightTopDrive.set(speed);
		rightMiddleDrive.set(speed);
		rightBottomDrive.set(speed);
	}
}
