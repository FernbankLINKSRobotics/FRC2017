package org.fernbanklinks.helper;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * 
 * This class allows us to use the Gyro as a PIDSource.
 * 
 * Since reset() does nothing, when this object is created,
 * it sets a zeroAngle to its current angle. When getting the angle,
 * it subtracts what the gyro reads from that zeroGyroAngle.
 *
 */
public class GyroPIDSource implements PIDSource {
	private AHRS gyro;
	private double zeroGyroAngle;
	
	private PIDSourceType type;
	
	/**
	 * Creates the source and sets the zeroGyroAngle.
	 * 
	 * @param gyroscope
	 * The Gyro on our robot.
	 */
	public GyroPIDSource(AHRS gyroscope){
		gyro = gyroscope;
		zeroGyroAngle = gyroscope.getAngle();
		
		type = PIDSourceType.kDisplacement;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		type = pidSource;

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public double pidGet() {
		return gyro.getAngle() - zeroGyroAngle;
	}

}
