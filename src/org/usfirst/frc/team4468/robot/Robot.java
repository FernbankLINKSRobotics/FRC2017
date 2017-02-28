
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
	int autonomous;
	
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
			autonomous = 1; // Gears Left
		} else if(SmartDashboard.getBoolean("DB/Button 2", false)){
			autonomous = 2; // Gears Center
		} else if(SmartDashboard.getBoolean("DB/Button 3", false)){
			autonomous = 3; // Gears Right
		} else {
			autonomous = 0; // Break Baseline
		}
	}
	
	public void autonomousPeriodic(){
		
		if(autonomous == 0){
			Baseline.main();
		} else {
			Gear.run(autonomous);
		}
	}
	
	public void teleopInit(){
		//CMap.drive.disablePID(); //Disable the Left & Right Drive PIDs so they don't
								 //interfere with the joysticks.
			CMap.leftEncoder.reset();
			CMap.rightEncoder.reset();
			
			CMap.leftPID.getPIDController().disable();
			CMap.rightPID.getPIDController().disable();

	}
	
	public void teleopPeriodic(){
		//Drive Code
		CMap.leftDrive.set(-CMap.leftStick.getY());
		CMap.rightDrive.set(-CMap.rightStick.getY());
		
		//Shifting Code
		CMap.shift.main(CMap.leftStick.getTrigger()); //This is the button for the code
		
		//Climbing Code
		CMap.climber.climb(CMap.auxStick.getRawButton(2)); //So, use getRawButton if you need to specifically
		
		System.out.println(CMap.leftEncoder.getDistance());
		
		//Gear Mechanism Code
		CMap.gears.adjustIntake(CMap.auxStick.getTrigger());
		
		
	}
	
	
	
	//Use Test Mode to charge the compressor.
	//Compressor compressor = new Compressor();
	public void testInit(){
		//compressor.setClosedLoopControl(true);
	}
	
	public void testPeriodic(){
		//System.out.println(CMap.leftEncoder.getRaw());
		//System.out.println(CMap.rightEncoder.getRaw());
		//Gear.run(2);
		//CMap.rightPID.getPIDController().setSetpoint(200);
		//System.out.println(CMap.rightEncoder.getDistance());
	}
	
}
