package frc.team1777.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class AutonomousElevatorToSwitch extends Command
{
	private double speed;
	
	public AutonomousElevatorToSwitch()
	{
		requires(Robot.elevator);
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.elevator.elevatorMove(speed);
	}
	
	protected boolean isFinished()
	{
		return Robot.elevator.isElevatorAtTop();
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
