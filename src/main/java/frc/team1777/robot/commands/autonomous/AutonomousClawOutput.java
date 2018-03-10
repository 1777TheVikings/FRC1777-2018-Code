package frc.team1777.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class AutonomousClawOutput extends Command
{
	public AutonomousClawOutput()
	{
		requires(Robot.claw);
		setTimeout(1);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.claw.control(-1, false);
	}
	
	protected boolean isFinished()
	{
		return isTimedOut();
	}
	
	protected void end()
	{
		Robot.claw.control(0, false);
	}
	
	protected void interrupted() { end(); }
}
