package drive;

import org.usfirst.frc.team4468.robot.CMap;

public class DriveTrain {
	LeftDriveTrain left;
	RightDriveTrain right;
	
	public DriveTrain(LeftDriveTrain leftD, RightDriveTrain rightD){
		left=leftD;
		right=rightD;
	}
	
	public void set(double leftSet, double rightSet) {
		left.set(leftSet);
		right.set(rightSet);
	}
	
	private void PIDsetSetpoint(double leftPoint, double rightPoint){
		CMap.leftPID.getPIDController().setSetpoint(leftPoint);
		CMap.rightPID.getPIDController().setSetpoint(rightPoint);
	}
}
