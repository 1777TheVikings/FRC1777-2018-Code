package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1777.robot.Robot;

public class GetJetsonAngle extends Command
{
	public GetJetsonAngle()
	{
		requires(Robot.jetson);
		setTimeout(2.0);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.jetson.lastAngle = SmartDashboard.getNumber("vision_angle", 999.0);
	}
	
	protected boolean isFinished()
	{
		return false;
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
