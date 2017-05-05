package org.fernbanklinks.subsystems;

import org.fernbanklinks.helper.GyroPIDSource;
import org.fernbanklinks.helper.MotorArray;

import com.kauailabs.navx.frc.AHRS;

import PIDsub.TurnController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * This subsystem represents the entire drivetrain of the robot. Assuming that we use a tank drive,
 * the left and right sides are represented as MotorArrays.
 * 
 * This subsystem implements the PIDOutput interface so we can use it as the output for the turnController
 * PID Controller. The constructor requires 5 parameters, but we can use null for any devices we don't use.
 */
public class DriveTrain implements PIDOutput{
	public MotorArray leftDriveTrain, rightDriveTrain;
	public Encoder leftEncoder, rightEncoder;
	
	public GyroPIDSource gyro;
	
	public PIDController turnController, leftPIDController, rightPIDController;
	
	/**
	 * This constructor is used to actually create our drivetrain. If we aren't using one of these devices,
	 * supply null as the parameter.
	 * 
	 * @param leftDrive
	 * This is the MotorArray representing the left side of the drivetrain.
	 * 
	 * @param rightDrive
	 * This is the MotorArray representing the right side of the drivetrain.
	 * 
	 * @param navx
	 * This is the gyro that would be mounted to our drivetrain.
	 * 
	 * @param leftDriveEncoder
	 * This is the encoder mounted to the left side of the drivetrain.
	 * 
	 * @param rightDriveEncoder
	 * This is the encoder mounted to the right side of our drivetrain.
	 */
	public DriveTrain(MotorArray leftDrive, MotorArray rightDrive, AHRS navx, 
			Encoder leftDriveEncoder, Encoder rightDriveEncoder){
		
		leftDriveTrain = leftDrive;
		rightDriveTrain = rightDrive;
		
		if(navx != null){
			gyro = new GyroPIDSource(navx);
			
			turnController = new PIDController(.05, 0, 0, gyro, this);
			turnController.disable();
		}
		
		if(leftDriveEncoder != null && rightDriveEncoder != null){
			leftEncoder = leftDriveEncoder;
			rightEncoder = rightDriveEncoder;
			
			leftPIDController = new PIDController(0.2, 0, 0, leftEncoder, leftDriveTrain);
			rightPIDController = new PIDController(0.2, 0, 0, rightEncoder, rightDriveTrain);
		}
	}
	
	public void set(double leftSet, double rightSet) {
		leftDriveTrain.set(leftSet);
		rightDriveTrain.set(rightSet);
	}
	
	public void pidWrite(double output){
		this.set(output, -output);
	}
	
	
	
	
	
	
	
}
