package org.fernbanklinks.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class VisionThread extends Thread {
	private double idealX, idealY;
	
	private double xOffset, yOffset;
	private double centerX, centerY; //Centers of Target from Camera WHAT THE CAMERA SEES
	
	private double gyroAngle;
	
	private NetworkTable table;
	//VALUES FOR GYRO ANGLE CALCULATION
	public static final double horizontalFOV = 0; //NEED TO UPDATE
	public static final double columns = 1280;
	public static final double rows = 720;
	public static final double columnDegreesPerPixel = horizontalFOV / columns;
	//public static final double rowDegreesPerPixel = horizontalFOV / rows;
	public static final double[] centerOfCamera = {(columns / 2), (rows / 2)};
	
	
	
	/**
	 * This constructor user-defines the ideal X and Y coordinates of the target.
	 * 
	 * @param goodX
	 * The x-coordinate of a lined-up target.
	 * @param goodY
	 * The y-coordinate of a lined-up target.
	 * 
	 * @param tableName
	 * The name of the networkTable we are looking for.
	 */
	public VisionThread(double goodX, double goodY, String tableName){
		idealX = goodX;
		idealY = goodY;
		table = NetworkTable.getTable(tableName);
		
		Timer startUpTimer = new Timer();
		startUpTimer.start();
		
		DriverStation.reportWarning("Trying to connect to Pi.....", false);
		while(!table.getBoolean("Online?", false)){
			//Insert a Break that would be joystick-controlled
		}
		DriverStation.reportWarning("SUCCESFULLY CONNECTED", false);
	}
	
	/**
	 * This constructor sets the centerX and centerY coordinates to be the center of the camera.
	 * 
	 * @param tableName
	 * The name of the networkTable we are looking for.
	 */
	public VisionThread(String tableName){
		this(centerOfCamera[0], centerOfCamera[1], tableName);
	}
	
	/**
	 * This will get the x and y offsets from the pi.
	 */
	public void run(){
		while(!this.isInterrupted()){
			//The Double.MAX_VALUE should NEVER BE encountered by the Pi
			centerX = table.getNumber("Center X", Double.MAX_VALUE);
			centerY = table.getNumber("Center Y", Double.MAX_VALUE);
			
			//This means we aren't connected to the pi and there's a problem.
			if(centerX == Double.MAX_VALUE || centerY == Double.MAX_VALUE){
				DriverStation.getInstance().reportError("NOT GETTING VALUES FROM PI", false);
			} else {
				xOffset = idealX - centerX;
				yOffset = idealY - centerY;
				
				gyroAngle = xOffset * columnDegreesPerPixel;
			}
		}
	}
	
	/**
	 * This interrupts the thread and shuts down the pi.
	 */
	public void interrupt(){ //Disables the Pi & Stops the Thread
		super.interrupt(); //Call the super method so it can actually stop the thread.
		table.putBoolean("Shutdown", true); //Shuts Down Pi
	}
	
	/**
	 * This returns the angle to be added to our gyroscope.
	 * @return
	 * Gyro Angle as a double.
	 */
	public double getGyroAngle(){ 
		return this.gyroAngle; 
	}
	
	
}
