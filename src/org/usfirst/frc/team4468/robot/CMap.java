package org.usfirst.frc.team4468.robot;
import drive.DriveTrain;
import com.kauailabs.navx.frc.AHRS;
import drive.GearShift;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.VictorSP;
import gears.GearSubsystem;
import shooter.ShooterSubsystem;
import vision.visionSubsystem;
import PIDsub.*;
public class CMap {
	//Motors
	public static VictorSP leftDrive,
							rightDrive,
							shooterMotor;
	
	//Encoders
	public static Encoder leftEncoder,
							rightEncoder,
							shooterEncoder;
	
	public static AHRS gyro;
	
	//Joysticks
	public static Joystick leftStick, rightStick, auxStick;
	
	//Pnumatics
	public static Compressor compressor;
	public static DoubleSolenoid leftShift,
								rightShift;
	
	//PID Subsystems
	public static leftDrive leftPID;
	public static rightDrive rightPID;
	public static turnPID turnController;
	public static ShooterPID shooterPID;
	
	//Regular Subsystems
	public static DriveTrain drive;
	public static visionSubsystem vision;
	public static ShooterSubsystem shooter;
	public static GearSubsystem gears;
	public static GearShift shift;
	
	public static void initialize(){
		station = DriverStation.getInstance();
		
		//Motors
		leftDrive = new VictorSP(0);
		rightDrive = new VictorSP(1);
		shooterMotor = new VictorSP(2);
		
		//Encoders
		leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
		rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
		shooterEncoder = new Encoder(4, 5, false, EncodingType.k4X);
		
		leftEncoder.reset();
		rightEncoder.reset();
		shooterEncoder.reset();
		
		leftEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		rightEncoder.setDistancePerPulse(drive.highDistancePerPulse);
		
		gyro = new AHRS(SerialPort.Port.kUSB1);
		gyro.reset();
		
		
		//BTW, k4x tells the encoder to count the rising and falling edges
		
		//Pnumatics
		compressor = new Compressor();
		leftShift = new DoubleSolenoid(0, 1);
		rightShift = new DoubleSolenoid(2, 3);
		
		
		//Joysticks
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		auxStick = new Joystick(2);
		
		//PID Subsystems
		leftPID = new leftDrive();
		rightPID = new rightDrive();
		turnController = new turnPID();
		shooterPID = new ShooterPID();
		
		turnController.getPIDController().disable();
		
		//Subsystems
		drive = new DriveTrain(leftDrive, rightDrive);
		shift = new GearShift(drive, leftShift, rightShift, rightStick);
		drive.addGearShift(shift);
		
		shooter = new ShooterSubsystem(shooterMotor, shooterEncoder);
		
		vision = new visionSubsystem("LINKSVision");
		
		System.out.println("Robot is Initialize");
	}
}
