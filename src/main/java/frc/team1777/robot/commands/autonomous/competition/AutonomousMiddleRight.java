package frc.team1777.robot.commands.autonomous.competition;

import frc.team1777.robot.commands.autonomous.AutoBase;
import frc.team1777.robot.commands.autonomous.*;

public class AutonomousMiddleRight extends AutoBase
{
	/**
	 * This is for the middle starting position and deposits in
	 * the right side switch. Uses more aggressive timings to
	 * get there as fast as possible.
	 */
	public AutonomousMiddleRight()
	{
		// 1
		addSequential(new AutonomousMoveLinear(36));
		addSequential(new AutonomousPause(0.35));
		
		// 2
		addSequential(new AutonomousMoveAngular(45));
		addSequential(new AutonomousPause(0.35));
		
		// 3
		addSequential(new AutonomousMoveLinear(45));
		addSequential(new AutonomousPause(0.35));
		
		// 4
		addSequential(new AutonomousMoveAngular(-45));
		addSequential(new AutonomousPause(0.35));
		
		// 5
		addParallel(new AutonomousElevatorToSwitch());
		addSequential(new AutonomousMoveLinear(32));
		addSequential(new AutonomousPause(0.35));
		
		// 6
		addSequential(new AutonomousClawOutput());
	}
}
