package frc.team1777.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;
import frc.team1777.robot.RobotMap;

public class AutonomousMoveAngular extends Command
{
	private double rotationAngle;
	private double angleError;
	
	/**
	 * Turns the robot to the specified angle using a P loop.
	 * 
	 * @param angle The angle to turn to, in degrees. Positive angles are clockwise.
	 */
	public AutonomousMoveAngular(double angle)
	{
		requires(Robot.drive);
		requires(Robot.sensors);
		
		rotationAngle = angle;
	}
	
	protected void initialize()
	{
		Robot.sensors.tarePigeon();
		Robot.sensors.resetEncoders();
	}
	
	protected void execute()
	{
		Robot.drive.autonomousAngleP(rotationAngle);
	}
	
	protected boolean isFinished()
	{
		// angleError and movementError will never be 0, so we need to check if they're close enough.
		return (Math.abs(angleError) <= RobotMap.pZeroRange);
	}
	
	protected void end()
	{
		Robot.drive.autonomousDrive(0, 0);
		Robot.sensors.tarePigeon();
		Robot.sensors.resetEncoders();
	}
	
	protected void interrupted()
	{
		end();
	}
}
