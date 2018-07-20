package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class UpdateNetworking extends Command {

	public UpdateNetworking()
	{
		requires(Robot.networking);
	}
	
	public void initialize() {}
	
	public void execute()
	{
		Robot.networking.update();
	}
	
	protected boolean isFinished()
	{
		return false;
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}
