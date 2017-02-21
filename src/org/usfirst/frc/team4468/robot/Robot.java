
package org.usfirst.frc.team4468.robot;

import autonomous.Baseline;
import autonomous.Gear;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	String autonomous = "Break the Baseline";
	
	public void robotInit(){
		CMap.initialize();
		
		//This will put the instructions for which button to press
		SmartDashboard.putString("DB/String 0", "Gears Left");
		SmartDashboard.putString("DB/String 1", "Gears Center");
		SmartDashboard.putString("DB/String 2", "Gears Right");
		SmartDashboard.putString("DB/String 3", "Break the Baseline");
	}
	
	public void autonomousInit(){
		//This will get the values from the Driver Station so we don't reflash code.
		if(SmartDashboard.getBoolean("DB/Button 1", false)){
			autonomous = "Gears Left";
		} else if(SmartDashboard.getBoolean("DB/Button 2", false)){
			autonomous = "Gears Center";
		} else if(SmartDashboard.getBoolean("DB/Button 3", false)){
			autonomous = "Gears Right";
		} else {
			autonomous = "Baseline";
		}
	}
	
	public void autonomousPeriodic(){
		/*
		switch(autonomous){
		case "Gears Left":
			Gear.main(1);
			break;
		case "Gears Center":
			Gear.main(2);
			break;
		case "Gears Right":
			Gear.main(3);
			break;
		default:
			Baseline.main();
		}*/
		
		
	}
	
	public void teleopInit(){
		//CMap.drive.disablePID(); //Disable the Left & Right Drive PIDs so they don't
								 //interfere with the joysticks.
	}
	
	public void teleopPeriodic(){
		//CMap.drive.main();
		//CMap.gears.main();
		//CMap.climber.main()
		//Drive Code
		CMap.leftDrive.set(CMap.leftStick.getY());
		CMap.rightDrive.Set(-CMap.rightStick.getY());
		
		//Shifting Code
		CMap.shift.main(CMap.leftStick.getTrigger()); //This is the button for the code
		
		//Climbing Code
		CMap.climber.climb(CMap.auxStick.getRawButton(2)); //So, use getRawButton if you need to specifically
		//call a button outside of the trigger. The number used corresponds to the button with that number on the driver station.
		
		//Gear Mechanism Code
		CMap.gears.adjustIntake(CMap.auxStick.getTrigger());
		
	}
	
	//Use Test Mode to charge the compressor.
	Compressor compressor = new Compressor();
	public void testInit(){
		compressor.setClosedLoopControl(true);
	}
	
	public void testPeriodic(){
		
	}
	
}
