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
	static boolean droppedGear = false;
	
	static double distanceToLift;
	static double angle;
	
	static double zeroGyroAngle = -999;
	
	Timer gearTimer = new Timer();
	public static void main(int which){
		
		if(which == 1 || which == 3){ //If any of the goals are on the sides
			driveToSide(which); //See method
		} else if(which == 2){ //If we are going to the center goal
			initialStraight = true;
			if(!drivenUp){ //If we haven't driven up yet
				CMap.drive.driveForward(distanceToLift); //Drive Forward by x inches
				if(CMap.drive.onTarget()){ //If we have reached the setpoint
					drivenUp = true; //Disable the PID Loops
					CMap.drive.disablePID();
					System.out.println("Reached center gear lift.");
				}
			} else if(!droppedGear){ //Drop the Gear
				CMap.gears.depositGear(true);
				droppedGear = true;
			}
		}
	}
	
	public static void driveToSide(int which){
		if(which == 1){
			if(!initialStraight){ //Initial Straight Drive
				CMap.drive.driveForward(distanceToLift); //Drive Forward
				if(CMap.drive.onTarget()){ //If we have reached the setpoint
					initialStraight = true; //Disable the PID Loops
					CMap.drive.disablePID();
					System.out.println("About to start turning.");
				}
			} else if(!turnedToSide){
				//Might not need if statement
				if(!CMap.turnController.getPIDController().isEnabled()){
					CMap.turnController.getPIDController().enable(); //Enable the Turn Controller
					zeroGyroAngle = CMap.turnController.getPosition();
					System.out.println("Starting the turn.");
					
				}
				
				/*
				 * So, let me explain the zero Gyro Angle. Odds are that by the time we reach
				 * the end of the initial drive, we will already have changed our heading from
				 * 0 degrees. As a result, we need to compensate for that. My idea is that before
				 * we start the turn, we will get the current heading and store that as a zero. We
				 * could reset the gyro using its reset() method, but that might invoke the calibration method.
				 * 
				 * So, the current heading is stored as zeroGyroAngle and we add/subtract the vision angle to that
				 * heading and set that as the setpoint. This allows us to dynamically change the setpoint
				 * as the vision continues to change its gyro heading.
				 *
				 */
				CMap.turnController.getPIDController().setSetpoint(zeroGyroAngle + CMap.vision.getGyroAngle());
				
				
				
				if(CMap.turnController.onTarget()){ //Just what the method says
					CMap.turnController.getPIDController().disable();
					turnedToSide= true;
					System.out.println("Lined up with gear lift.");
				}
			} else if(!drivenUp){
				CMap.drive.enablePID();
				
				CMap.drive.driveForward(0);
				
				if(CMap.drive.onTarget()){ //If we have reached the setpoint
					drivenUp = true; //Disable the PID Loops
					CMap.drive.disablePID();
					System.out.println("At gear lift.");
				}
			} else if(!droppedGear){
				CMap.gears.depositGear(true);
				droppedGear = true;
			}
		} else if(which == 3){
			if(!initialStraight){ 
				CMap.drive.driveForward(distanceToLift); //Drive Forward
				if(CMap.drive.onTarget()){ //If we have reached the setpoint
					initialStraight = true; //Disable the PID Loops
					CMap.drive.disablePID();
					System.out.println("About to start turning.");
				}
			} else if(!turnedToSide){
				if(!CMap.turnController.getPIDController().isEnabled()){
					CMap.turnController.getPIDController().enable(); //Enable the Turn Controller
					CMap.turnController.getPIDController().setSetpoint(angle);
					System.out.println("Starting the turn.");
				}
				
				if(CMap.turnController.onTarget()){
					CMap.turnController.getPIDController().disable();
					turnedToSide= true;
					System.out.println("Lined up with gear lift.");
				}
			} else if(!drivenUp){
				CMap.drive.enablePID();
				
				CMap.drive.driveForward(0);
				
				if(CMap.drive.onTarget()){ //If we have reached the setpoint
					drivenUp = true; //Disable the PID Loops
					CMap.drive.disablePID();
					System.out.println("At gear lift.");
				}
			} else if(!droppedGear){
				CMap.gears.depositGear(true);
				droppedGear = true;
			}
		}
	}


}
