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

public class CMap { 
	//Encoders
	public static Encoder leftEncoder,
							rightEncoder;
	
	public static AHRS gyro;
	
	//Motors
	public static VictorSP leftDrive, rightDrive,
						   climbMotor, leftGearMotor,
						   rightGearMotor;
	
	//Joysticks
	public static Joystick leftStick, rightStick, auxStick;
	
	//Pnumatics
	public static Compressor compressor;
	
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
	
	public static void initialize(){
		//Motors
		leftDrive = new VictorSP(0);
		rightDrive = new VictorSP(1);
		climbMotor = new VictorSP(2);
		leftGearMotor = new VictorSP(3);
		rightGearMotor = new VictorSP(4);
		
		//Encoders
		
		leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
		rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
		
		leftEncoder.reset();
		rightEncoder.reset();
		
		leftEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		rightEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		
		
		
		gyro = new AHRS(SerialPort.Port.kUSB1);
		gyro.reset();
		
		
		//BTW, k4x tells the encoder to count the rising and falling edges
		
		//Pnumatics
		//compressor = new Compressor();
		
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
		drive = new DriveTrain(leftDrive, rightDrive);
		
		//shift = new GearShift(drive, new DoubleSolenoid(0, 1), new DoubleSolenoid(2, 3), leftStick.getTrigger());
		//drive.addGearShift(shift);
		
		//gears = new GearSubsystem(new VictorSP(3), new VictorSP(4), new DoubleSolenoid(4, 5));
		//climber = new ClimbSubsystem(new VictorSP(2), new DigitalOutput(0), new Relay(0));
		
		vision = new visionSubsystem("LINKSVision");
		
		System.out.println("Robot is Initialized");
	}
	
}
