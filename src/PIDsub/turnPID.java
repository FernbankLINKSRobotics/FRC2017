package PIDsub;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team4468.robot.*;
/**
 *
 */
public class turnPID extends PIDSubsystem {
	private static final double Kp = .2;
	private static final double Ki = 0;
	private static final double Kd = 0;
    // Initialize your subsystem here
    public turnPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("turnPID", Kp, Ki, Kd);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
    	
    	return 0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	CMap.drive.turnSet(output);
    }
}
