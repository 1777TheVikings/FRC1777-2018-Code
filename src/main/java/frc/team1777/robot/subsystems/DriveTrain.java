package frc.team1777.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1777.robot.Robot;
import frc.team1777.robot.RobotMap;
import frc.team1777.robot.commands.TeleopDrive;

public class DriveTrain extends Subsystem
{
	private static WPI_TalonSRX driveLeftFrontMotor = new WPI_TalonSRX(RobotMap.leftFrontMotor);
	private static WPI_TalonSRX driveLeftRearMotor = new WPI_TalonSRX(RobotMap.leftRearMotor);
	private static SpeedControllerGroup driveLeft = new SpeedControllerGroup(driveLeftFrontMotor, driveLeftRearMotor);
	private static WPI_TalonSRX driveRightFrontMotor = new WPI_TalonSRX(RobotMap.rightFrontMotor);
	private static WPI_TalonSRX driveRightRearMotor = new WPI_TalonSRX(RobotMap.rightRearMotor);
	private static SpeedControllerGroup driveRight = new SpeedControllerGroup(driveRightFrontMotor, driveRightRearMotor);
	private static DifferentialDrive drive = new DifferentialDrive(driveRight, driveLeft);
	
	private static DoubleSolenoid transSolenoid = new DoubleSolenoid(RobotMap.transSolenoidID[0], RobotMap.transSolenoidID[1]);
	
	public static enum autonomousDriveMode {drive, rotation};
	
	public DriveTrain()
	{
		driveLeftFrontMotor.configOpenloopRamp(0, 0);
		driveLeftRearMotor.configOpenloopRamp(0, 0);
		driveRightFrontMotor.configOpenloopRamp(0, 0);
		driveRightRearMotor.configOpenloopRamp(0, 0);
	}
	
	public void initDefaultCommand()
	{
		// always allow user control whenever we're not doing something else
		setDefaultCommand(new TeleopDrive());
	}
	
	
	public void teleopDrive()
	{
		if (SmartDashboard.getBoolean("kidMode", false))
		{
			drive.arcadeDrive(Robot.oi.getX() * .5, Robot.oi.getRotation() * .5);
			slowTransmission();
		}
		else
		{
			// joystick values are automatically clamped to [-1, 1]
			drive.arcadeDrive(Robot.oi.getX(), Robot.oi.getRotation());
			if (Robot.oi.getTransmission())
			{
				fastTransmission();
			}
			else
			{
				slowTransmission();
			}
		}
	}
	
	
	public void autonomousDrive(double xSpeed, double zRotation)
	{
		drive.arcadeDrive(xSpeed, zRotation);
	}
	
	/**
	 * Moves the robot using a P loop. Sensors should be zeroed/tared before and
	 * after done calling this.
	 * 
	 * @param moveDistance The distance to move, in inches.
	 * 
	 * @return An array of the format [movement error, angle error].
	 */
	public double[] autonomousLinearP(double moveDistance)
	{
		double angleError = -Robot.sensors.getRotation();
		double angleOutput = RobotMap.pGainFactorTurning * angleError;
		
		double movementError = moveDistance - Robot.sensors.getDistance();
		double movementOutput = RobotMap.pGainFactorMovement * movementError;
		
		Robot.drive.autonomousDrive(movementOutput, angleOutput);
		
		double[] output = {movementError, angleError};
		
		return output;
	}
	
	/**
	 * Turns the robot using a P loop. Sensors should be zeroed/tared before
	 * and after done calling this.
	 * 
	 * @param turnAngle
	 * 
	 * @return The angle error, as a double.
	 */
	public double autonomousAngleP(double turnAngle)
	{
		double angleError = turnAngle - Robot.sensors.getRotation();
		double angleOutput = RobotMap.pGainFactorTurning * angleError;
		
		Robot.drive.autonomousDrive(0, angleOutput);
		
		return angleError;
	}
	
	public void slowTransmission()
	{
		transSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void fastTransmission()
	{
		transSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
}
