package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class TeleopDrive extends Command
{
	public TeleopDrive()
	{
		requires(Robot.drive);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.drive.teleopDrive();
	}
	
	protected boolean isFinished()
	{
		// This command never finishes on its own, since it's the
		// default behavior for the subsystem.
		return false;
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
