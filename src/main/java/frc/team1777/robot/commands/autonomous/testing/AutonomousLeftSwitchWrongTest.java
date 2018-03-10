package frc.team1777.robot.commands.autonomous.testing;

import frc.team1777.robot.commands.autonomous.*;

public class AutonomousLeftSwitchWrongTest extends AutoBase
{
	public AutonomousLeftSwitchWrongTest()
	{
		// 1
		addSequential(new AutonomousMoveLinear(229));
		addSequential(new AutonomousPause(0.5));
		
		// 2
		addSequential(new AutonomousMoveAngular(90));
		addSequential(new AutonomousPause(0.5));
		
		// 3
		addSequential(new AutonomousMoveLinear(229));
		addSequential(new AutonomousPause(0.5));
		
		// 4
		addSequential(new AutonomousMoveAngular(90));
		addSequential(new AutonomousPause(0.5));
		
		// 5
		addParallel(new AutonomousMoveLinear(61));
		addSequential(new AutonomousElevatorToSwitch());
		addSequential(new AutonomousPause(0.5));
		
		// 6
		addSequential(new AutonomousMoveAngular(90));
		addSequential(new AutonomousPause(0.5));
		
		// 7
		addSequential(new AutonomousMoveLinear(38));
		addSequential(new AutonomousPause(0.5));
		addSequential(new AutonomousClawOutput());
		addSequential(new AutonomousMoveLinear(-38));
		addSequential(new AutonomousPause(0.5));
	}
}
