package climber;

import org.usfirst.frc.team4468.robot.CMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Relay.Value;

public class ClimbSubsystem {
	public VictorSP climbMotor, climbMotor2;
	
	private boolean climbing;
	
	public ClimbSubsystem(VictorSP motor, VictorSP motor2){
		climbMotor = motor;
		climbMotor2 = motor2;
	}
	
	
	//According to Adam, the motor will have a mechanism
	//built in that prevents it from moving backwards
	public void climb(boolean button1, boolean button2){
		if(button1){
			climbMotor.set(-1);
			climbMotor2.set(-1);
		} else if(button2){
			climbMotor.set(-0.5);
			climbMotor2.set(-0.5);
		}
		else {
			climbMotor.set(0);
			climbMotor2.set(0);
		}
	}
	
	}
}
