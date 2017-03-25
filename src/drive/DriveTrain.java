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
	
	public void PIDsetSetpoint(double leftPoint, double rightPoint){
		CMap.leftPID.getPIDController().setSetpoint(leftPoint);
		CMap.rightPID.getPIDController().setSetpoint(rightPoint);
	}
	
	public void angleTurn(double angle){
		double neg = 1;
		if(angle < 0){ neg = -1; }
		while(angle < CMap.gyro.getAngle()){
			left.set(neg * 0.1);
			right.set(-neg * 0.1);
		}
	}
}
