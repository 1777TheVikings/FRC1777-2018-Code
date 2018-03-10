package frc.team1777.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Encoder;
import frc.team1777.robot.RobotMap;

public class Sensors extends Subsystem
{
	public static Encoder leftEncoder = new Encoder(RobotMap.leftEncoderID[0], RobotMap.leftEncoderID[1], true, Encoder.EncodingType.k4X);
	public static Encoder rightEncoder = new Encoder(RobotMap.rightEncoderID[0], RobotMap.rightEncoderID[1], false, Encoder.EncodingType.k4X);
	// private static PigeonIMU pigeon = new PigeonIMU(RobotMap.pigeonID);
	
	public void initDefaultCommand() {}
	
	public Sensors()
	{
		leftEncoder.setName("Left Encoder");
		leftEncoder.setDistancePerPulse((RobotMap.wheelCircumference / 360) * RobotMap.wheelCircumference);  // in inches
		// leftEncoder.setMinRate(0.01);
		// leftEncoder.setSamplesToAverage(5);
		
		rightEncoder.setName("Right Encoder");
		rightEncoder.setDistancePerPulse((RobotMap.wheelCircumference / 360) * RobotMap.wheelCircumference);  // in inches
	}
	
	/**
	 * Resets the encoders. Called after using {@link Sensors.getDistance()}.
	 */
	public void resetEncoders()
	{
		// leftEncoder.reset();
		rightEncoder.reset();
	}
	
	/**
	 * Sets the Pigeon's yaw to 0. Should be called whenever turning is done or when robot is disabled.
	 */
	public void tarePigeon()
	{
		// pigeon.setYaw(0, 0);
	}
	
	/**
	 * Returns the distance travelled by the robot since the last reset. Extremely inaccurate when turning.
	 * @return The distance travelled, in inches.
	 */
	public double getDistance()
	{
		// return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
		return rightEncoder.getRaw();
	}
	
	/**
	 * Returns the angle of rotation since the Pigeon was last tared.
	 * @return The angle of rotation, in degrees. Positive numbers are clockwise (to the right).
	 */
	public double getRotation()
	{
		double[] gyroData = {0.0, 0.0, 0.0};
		// pigeon.getYawPitchRoll(gyroData);
		return gyroData[0];
	}
}
