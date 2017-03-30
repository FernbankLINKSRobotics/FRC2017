
package org.usfirst.frc.team4468.robot;

import autonomous.Baseline;
import autonomous.Gear;
import climber.ClimbSubsystem;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
	public int autonomous;
	
	public void robotInit(){
		CMap.initialize();
		
		//This will put the instructions for which button to press
		SmartDashboard.putString("DB/String 0", "Gears Left");
		SmartDashboard.putString("DB/String 1", "Gears Center");
		SmartDashboard.putString("DB/String 2", "Gears Right");
		SmartDashboard.putString("DB/String 3", "Break the Baseline");
		

		CMap.gyro.resetDisplacement();
		CMap.gyro.reset();
		CMap.gyro.zeroYaw();
		
		CMap.zeroGyroAngle = CMap.gyro.getAngle();
		
	}
	
	public void autonomousInit(){
		//CMap.gearMechanism.set(Value.kForward);
		//This will get the values from the Driver Station so we don't reflash code.
		if(SmartDashboard.getBoolean("DB/Button 0", false)){
			autonomous = 1; // Gears Left
		} else if(SmartDashboard.getBoolean("DB/Button 1", false)){
			autonomous = 2; // Gears Center
		} else if(SmartDashboard.getBoolean("DB/Button 2", false)){
			autonomous = 3; // Gears Right
		} else {
			autonomous = 0; // Break Baseline
		}
	}
	
	public void autonomousPeriodic(){
		System.out.println(autonomous);
		
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
		//Comment to do Commit
		if(CMap.leftStick.getRawButton(2)){
			//Drive Forward
			CMap.drive.set(1,1);
		} else if(CMap.leftStick.getRawButton(3)){
			//Drive Backward
			CMap.drive.set(-1, -1);
		} else if(Math.abs(CMap.leftStick.getY()) > 0 || Math.abs(CMap.rightStick.getY()) > 0){
			CMap.drive.set(-CMap.leftStick.getY(), -CMap.rightStick.getY());
		} else {
			CMap.drive.set(0, 0);
		}
		
		//Shifting Code
		//CMap.shift.main(CMap.leftStick.getTrigger()); //This is the button for the code
		
		//Climbing Code
		//CMap.climber.climb(CMap.rightStick.getRawButton(4), CMap.rightStick.getRawButton(5)); //So, use getRawButton if you need to specifically
		
		
		System.out.println(CMap.leftEncoder.getDistance());
		
		//Gear Mechanism Code
		//Button 1 is the Lift Intake
		//Button 2 is the Motor Roller
		//CMap.gears.main(CMap.rightStick.getTrigger(), CMap.rightStick.getRawButton(2));
		
		/*
		//Drive straight code
		if (CMap.leftStick.getRawButton(2)) {
			CMap.leftPID.setSetpoint(395);
			CMap.rightPID.setSetpoint(395);
		}*/
		
		
	}
	
	private Timer timer = new Timer();
	
	//Use Test Mode to charge the compressor.
	//Compressor compressor = new Compressor();
	public void testInit(){
		//CMap.turnController.getPIDController().setSetpoint(90);
		//CMap.drive.angleTurn(90);
		//CMap.drive.angleTurn(-90);
		
		CMap.zeroGyroAngle = CMap.gyro.getAngle();
		
		CMap.turnController.getPIDController().enable();
		CMap.turnController.getPIDController().setSetpoint(-60);
		
		timer.start();
		

	}
	
	private int factor = 0;

	
	public void testPeriodic(){
		
		//System.out.println(CMap.leftEncoder.get());
		//System.out.println(CMap.rightEncoder.get());
		
		
		/*
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
		factor += 1;
		} else if(timer.get() > 5){
			System.out.println("GEUNHIUGDSHUIGDHUHGIUSH");
			CMap.zeroGyroAngle = CMap.gyro.getAngle();
			
			CMap.turnController.getPIDController().enable();
			CMap.turnController.getPIDController().setSetpoint(-60);
		}
		
		System.out.println(timer.get());*/
		
		CMap.rightMiddleDrive.set(1);
		
		//System.out.println(CMap.gyro.getAngle() - CMap.zeroGyroAngle);
		
		//CMap.drive.PIDsetSetpoint(-30, -30);
		//CMap.turnController.getPIDController().setSetpoint(60);
	}
	
}
