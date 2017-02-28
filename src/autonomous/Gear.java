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
	static double distanceToLift = 90;
	static double angle;
	
	static double zeroGyroAngle = -999;
	
	Timer gearTimer = new Timer();
	
	
	public static void run(int position){
		if(position == 1 || position == 3){
			driveToSide(position);
			
		} else if(position == 2){
			PIDsetSetpoint(distanceToLift);
		}
	}
	
	private static void PIDsetSetpoint(double leftPoint, double rightPoint){
		CMap.leftPID.getPIDController().setSetpoint(leftPoint);
		CMap.rightPID.getPIDController().setSetpoint(rightPoint);
	}
	
	private static void PIDcontroller(boolean enable){
		if(enable == true){
			CMap.leftPID.getPIDController().enable();
			CMap.rightPID.getPIDController().enable();
		} else {
			CMap.leftPID.getPIDController().disable();
			CMap.rightPID.getPIDController().disable();
		}
	}
	
	public static void driveToSide(int side){
		//System.out.println(CMap.leftPID.getPosition());
		if(side == 1){
			//Drive Forward
			if(!initialStraight){
				CMap.leftPID.getPIDController().setSetpoint(distanceToLift);
				CMap.rightPID.getPIDController().setSetpoint(distanceToLift);
				if(CMap.leftPID.getPosition() >= distanceToLift - 1){
					System.out.println("IM HERE");
					initialStraight = true;
					
				}
			//Gyro Turn, have vision correct error
			} else if(!turnedToSide){
				
				CMap.leftPID.getPIDController().setSetpoint(180);
				if(CMap.leftPID.getPosition() >= 179){
					//turnedToSide = true;
					System.out.println("IM HERE");
					turnedToSide = true;
				}
			//Drive Forward and Place Gear
			} else {
				PIDsetSetpoint(360, 270);
			}
		} else {//Drive Forward
			System.out.println("FGYISGIGFIUHIUHUI");
			if(!initialStraight){
				PIDsetSetpoint(distanceToLift, distanceToLift);
				if(CMap.leftPID.getPosition() >= distanceToLift - 1){
					System.out.println("IM HERE");
					initialStraight = true;
					
				}
			//Gyro Turn, have vision correct error
			} else if(!turnedToSide){
				
				CMap.rightPID.getPIDController().setSetpoint(180);
				if(CMap.rightPID.getPosition() >= 179){
					//turnedToSide = true;
					System.out.println("IM HERE");
					turnedToSide = true;
				}
			//Drive Forward and Place Gear
			} else {
				PIDsetSetpoint(270, 360);
			}
		}
	}
}
