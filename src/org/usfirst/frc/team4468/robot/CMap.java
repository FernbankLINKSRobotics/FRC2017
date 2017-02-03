package org.usfirst.frc.team4468.robot;
import drive.DriveTrain;
import com.kauailabs.navx.frc.AHRS;
import drive.GearShift;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import gears.GearSubsystem;
import shooter.ShooterSubsystem;
import vision.visionSubsystem;
import PIDsub.*;
import climber.ClimbSubsystem;
public class CMap { 
	public static DriverStation station;
	
	//Encoders
	public static Encoder leftEncoder,
							rightEncoder,
							shooterEncoder;
	
	public static AHRS gyro;
	
	//Joysticks
	public static Joystick leftStick, rightStick, auxStick;
	
	//Pnumatics
	public static Compressor compressor;
	
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
	public static ClimbSubsystem climber;
	
	public static void initialize(){
		station = DriverStation.getInstance();
		
		//Encoders
		
		leftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
		rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
		shooterEncoder = new Encoder(4, 5, false, EncodingType.k4X);
		
		leftEncoder.reset();
		rightEncoder.reset();
		shooterEncoder.reset();
		
		leftEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		rightEncoder.setDistancePerPulse(drive.lowDistancePerPulse);
		
		gyro = new AHRS(SerialPort.Port.kUSB1);
		gyro.reset();
		
		
		//BTW, k4x tells the encoder to count the rising and falling edges
		
		//Pnumatics
		compressor = new Compressor();
		
		//Joysticks
		leftStick = new Joystick(0);
		rightStick = new Joystick(1);
		auxStick = new Joystick(2);
		
		//PID Subsystems
		//leftPID = new leftDrive();
		//rightPID = new rightDrive();
		//turnController = new turnPID();
		
		turnController.getPIDController().disable();
		
		//Subsystems
		drive = new DriveTrain(new VictorSP(0), new VictorSP(1));
		//shift = new GearShift(drive, new DoubleSolenoid(0, 1), new DoubleSolenoid(2, 3), leftStick.getTrigger());
		//drive.addGearShift(shift);
		
		//gears = new GearSubsystem(new VictorSP(3), new VictorSP(4), new DoubleSolenoid(4, 5));
		//climber = new ClimbSubsystem(new VictorSP(2), new DigitalOutput(0), new Relay(0));
		
		vision = new visionSubsystem("LINKSVision");
		
		System.out.println("Robot is Initialized");
	}
	
}
