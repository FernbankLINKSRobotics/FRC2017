package autonomous;
import org.usfirst.frc.team4468.robot.*;

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
	
	static boolean initialStraight = false;
	static boolean turnedToSide = false;
	static boolean drivenUp = false;
	
	static boolean visionCorrection = false;
	
	static double distanceToLift;
	static double angle;
	
	static double zeroGyroAngle = -999;
	
	Timer gearTimer = new Timer();
	
	
	public static void run(int position){
		if(position == 1 || position == 3){
			driveToSide(position);
		} else if(position == 2){
			CMap.leftPID.getPIDController().setSetpoint(distanceToLift);
			CMap.rightPID.getPIDController().setSetpoint(distanceToLift);
		}
	}
	
	public static void driveToSide(int side){
		if(side == 1){
			//Drive Forward
			if(!initialStraight){
				CMap.leftPID.getPIDController().setSetpoint(0);
				CMap.rightPID.getPIDController().setSetpoint(0);
				if(CMap.leftPID.onTarget()){
					initialStraight = true;
					CMap.leftPID.getPIDController().disable();
					CMap.rightPID.getPIDController().disable();
				}
			//Gyro Turn, have vision correct error
			} else if(!turnedToSide){
				//Need to replace the 45 with whatever the angle is
				CMap.turnController.getPIDController().setSetpoint(45);
				if(CMap.turnController.onTarget()){
					turnedToSide = true;
					CMap.turnController.disable();
					CMap.leftPID.getPIDController().enable();
					CMap.rightPID.getPIDController().enable();
				}
			//Drive Forward and Place Gear
			} else {
				CMap.leftPID.getPIDController().setSetpoint(0);
				CMap.rightPID.getPIDController().setSetpoint(0);
			}
		} else {
			//Drive Forward
			if(!initialStraight){
				CMap.leftPID.getPIDController().setSetpoint(0);
				CMap.rightPID.getPIDController().setSetpoint(0);
				if(CMap.leftPID.onTarget()){
					initialStraight = true;
					CMap.leftPID.getPIDController().disable();
					CMap.rightPID.getPIDController().disable();
				}
			//Gyro Turn, have vision correct error
			} else if(!turnedToSide){
				//Need to replace the 45 with whatever the angle is
				CMap.turnController.getPIDController().setSetpoint(45);
				if(CMap.turnController.onTarget()){
					turnedToSide = true;
					CMap.turnController.disable();
					CMap.leftPID.getPIDController().enable();
					CMap.rightPID.getPIDController().enable();
				}
			//Drive Forward and Place Gear
			} else {
				CMap.leftPID.getPIDController().setSetpoint(0);
				CMap.rightPID.getPIDController().setSetpoint(0);
			}
		}
	}
	

}
