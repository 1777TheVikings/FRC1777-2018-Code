package frc.team1777.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;
import frc.team1777.robot.RobotMap;

public class AutonomousMoveLinear extends Command
{
	private double moveDistance;
	private double movementError;
	
	private double angleError;
	
	/**
	 * Moves the robot in a straight line using a P loop.
	 * 
	 * @param distance The distance to move to, in inches.
	 */
	public AutonomousMoveLinear(double distance)
	{
		requires(Robot.drive);
		requires(Robot.sensors);
		
		moveDistance = distance;
	}
	
	protected void initialize()
	{
		Robot.sensors.tarePigeon();
		Robot.sensors.resetEncoders();
	}
	
	protected void execute()
	{
		double[] input = Robot.drive.autonomousLinearP(moveDistance);
		movementError = input[0];
		angleError = input[1];
	}
	
	protected boolean isFinished()
	{
		// angleError and movementError will never be 0, so we need to check if they're close enough.
		return (Math.abs(angleError) <= RobotMap.pZeroRange) && (Math.abs(movementError) <= RobotMap.pZeroRange);
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
