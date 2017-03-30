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
	
	
	//DISTANCE TO LIFT FROM STARTING
	static double distanceToLift = 30;
	static final double rotation = 5;
	static final double sideDistance = 20;
	static double angle;
	
	static double zeroGyroAngle = -999;
	
	Timer gearTimer = new Timer();
	
	
	public static void run(int position){
		if(position == 1 || position == 3){
			driveToSide(position);
			
		} else if(position == 2){
			CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
			System.out.println(CMap.leftPID.getPosition());
			if (CMap.leftPID.getPosition() >= distanceToLift){
				CMap.leftPID.getPIDController().disable();
				CMap.rightPID.getPIDController().disable();
				
				CMap.drive.set(0.1, 0.1);
			} else {
				CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
			}
		}
	}
	
	public static void driveToSide(int side){
		//System.out.println(CMap.leftPID.getPosition());
		if(side == 1){
			//Drive Forward
			if(!initialStraight){
				CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
				System.out.println(CMap.leftPID.getPosition());
				if(CMap.leftPID.getPosition() >= distanceToLift - 1){
					System.out.println("IM HERE");
					CMap.leftEncoder.reset();
					initialStraight = true;
					
				}
			//Gyro Turn, have vision correct error
			} else if(!turnedToSide){
				CMap.turnController.getPIDController().setSetpoint(100);
				if(CMap.leftPID.getPosition() >= 6){
					//turnedToSide = true;
					System.out.println("IM HERE");
					CMap.leftEncoder.reset();
					CMap.rightEncoder.reset();
					turnedToSide = true;
				}
			//Drive Forward and Place Gear
			} else {
				if(CMap.leftPID.getPosition() >= sideDistance){
					CMap.leftPID.getPIDController().disable();
					CMap.rightPID.getPIDController().disable();
					
					CMap.drive.set(0.1, 0.1);
				} else {
					CMap.drive.PIDsetSetpoint(sideDistance, sideDistance);
				}
			}
		} else {//Drive Forward
			if(!initialStraight){
				CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
				System.out.println(CMap.leftPID.getSetpoint());
				System.out.println(CMap.rightPID.getSetpoint());
				System.out.println("FGYISGIGFIUHIUHUI");
				if(CMap.leftPID.getPosition() >= distanceToLift - 1){
					System.out.println("IM HERE");
					CMap.rightEncoder.reset();
					initialStraight = true;
					turnedToSide = false;
					
				}
			//Gyro Turn, have vision correct error
			} else if(!turnedToSide){
				System.out.println("FHNDUIABNGFSNGFIUBNDSIUNIUFc");
				CMap.drive.PIDsetSetpoint(distanceToLift, distanceToLift);
				System.out.println(CMap.rightPID.getPosition());
				if(CMap.rightPID.getPosition() >= 24){
					//turnedToSide = true;
					System.out.println("IM HERE");
					CMap.leftEncoder.reset();
					CMap.rightEncoder.reset();
					turnedToSide = true;
				}
			//Drive Forward and Place Gear
			} else {
				CMap.drive.PIDsetSetpoint(sideDistance, sideDistance);
			}
		}
	}
}
