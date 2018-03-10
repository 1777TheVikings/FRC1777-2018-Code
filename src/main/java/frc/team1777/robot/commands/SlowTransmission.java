package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class SlowTransmission extends Command
{
	public SlowTransmission()
	{
		requires(Robot.drive);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.drive.slowTransmission();
	}
	
	protected boolean isFinished()
	{
		// One-shot commands should finish after one cycle
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
