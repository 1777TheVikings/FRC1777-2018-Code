package frc.team1777.robot.commands.autonomous.testing;

import frc.team1777.robot.commands.autonomous.*;

public class AutonomousLeftSwitchRightTest extends AutoBase
{
	public AutonomousLeftSwitchRightTest()
	{
		// 1
		addParallel(new AutonomousMoveLinear(148.0));
		addParallel(new AutonomousElevatorToSwitch());
		addSequential(new AutonomousPause(0.5));
		
		// 2
		addSequential(new AutonomousMoveAngular(90));
		addSequential(new AutonomousPause(0.5));
		
		// 3
		addSequential(new AutonomousMoveLinear(38));
		addSequential(new AutonomousPause(0.5));
		addSequential(new AutonomousClawOutput());
		addSequential(new AutonomousMoveLinear(-38));
		addSequential(new AutonomousPause(0.5));
	}
}
