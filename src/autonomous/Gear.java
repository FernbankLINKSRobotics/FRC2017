package autonomous;
import org.usfirst.frc.team4468.robot.*;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class Gear {
	
	//INCHES
	//DISTANCE TO LINE WHERE CENTER LIFT IS
	static double distanceToLift = 30;
	
	//DEGREES
	//COUNTERCLOCKWISE IS POSITIVE
	//ANGLE OF LIFT PEG on Boiler Side
	static double boilerAngle = -60;
	
	static boolean timerStarted = false;
	
	static Timer timer = new Timer();
	
	private static int stage = 1;
	
	public static void run(int position){
		if(position == 1){
			boilerAutonomous();
		} else if(position == 2){
			driveStraightToCenter();
		} else if(position == 3){
			CMap.leftPID.getPIDController().enable();
			CMap.rightPID.getPIDController().enable();
			
			CMap.leftPID.setSetpoint(distanceToLift);
			CMap.rightPID.setSetpoint(distanceToLift);
		}
	}
	
	public static void boilerAutonomous(){
		if(stage == 1){
			CMap.leftPID.getPIDController().enable();
			CMap.rightPID.getPIDController().enable();
			
			CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
			
			if(CMap.leftEncoder.getDistance() >= distanceToLift - 1){
				CMap.leftPID.getPIDController().disable();
				CMap.rightPID.getPIDController().disable();
				
				CMap.turnController.getPIDController().enable();
				
				stage = 2;
			}
		} else if(stage == 2){
			CMap.turnController.getPIDController().setSetpoint(boilerAngle);
			
			if(checkTurn(CMap.turnController.getPosition())){
				CMap.turnController.getPIDController().disable();
				
				CMap.leftEncoder.reset();
				CMap.rightEncoder.reset();
				
				timer.start();
				
				stage = 3;
			}
		} else if(stage == 3 && timer.get() < 6){
			
			CMap.leftDrive.set(.5);
			CMap.rightDrive.set(.5);
		} else {
			CMap.leftDrive.set(-.1);
			CMap.rightDrive.set(-.1);
		}
	}
	
	//Are we at the angle?
	public static boolean checkTurn(double angle){
		if(59.5 < Math.abs(angle) && Math.abs(angle) < 60.5){
			return true;
		} else {
			return false;
		}
	}
	
	//Center Gear Auto
	public static void driveStraightToCenter(){
		if (timer.get()<1.5) {
			CMap.leftDrive.set(.5);
			CMap.rightDrive.set(.5);
		}
		else if(timer.get() < 6){
			CMap.turnController.getPIDController().enable();
			CMap.turnController.getPIDController().setSetpoint(-10);
		} else {
			CMap.turnController.getPIDController().disable();
			CMap.leftDrive.set(.5);
			CMap.rightDrive.set(.5);
		}
	}
	
	//Side Gear to Gear Peg
	public static void driveToSideLift(){
		if(!timerStarted){
			timer.start();
			timerStarted = true;
		} else if(timer.get() < 10) {
			double angle = CMap.gyro.getAngle() + 60 - CMap.zeroGyroAngle;
			CMap.drive.drive(.5, -angle);
		} else {
			CMap.leftDrive.set(1);
			CMap.rightDrive.set(1);
		}
	}
}

