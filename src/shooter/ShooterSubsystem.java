package shooter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;

public class ShooterSubsystem {

	//motor controller for shooter
	public  SpeedController shooterMotor;

	private  boolean beenPressed = false;
	
	public Encoder encoder;
	
	public ShooterSubsystem(SpeedController shooterMotor1, Encoder encoder1){
		shooterMotor = shooterMotor1;
		encoder = encoder1;
	}
	
	
	public  void set(Joystick stick){
		if(stick.getTrigger()){
			if(!beenPressed ){
				if(shooterMotor.get() == 0){
					shooterMotor.set(1);	
				}
				else{
					shooterMotor.set(0);
				}
			}
		}
	}
	
	
	
}
