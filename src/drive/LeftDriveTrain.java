package drive;
import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.*;

public class LeftDriveTrain {
	public static VictorSP 
	leftTopDrive,
	leftMiddleDrive,
	leftBottomDrive;
	
	public void initialize() {
		
		System.out.println("Motors have been initialized.");
	}
	
	public LeftDriveTrain(VictorSP motor1, VictorSP motor2, VictorSP motor3){
		leftTopDrive= motor1;
		leftMiddleDrive= motor2;
		leftBottomDrive= motor3;
	}
	
	public void set(double speed) {
		leftTopDrive.set(speed);
		leftMiddleDrive.set(speed);
		leftBottomDrive.set(speed);
	}
	
}
