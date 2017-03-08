package autonomous;

import org.usfirst.frc.team4468.robot.CMap;

public class Baseline {
	public static void main() {
		//7 ft 9.25 inches
		//Actually, a little further to the area to get gears.
		CMap.drive.PIDsetSetpoint(330, 330);
		System.out.println("Baseline is initialized.");
	}

}
