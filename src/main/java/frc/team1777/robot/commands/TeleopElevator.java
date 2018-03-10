package frc.team1777.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class TeleopElevator extends Command
{
	public TeleopElevator()
	{
		requires(Robot.elevator);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.elevator.teleopControl();
	}
	
	protected boolean isFinished()
	{
		return false;  // never finishes; defualt commands should be interrupted
	}
	
	protected void end()
	{
		Robot.elevator.elevatorMove(0);
	}
	
	protected void interrupted()
	{
		end();
	}
}
