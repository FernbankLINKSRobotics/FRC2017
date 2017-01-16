package shooter;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class ShooterSubsystem {

	//motor controller for shooter
	public static SpeedController shooter;

	private static boolean beenPressed = false;
	
	public Encoder encoder;
	
	public ShooterSubsystem(SpeedController shooterMotor, Encoder encoder1){
		shooter = shooterMotor;
		encoder = encoder1;
	}
	
	
	public static void set(Joystick stick){
		
		if(stick.getTrigger()){
			if(!beenPressed ){
				if(shooter.get() == 0){
					shooter.set(1);	
				}
				else{
					shooter.set(0);
				}
			}
		}
	}
	
}
