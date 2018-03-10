package frc.team1777.robot.commands.autonomous.competition;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class AutonomousCrossLine extends Command
{
	public AutonomousCrossLine()
	{
		setTimeout(1.75);
		requires(Robot.drive);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.drive.autonomousDrive(0.8, 0);
	}
	
	protected boolean isFinished()
	{
		return isTimedOut();
	}
	
	protected void end()
	{
		Robot.drive.autonomousDrive(0, 0);
	}
	
	protected void interrupted()
	{
		end();
	}
}
