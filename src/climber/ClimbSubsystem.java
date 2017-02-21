package climber;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Relay.Value;

public class ClimbSubsystem {
	public VictorSP climbMotor;
	
	private boolean climbing;
	
	public ClimbSubsystem(VictorSP motor){
		climbMotor = motor;
	}
	
	public void main(){
		this.climb(CMap.auxStick.getRawButton(8));
	}
	
	
	//According to Adam, the motor will have a mechanism
	//built in that prevents it from moving backwards
	public void climb(boolean button){
		if(button){
			climbMotor.set(1);
		} else {
			climbMotor.set(0);
		}
	}
}
