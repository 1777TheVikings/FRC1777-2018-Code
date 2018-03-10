package frc.team1777.robot.commands.autonomous.testing;

import frc.team1777.robot.commands.autonomous.*;
import frc.team1777.robot.commands.autonomous.AutoHistory.ActionType;

public class AutonomousRoutineTest extends AutoBase
{
	public AutonomousRoutineTest()
	{
		addSequential(new AutonomousMoveAngular(360));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.angular, 360)});
		
		addParallel(new AutonomousMoveLinear(60));
		addSequential(new AutonomousElevatorToSwitch());
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, 60),
										   new AutoHistory(ActionType.elevator, 0)});
		
		addSequential(new UndoHistory(moveHistory));
	}
}
