package frc.team1777.robot.commands.autonomous.competition;

import frc.team1777.robot.Robot;
import frc.team1777.robot.commands.GetJetsonAngle;
import frc.team1777.robot.commands.autonomous.*;
import frc.team1777.robot.commands.autonomous.AutoHistory.ActionType;

public class AutonomousRightRight extends AutoBase
{
	public AutonomousRightRight()
	{
		// 1
		addParallel(new AutonomousMoveLinear(148.0));
		addParallel(new AutonomousElevatorToSwitch());
		addSequential(new AutonomousPause(0.5));
		
		// 2
		addSequential(new AutonomousMoveAngular(-90));
		addSequential(new AutonomousPause(0.5));
		
		// 3 - start history here
		addSequential(new AutonomousMoveLinear(38));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, 38)});
		addSequential(new AutonomousClawOutput());
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.claw, 1)});
		addSequential(new AutonomousMoveLinear(-38));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, -38)});
		
		// 4
		addSequential(new AutonomousMoveAngular(90));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.angular, 90)});
		
		// 5
		addParallel(new AutonomousMoveLinear(93.5));
		addSequential(new AutonomousElevatorToGround());
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, 93.5),
										   new AutoHistory(ActionType.elevator, 0)});
		
		// 6
		addSequential(new AutonomousMoveAngular(-90));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.angular, -90)});
		
		// 7
		addSequential(new AutonomousMoveLinear(114.5));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, 114.5)});
		
		// 8
		addSequential(new GetJetsonAngle());
		addSequential(new AutonomousMoveAngular(Robot.jetson.getSavedAngle()));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.angular, Robot.jetson.getSavedAngle())});
		
		// 9 + 10
		double cubeDist = 6 / Math.sin(Robot.jetson.getSavedAngle());
		addParallel(new AutonomousMoveLinear(cubeDist));
		addSequential(new AutonomousClawIntake());  // 
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, cubeDist)});
		
		// Go through history
		addSequential(new UndoHistory(moveHistory));
	}
}
