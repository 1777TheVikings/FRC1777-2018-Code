package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class GetSwitchScaleColors extends Command
{
	public GetSwitchScaleColors()
	{
		requires(Robot.fieldInfo);
		setTimeout(3);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.fieldInfo.getSwitchScaleColors();
	}
	
	protected boolean isFinished()
	{
		return (Robot.fieldInfo.switchScaleColors != null) || isTimedOut();
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
