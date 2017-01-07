package drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveTrain {
	/**
	 * The motor controller responsible for the left drive motor.
	 */
	public SpeedController leftDrive;
	
	/**
	 * The motor controller responsible for the right drive motor.
	 */
	public SpeedController rightDrive;
	
	/**
	 * Public Constructor for the Drivetrain class. This initializes
	 * the values of the left and right drive motors. The motors are
	 * stored as speed controller objects, so we don't have to have
	 * a constructor for each type of motor controller.
	 * 
	 * @param leftDriveMotor
	 * The motor responsible for the left side of the drivetrain.
	 * 
	 * @param rightDriveMotor
	 * The motor responsible for the right side of the drivetrain.
	 */
	public DriveTrain(SpeedController leftDriveMotor, SpeedController rightDriveMotor){
		leftDrive = leftDriveMotor;
		rightDrive = rightDriveMotor;
	}
	
	/**
	 * This method sets the inverted state of one of the motors. Right now, that
	 * can be called by calling the instance of the motor to be inverted.
	 * 
	 * @param motor
	 * The motor to be inverted.
	 * @param inverted
	 * The inverted state to be set to. True will invert the motor, False will keep the motor in normal position.
	 */
	public void setInverted(SpeedController motor, boolean inverted){
		motor.setInverted(true);
	}
	
	
	/**
	 * This method sets both of the motors to the same value. This can be used
	 * when in autonomous and you want to set both motors to the same when you have
	 * one drivetrain PID class.
	 * 
	 * @param value
	 * The PWM value to set the motor to.
	 */
	public void set(double value){
		leftDrive.set(value);
		rightDrive.set(value);
	}
	
	/**
	 * This method can be used to set both of the motors to different values. This is
	 * highly useful when the robot is in teleoperated mode.
	 * 
	 * @param leftValue
	 * The value that the left motor should be set to.
	 * @param rightValue
	 * The value that the right motor should be set to.
	 */
	public void set(double leftValue, double rightValue){
		leftDrive.set(leftValue);
		rightDrive.set(rightValue);
	}
	
	/**
	 * This method can be used to set the motor values with joysticks. This
	 * calls the getY() function, so any inverting should be done before
	 * this function is called.
	 * 
	 * @param leftStick
	 * The joystick responsible for the left side of the drivetrain.
	 * @param rightStick
	 * The joystick responsible for the right side of the drivetrain.
	 */
	public void set(Joystick leftStick, Joystick rightStick){
		leftDrive.set(leftStick.getY());
		rightDrive.set(rightStick.getY());
	}
	
	/**
	 * This method can be used in conjunction with drivetrain PID systems.
	 * It will set the values of the motors to opposites to ensure turning.
	 * @param value
	 */
	public void turnSet(double value){
		leftDrive.set(value);
		rightDrive.set(-value);
	}
}
