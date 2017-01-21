package PIDsub;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ShooterPID extends PIDSubsystem {
	public static final double Kp= .2;
	public static final double Ki= .2;
	public static final double Kd= .2;
	public static final double Kf= .2;
    // Initialize your subsystem here
    public ShooterPID() {
    	super("ShooterPID", Kp, Ki, Kd, Kf);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return CMap.shooter.encoder.getRate();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	CMap.shooter.shooterMotor.set(output);
    }
}
