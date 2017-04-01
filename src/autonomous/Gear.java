package autonomous;
import org.usfirst.frc.team4468.robot.*;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class Gear {
	/*
	 * STEPS IN AUTONOMOUS:
	 * 
	 * 1. Drive Straight
	 * 2. Turn to angle if neccesary
	 * 3. Drive Straight
	 * 4. Drop Gear
	 */
	
	//DISTANCE TO LIFT FROM STARTING
	static double distanceToLift = 30;
	
	static boolean timerStarted = false;
	
	static double zeroGyroAngle = -999;
	
	static Timer timer = new Timer();
	
	private static int stage = 1;
	private static int factor = 0;
	
	public static void run(int position){
		if(position == 1){
			boilerAutonomous();
		} else if(position == 2){
			
		} else if(position == 3){
			
		}
	}
	
	public static void boilerAutonomous(){
		if(stage == 1){
			CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
			
			if(CMap.leftEncoder.getDistance() >= distanceToLift - 1){
				CMap.leftPID.getPIDController().disable();
				CMap.rightPID.getPIDController().disable();
				
				CMap.turnController.getPIDController().enable();
				
				stage = 2;
			}
		} else if(stage == 2){
			CMap.turnController.getPIDController().setSetpoint(-60);
			
			if(checkTurn(CMap.turnController.getPosition())){
				CMap.turnController.getPIDController().disable();
				
				CMap.leftEncoder.reset();
				CMap.rightEncoder.reset();
				
				stage = 3;
			}
		} else if(stage == 3){
			if(timer.get() < 1){
				if(factor % 2 == 0 && factor < 10){
					CMap.leftDrive.set(-1);
					CMap.rightDrive.set(-.8);
				} else{
					CMap.leftDrive.set(-.8);
					CMap.rightDrive.set(-1);
					if(factor > 25)
						factor = 0;
					}
			}
			
			factor += 1;
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
		if(!timerStarted){
			timer.start();
			CMap.zeroGyroAngle = CMap.gyro.getAngle();
			timerStarted = true;
		} else if(timer.get() < 10) {
			double angle = CMap.gyro.getAngle() - CMap.zeroGyroAngle;
			CMap.drive.drive(1, -angle);
		} else {
			CMap.leftDrive.set(1);
			CMap.rightDrive.set(1);
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

