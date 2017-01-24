package vision;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * This class can be used to generate the offsets of a camera. This works best
 * when you are using a coprocessor that is sending data to the RIO over the
 * Network Tables interface.
 * 
 *
 */
public class visionSubsystem {
	private double idealX, idealY;
	private NetworkTable visionTable;
	public boolean connectedToRaspberry = false;
	
	/*
	 * These are the values we will use to convert the camera coordinates
	 * to gyro coordinates. First, we need the horizontal FOV which I found
	 * online after some research. Next, we need to figure out how many degrees
	 * are covered in each pixel.
	 */
	public static final double horizontalFOV = 62.8;
	public static final double columns = 1280;
	public static final double rows = 720;
	public static final double columnDegreesPerPixel = horizontalFOV / columns;
	public static final double rowDegreesPerPixel = horizontalFOV / rows;
	public static final double[] centerOfCamera = {(columns / 2), (rows / 2)};
	
	/**
	 * Constructor for the visonSubsystem class
	 * 
	 * @param goodX
	 * The ideal center X position of the target.
	 * 
	 * @param goodY
	 * The ideal center Y position of the target.
	 * 
	 * @param tableName
	 * The name of the networkTable that will house the information.
	 * 
	 */
	public visionSubsystem(double goodX, double goodY, String tableName){
		idealX = goodX;
		idealY = goodY;
		visionTable = NetworkTable.getTable(tableName);
		connectedToRaspberry = visionTable.getBoolean("Online?", false);
		while(!connectedToRaspberry){
			connectedToRaspberry = visionTable.getBoolean("Online?", false);
		}
		System.out.println("Connected to Raspberry Pi.");
	}
	
	/**
	 * Constructor for the visonSubsystem class. This constructor will set the
	 * target coordinates to the center of the camera.
	 * 
	 * @param tableName
	 * The name of the networkTable that will house the information.
	 */
	public visionSubsystem(String tableName){
		idealX = centerOfCamera[0];
		idealY = centerOfCamera[1];
		visionTable = NetworkTable.getTable(tableName);
		connectedToRaspberry = visionTable.getBoolean("Online?", false);
		while(!connectedToRaspberry){
			connectedToRaspberry = visionTable.getBoolean("Online?", false);
		}
		System.out.println("Connected to Raspberry Pi.");
	}
	
	/**
	 * This method will find the offsets of the target from the current
	 * data coming from the coprocessor. 
	 * 
	 * @return
	 * The offsets of the targets in an array. The first index is the x-offset
	 * while the second is the y-offset. If the x offset is positive, the target
	 * is too far to the right. If the y-offset is positive, the target is too far down.
	 */
	public double[] getOffsets(){
		double[] centers = this.getCenters(),
				returnArray = new double[2];
		returnArray[0] = centers[0] - idealX;
		returnArray[1] = centers[1] - idealY;
		
		return returnArray;
	}
	
	/**
	 * This method will find the centers of the target on the picture.
	 * 
	 * @return
	 * The center coordinates of the target in a coordinate array (x, y).
	 */
	public double[] getCenters(){
		double[] returnArray = new double[2];
		returnArray[0] = visionTable.getNumber("Center X", 50);
		returnArray[1] = visionTable.getNumber("Center Y", 50);
		return returnArray;
	}
	
	/**
	 * This method will return the angle that the gyro would need to 
	 * turn to in order to line up with the target.
	 * 
	 * @param offsets
	 * The offsets from the current target
	 * @return
	 * The target angles in an array of (x, y). This should be added to the current
	 * PID setpoint.
	 */
	public double getGyroAngle(){
		double xOffset = this.getOffsets()[0];
		double targetX = xOffset * columnDegreesPerPixel;
		//double targetY = offsets[1] * rowDegreesPerPixel;
		return targetX;
	}
	
	public boolean checkConnection(){
		
		return (Boolean) null;
	}
}
