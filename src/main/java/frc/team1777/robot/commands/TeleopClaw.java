package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class TeleopClaw extends Command
{
	public TeleopClaw()
	{
		requires(Robot.claw);
	}
	
	public void initialize() {}
	
	public void execute()
	{
		Robot.claw.teleopControl();
	}
	
	protected boolean isFinished()
	{
		return false;  // default commands should be interrupted, not stopped
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
