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
	
	//Method for Driving Straight
	//curve < 0 means left
	//curve > 0 means right
	public void drive(double outputMagnitude, double turn){
		double m_sensitivity = 0.5;
		
		double leftOutput,
			   rightOutput;
		
		double curve = turn * .1;
		if (curve < 0) {
		      double value = Math.log(-curve);
		      double ratio = (value - m_sensitivity) / (value + m_sensitivity);
		      if (ratio == 0) {
		        ratio = .0000000001;
		      }
		      leftOutput = outputMagnitude / ratio;
		      rightOutput = outputMagnitude;
		} else if (curve > 0) {
		      double value = Math.log(curve);
		      double ratio = (value - m_sensitivity) / (value + m_sensitivity);
		      if (ratio == 0) {
		        ratio = .0000000001;
		      }
		      leftOutput = outputMagnitude;
		      rightOutput = outputMagnitude / ratio;
		    } else {
		      leftOutput = outputMagnitude;
		      rightOutput = outputMagnitude;
	 }
		
		CMap.leftDrive.set(leftOutput);
		CMap.rightDrive.set(rightOutput);
	}
}
