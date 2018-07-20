package frc.team1777.robot.commands.autonomous.competition;

import frc.team1777.robot.Robot;
import frc.team1777.robot.commands.autonomous.*;
import frc.team1777.robot.commands.autonomous.AutoHistory.ActionType;

public class AutonomousLeftWrong extends AutoBase
{
	public AutonomousLeftWrong()
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
		
		// 7 - start history here
		addSequential(new AutonomousMoveLinear(38));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, 38)});
		addSequential(new AutonomousClawOutput());
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.claw, 1)});
		addSequential(new AutonomousMoveLinear(-38));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, -38)});
		
		// 8
		addSequential(new AutonomousMoveAngular(90));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.angular, 90)});
		
		// 9
		addParallel(new AutonomousMoveLinear(61));
		addSequential(new AutonomousElevatorToGround());
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, 61),
										   new AutoHistory(ActionType.elevator, 0)});
		
		// 10
		addSequential(new AutonomousMoveAngular(-90));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.angular, -90)});
		
		// 11
		addSequential(new AutonomousMoveLinear(114.5));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, 114.5)});
		
		// 12
		float jetsonAngle = Robot.networking.getJetsonAngle();
		addSequential(new AutonomousMoveAngular(jetsonAngle));
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.angular, jetsonAngle)});
		
		// 13 + 14
		double cubeDist = 6 / Math.sin(jetsonAngle);
		addParallel(new AutonomousMoveLinear(cubeDist));
		addSequential(new AutonomousClawIntake());  // 
		addSequential(new AutonomousPause(0.5));
		moveHistory.add(new AutoHistory[] {new AutoHistory(ActionType.linear, cubeDist)});
		
		// Go through history
		addSequential(new UndoHistory(moveHistory));
	}
}
