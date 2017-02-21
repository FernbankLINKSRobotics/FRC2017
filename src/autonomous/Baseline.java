package autonomous;

import org.usfirst.frc.team4468.robot.CMap;

public class Baseline {
	public static void main() {
		//7 ft 9.25 inches
		CMap.leftPID.setSetpoint(100);
		CMap.rightPID.setSetpoint(100);
		System.out.println("Baseline is initialized.");
	}

}
