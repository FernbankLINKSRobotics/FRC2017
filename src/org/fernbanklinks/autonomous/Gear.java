package autonomous;

import org.fernbanklinks.main.*;

import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

public class Gear {
	
	//INCHES
	//DISTANCE TO LINE WHERE CENTER LIFT IS
	static double distanceToLift = 30;
	
	//DEGREES
	//COUNTERCLOCKWISE IS POSITIVE
	//ANGLE OF LIFT PEG on Boiler Side
	static double boilerAngle = -60;
	
	static boolean turned = false;
	static boolean timerStarted = false;
	
	public static Timer timer = new Timer();
	
	private static int stage = 1;
	
	public static void run(int position){
		if(position == 1){
			//leftEncoderAutonomous();
			
			timeLeft();
		} else if(position == 2){
			timeCenter();
		} else if(position == 3){
			timeRight();
			
			//CMap.leftPID.getPIDController().enable();
			//CMap.rightPID.getPIDController().enable();
			
			//CMap.leftPID.setSetpoint(distanceToLift);
			//CMap.rightPID.setSetpoint(distanceToLift);
			
		}
	}
	
	public static void leftEncoderAutonomous(){
		System.out.println(CMap.leftEncoder.getDistance());
		
		if(stage == 1){
			CMap.leftPID.getPIDController().enable();
			CMap.rightPID.getPIDController().enable();
			
			CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
			
			if(CMap.leftEncoder.getDistance() >= distanceToLift - 2){
				CMap.leftPID.getPIDController().disable();
				CMap.rightPID.getPIDController().disable();
				
				CMap.turnController.getPIDController().enable();
				
				stage = 2;
			}
		} else if(stage == 2){
			CMap.turnController.getPIDController().setSetpoint(boilerAngle);
			
			if(CMap.autoTimer.get() > 4){
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
	
	//Center Gear Auto
	public static void timeCenter(){
		double voltage = DriverStation.getInstance().getBatteryVoltage();
		double ratio = 12.9/voltage;
		
		if (timer.get()<1 * ratio) {
			CMap.leftDrive.set(.3);
			CMap.rightDrive.set(.3);
		} else if(timer.get() < 2*ratio){
			CMap.leftDrive.set(0);
			CMap.rightDrive.set(0);
		} else if(timer.get() < 9*ratio){
			CMap.turnController.getPIDController().enable();
			CMap.turnController.getPIDController().setSetpoint(-10);
		} else {
			CMap.leftDrive.set(0);
			CMap.rightDrive.set(0);
		}
	}
	
	
	public static void timeLeft(){
		double voltage = DriverStation.getInstance().getBatteryVoltage();
		double ratio = 12.9/voltage*1.1;
		
		System.out.println(timer.get());
		
		if(timer.get() < .85*ratio){
			CMap.leftDrive.set(.5);
			CMap.rightDrive.set(.5);
			
			System.out.println(1);
		} else if(timer.get() > 1.2*ratio && timer.get() < 2.2) {
			CMap.leftDrive.set(0);
			CMap.rightDrive.set(0);
			
			System.out.println(2);
		} else if(!turned){
			CMap.turnController.getPIDController().enable();
			CMap.turnController.getPIDController().setSetpoint(-60);
			System.out.println(3);
			
			if(timer.get() > 4){
				turned = true;
				CMap.turnController.getPIDController().disable();
			}
		} else if(timer.get() < 7){
			System.out.println(4);
			CMap.leftDrive.set(.5);
			CMap.rightDrive.set(.5);
		} else if(timer.get() < 7.5){
			CMap.leftDrive.set(-.0);
			CMap.rightDrive.set(-.0);
		}
	}
	
	public static void timeRight(){
		double voltage = DriverStation.getInstance().getBatteryVoltage();
		double ratio = 12.9/voltage*1.1;
		
		System.out.println(1.3*ratio);
		
		if(timer.get() < 1.3*ratio){
			CMap.leftDrive.set(.5);
			CMap.rightDrive.set(.5);
			
			System.out.println(1);
		} else if(timer.get() > 2*ratio && timer.get() < 3) {
			CMap.leftDrive.set(0);
			CMap.rightDrive.set(0);
			System.out.println(2.5);
		} else if(!turned){
			CMap.turnController.getPIDController().enable();
			CMap.turnController.getPIDController().setSetpoint(60);
			System.out.println(3);
			
			if(timer.get() > 5){
				turned = true;
				CMap.turnController.getPIDController().disable();
			}
		} else if(timer.get() < 7){
			System.out.println(4);
			CMap.leftDrive.set(.4);
			CMap.rightDrive.set(.4);
		} else if(timer.get() < 9){
			CMap.leftDrive.set(0);
			CMap.rightDrive.set(0);
		}
	}
}

