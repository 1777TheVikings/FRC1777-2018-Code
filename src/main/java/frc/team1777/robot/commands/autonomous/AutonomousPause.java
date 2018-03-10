package frc.team1777.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class AutonomousPause extends Command
{
	/**
	 * Pauses execution for a specified amount of time without moving. Useful for
	 * ensuring that the robot is stopped.
	 * 
	 * @param time The amount of time to stop, in seconds.
	 */
	public AutonomousPause(double time)
	{
		requires(Robot.drive);
		setTimeout(time);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.drive.autonomousDrive(0, 0);
	}
	
	protected boolean isFinished()
	{
		return isTimedOut();
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
