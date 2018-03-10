package frc.team1777.robot.commands.autonomous.competition;

import frc.team1777.robot.commands.autonomous.AutoBase;
import frc.team1777.robot.commands.autonomous.*;

public class AutonomousMiddleLeft extends AutoBase
{
	/**
	 * This is for the middle starting position and deposits in
	 * the left side switch. Uses more aggressive timings to
	 * get there as fast as possible.
	 */
	public AutonomousMiddleLeft()
	{
		// 1
		addSequential(new AutonomousMoveLinear(36));
		addSequential(new AutonomousPause(0.35));
		
		// 2
		addSequential(new AutonomousMoveAngular(-90));
		addSequential(new AutonomousPause(0.35));
		
		// 3
		addSequential(new AutonomousMoveLinear(80));
		addSequential(new AutonomousPause(0.35));
		
		// 4
		addSequential(new AutonomousMoveAngular(90));
		addSequential(new AutonomousPause(0.35));
		
		// 5
		addParallel(new AutonomousElevatorToSwitch());
		addSequential(new AutonomousMoveLinear(64));
		addSequential(new AutonomousPause(0.35));
		
		// 6
		addSequential(new AutonomousClawOutput());
	}
}
