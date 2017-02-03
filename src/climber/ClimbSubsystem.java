package climber;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Relay.Value;

public class ClimbSubsystem {
	public SpeedController climbMotor;
	public Relay lights;
	public DigitalOutput limitSwitch;
	
	private boolean climbing;
	
	public ClimbSubsystem(SpeedController motor, DigitalOutput limitSwitch1, Relay light){
		climbMotor = motor;
		lights = light;
		limitSwitch = limitSwitch1;
	}
	
	public void main(){
		this.climb(CMap.auxStick.getRawButton(8));
	}
	
	
	//According to Adam, the motor will have a mechanism
	//built in that prevents it from moving backwards
	public void climb(boolean button){
		if(button){
			System.out.println("We have begun to climb");
			climbing = true;
		}
		
		if(climbing){
			if(limitSwitch.get()){
				climbMotor.set(0);
				lights.set(Value.kOn);
			} else {
				climbMotor.set(1);
			}
		}
	}
}
