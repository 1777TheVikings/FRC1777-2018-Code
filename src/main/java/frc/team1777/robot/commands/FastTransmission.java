package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class FastTransmission extends Command
{
	public FastTransmission()
	{
		requires(Robot.drive);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.drive.fastTransmission();
	}
	
	protected boolean isFinished()
	{
		// One-shot commands should finish after one cycle
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
