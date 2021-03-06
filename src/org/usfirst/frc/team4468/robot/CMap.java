package org.usfirst.frc.team4468.robot;

import com.kauailabs.navx.frc.AHRS;

import drive.DriveTrain;
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
	public final static double wheelDiameter = 4;
	
	public static String wheelToControl = "Right";
	
	//Spin the Wheel, how many pulses are returned.
	//Put that number in here.
	public final static double pulsePerRevolution = 1800; //Low Gear
	public final static double lowDistancePerPulse = (1/pulsePerRevolution) * Math.PI * wheelDiameter;
	
	
	public static double zeroGyroAngle;
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
						    climbMotor1,
						    climbMotor2,
						    intakeMotor;
	
	//Joysticks
	public static Joystick leftStick, rightStick;
	
	//Pnematics
	public static DoubleSolenoid gearMechanism,
								 driveShift,
								 gearIntake;
	
	//Drive Train Subsystems
	public static LeftDriveTrain leftDrive;
	public static RightDriveTrain rightDrive;
	public static DriveTrain drive;
	
	//PID Subsystems
	public static leftDrive leftPID;
	public static rightDrive rightPID;
	public static turnPID turnController;
	
	//Regular Subsystems
	public static visionSubsystem vision;
	public static GearSubsystem gears;
	public static GearShift shift;
	public static ClimbSubsystem climber;
	
	public static CameraServer server;
	
	//Timers
	public static Timer autoTimer;
	public static Timer teleopTimer;

	public static void initialize(){
		//Timers
		autoTimer = new Timer();
		teleopTimer = new Timer();
		
		//Motors
		leftTopDrive = new VictorSP(0);
		leftMiddleDrive = new VictorSP(1);
		leftBottomDrive = new VictorSP(2);
		rightTopDrive = new VictorSP(3);
		rightMiddleDrive = new VictorSP(4);
		rightBottomDrive = new VictorSP(5);
		climbMotor1 = new VictorSP(6);
		climbMotor2 = new VictorSP(7);
		intakeMotor = new VictorSP(8);
		
		//Invert Left Side of Motors to Make PIDs Easier
		leftTopDrive.setInverted(true);
		leftMiddleDrive.setInverted(true);
		leftBottomDrive.setInverted(true);
		
		leftDrive = new LeftDriveTrain(leftTopDrive, leftMiddleDrive, leftBottomDrive);
		rightDrive = new RightDriveTrain(rightTopDrive, rightMiddleDrive, rightBottomDrive);
		drive= new DriveTrain(leftDrive, rightDrive);
		
		//Encoders
		/*
		leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
		rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
		
		
		leftEncoder.reset();
		rightEncoder.reset();
		
		leftEncoder.setReverseDirection(true);
		
		leftEncoder.setDistancePerPulse(lowDistancePerPulse);
		rightEncoder.setDistancePerPulse(lowDistancePerPulse);
		*/
	
		gyro = new AHRS(SerialPort.Port.kUSB1);
		gyro.reset();
		zeroGyroAngle = gyro.getAngle();
	
		
		//Pnumatics
		
		gearMechanism = new DoubleSolenoid(4, 5);
		driveShift = new DoubleSolenoid(6, 7);
		gearIntake = new DoubleSolenoid(0, 1);
		
		
		//Joysticks
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		
		//PID Subsystems
		leftPID = new leftDrive();
		rightPID = new rightDrive();

		
		leftPID.getPIDController().disable();
		rightPID.getPIDController().disable();
		
		leftPID.getPIDController().setOutputRange(-.6, .6);
		rightPID.getPIDController().setOutputRange(-.6, .6);
		
		turnController = new turnPID();		
		turnController.getPIDController().setOutputRange(-1, 1);

		turnController.getPIDController().disable();
		
		//vision = new visionSubsystem("LINKSVision");
		leftDrive = new LeftDriveTrain(leftTopDrive, leftMiddleDrive, leftBottomDrive);
		rightDrive = new RightDriveTrain(rightTopDrive, rightMiddleDrive, rightBottomDrive);
		shift = new GearShift(driveShift);
		climber = new ClimbSubsystem(climbMotor1, climbMotor2);
		gears = new GearSubsystem(intakeMotor, gearMechanism, gearIntake);
		
		//server = CameraServer.getInstance();
		//server.startAutomaticCapture();
		
		System.out.println("Robot is Initialized");
	}
	
}
