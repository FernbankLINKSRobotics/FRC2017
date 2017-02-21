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
	
	
	
	

}
