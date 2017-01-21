package autonomous;

import org.usfirst.frc.team4468.robot.CMap;

public class Baseline {
	public static void Main() {
		CMap.drive.enablePID();
		//7 ft 9.25 inches
		CMap.leftPID.setSetpoint(100);
		CMap.rightPID.setSetpoint(100);
		System.out.println("Baseline is initialized.");
		if (CMap.drive.onTarget()) {
			System.out.println("Baseline has been broken.");
		}
	}

}
