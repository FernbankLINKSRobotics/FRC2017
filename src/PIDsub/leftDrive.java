package PIDsub;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class leftDrive extends PIDSubsystem {
	private static final double Kp = .2;
	private static final double Ki = 0;
	private static final double Kd = 0;

    // Initialize your subsystem here
    public leftDrive() {
    	super("leftDrive", Kp, Ki, Kd);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	//System.out.println("LEFT ENCODER DISTANCE: " + CMap.leftEncoder.getDistance());
    	//return -CMap.leftEncoder.getDistance();
    	return 0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	CMap.leftDrive.set(output);
    }
}
