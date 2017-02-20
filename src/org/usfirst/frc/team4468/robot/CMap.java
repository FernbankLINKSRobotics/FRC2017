package org.usfirst.frc.team4468.robot;

import drive.DriveTrain;
import com.kauailabs.navx.frc.AHRS;
import drive.GearShift;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import gears.GearSubsystem;
import vision.visionSubsystem;
import PIDsub.*;
import climber.ClimbSubsystem;
import drive.LeftDriveTrain;
import drive.RightDriveTrain;

public class CMap { 
	//Encoders
	public static Encoder leftEncoder,
							rightEncoder;
	
	//Gyroscope
	public static AHRS gyro;
	
	//Motors
	public static VictorSP leftTopDrive,
							leftMiddleDrive,
							leftBottomDrive,
							rightTopDrive,
							rightMiddleDrive,
							rightBottomDrive,
						   climbMotor;
	
	//Joysticks
	public static Joystick leftStick, rightStick, auxStick;
	
	//Pnumatics
	public static Compressor compressor;
	
	public static DoubleSolenoid gearMechanism,
								 driveShift;
	//Motors from Drive Train
	public static LeftDriveTrain leftDrive;
	public static RightDriveTrain rightDrive;
	//PID Subsystems
	public static leftDrive leftPID;
	public static rightDrive rightPID;
	public static turnPID turnController;
	
	//Regular Subsystems
	public static DriveTrain drive;
	public static visionSubsystem vision;
	public static GearSubsystem gears;
	public static GearShift shift;
	public static ClimbSubsystem climber;

	public static DoubleSolenoid shifter;
	
	public static void initialize(){
		//Motors
		leftTopDrive = new VictorSP(0);
		leftMiddleDrive = new VictorSP(1);
		leftBottomDrive = new VictorSP(2);
		rightTopDrive = new VictorSP(3);
		rightMiddleDrive = new VictorSP(4);
		rightBottomDrive = new VictorSP(5);
		climbMotor = new VictorSP(6);
		leftDrive=new LeftDriveTrain(leftTopDrive, leftMiddleDrive, leftBottomDrive);
		rightDrive= new RightDriveTrain(rightTopDrive, rightMiddleDrive, rightBottomDrive);
		
		
		shifter = new DoubleSolenoid(4,5);
		//shift = new GearShift(leftDrive, rightDrive, shifter);
		
		//Encoders
		
		/*
		leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
		rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
		
		leftEncoder.reset();
		rightEncoder.reset();
		
		leftEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		rightEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		*/
		
		
		//gyro = new AHRS(SerialPort.Port.kUSB1);
		//gyro.reset();
		
		
		//BTW, k4x tells the encoder to count the rising and falling edges
		
		//Pnumatics
		//compressor = new Compressor();
		
		gearMechanism = new DoubleSolenoid(4, 5);
		driveShift = new DoubleSolenoid(6, 7);
		
		
		//Joysticks
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		auxStick = new Joystick(2);
		
		//PID Subsystems
		/*
		leftPID = new leftDrive();
		rightPID = new rightDrive();
		turnController = new turnPID();
		
		turnController.getPIDController().disable();
		*/
		
		//Subsystems
		//drive = new DriveTrain(leftDrive, rightDrive);
		
		//shift = new GearShift(drive, new DoubleSolenoid(0, 1), new DoubleSolenoid(2, 3), leftStick.getTrigger());
		//drive.addGearShift(shift);
		
		//gears = new GearSubsystem(new VictorSP(3), new VictorSP(4), new DoubleSolenoid(4, 5));
		//climber = new ClimbSubsystem(new VictorSP(2), new DigitalOutput(0), new Relay(0));
		
		//vision = new visionSubsystem("LINKSVision");
		
		System.out.println("Robot is Initialized");
	}
	
}
