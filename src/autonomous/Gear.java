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
