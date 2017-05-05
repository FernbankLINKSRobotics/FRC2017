package PIDsub;

import org.fernbanklinks.main.*;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
/**
 *
 */
public class TurnController extends PIDSubsystem {
	private static final double Kp = .05;
	private static final double Ki = 0;
	private static final double Kd = 0;
    // Initialize your subsystem here
    public TurnController() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("turnPID", Kp, Ki, Kd);
    }
    
    
    protected double returnPIDInput() {
    	
    	//System.out.println(CMap.gyro.getAngle())
    	
    	System.out.println(CMap.gyro.getAngle() - CMap.zeroGyroAngle);
    	return CMap.gyro.getAngle() - CMap.zeroGyroAngle;
    	
    	//return 0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	//CMap.drive.turnSet(output);
    	
    	System.out.println(output);
    	//CMap.rightDrive.set(output);
    	
    	CMap.leftDrive.set(-output);
    }


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
