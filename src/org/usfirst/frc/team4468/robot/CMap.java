package org.usfirst.frc.team4468.robot;

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
	public final double wheelDiameter = 4;
	
	//Need to find these values either through trial-and-error or complex math.
	public final double lowPulsePerRevolution = 5050;
	public final double highPulsePerRevolution = 4040;
	
	public final double lowDistancePerPulse = (1/lowPulsePerRevolution) * Math.PI * wheelDiameter;
	public final double highDistancePerPulse = (1/highPulsePerRevolution) * Math.PI * wheelDiameter;
	
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
	
	//Pnematics
	public static DoubleSolenoid gearMechanism,
								 driveShift;
	
	//Drive Train Subsystems
	public static LeftDriveTrain leftDrive;
	public static RightDriveTrain rightDrive;
	
	//PID Subsystems
	public static leftDrive leftPID;
	public static rightDrive rightPID;
	public static turnPID turnController;
	
	//Regular Subsystems
	public static visionSubsystem vision;
	public static GearSubsystem gears;
	public static GearShift shift;
	public static ClimbSubsystem climber;

	public static void initialize(){
		//Motors
		leftTopDrive = new VictorSP(0);
		leftMiddleDrive = new VictorSP(1);
		leftBottomDrive = new VictorSP(2);
		rightTopDrive = new VictorSP(3);
		rightMiddleDrive = new VictorSP(4);
		rightBottomDrive = new VictorSP(5);
		climbMotor = new VictorSP(6);
		
		//Invert Left Side of Motors to Make PIDs Easier
		leftTopDrive.setInverted(true);
		leftMiddleDrive.setInverted(true);
		leftBottomDrive.setInverted(true);
		
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
		
	
		//gyro = new AHRS(SerialPort.Port.kUSB1);
		//gyro.reset();
		
		//Pnumatics
		gearMechanism = new DoubleSolenoid(4, 5);
		driveShift = new DoubleSolenoid(6, 7);
		
		
		//Joysticks
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		auxStick = new Joystick(2);
		
		//PID Subsystems
		leftPID = new leftDrive();
		rightPID = new rightDrive();
		turnController = new turnPID();
		
		turnController.getPIDController().disable();
		
		//vision = new visionSubsystem("LINKSVision");
		leftDrive = new LeftDriveTrain(leftTopDrive, leftMiddleDrive, leftBottomDrive);
		rightDrive = new RightDriveTrain(rightTopDrive, rightMiddleDrive, rightBottomDrive);
		shift = new GearShift(driveShift);
		climber = new ClimbSubsystem(climbMotor);
		gears = new GearSubsystem(gearMechanism);
		
		System.out.println("Robot is Initialized");
	}
	
}
