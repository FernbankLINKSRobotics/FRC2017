package org.usfirst.frc.team4468.robot;
import drive.DriveTrain;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.VictorSP;
import gears.GearSubsystem;
import shooter.ShooterSubsystem;
import vision.visionSubsystem;
import PIDsub.*;
public class CMap {
	//Motors
	public static VictorSP leftDrive,
							rightDrive;
	
	//Encoders
	
	//Pnumatics
	
	//PID Subsystems
	public static leftDrive leftPID;
	public static rightDrive rightPID;
	public static turnPID turnController;
	
	//Regular Subsystems
	public static DriveTrain drive;
	public static visionSubsystem vision;
	public static ShooterSubsystem shooter;
	public static GearSubsystem gears;
	
	public static void initialize(){
		//Motors
		leftDrive = new VictorSP(0);
		rightDrive = new VictorSP(1);
		//Encoders
		
		//Pnumatics
		
		//PID Subsystems
		leftPID = new leftDrive();
		rightPID = new rightDrive();
		turnController = new turnPID();
		
		turnController.getPIDController().disable();
		
		//Subsystems
		drive = new DriveTrain(leftDrive, rightDrive);
		vision = new visionSubsystem("LINKSVision");
	}
}
