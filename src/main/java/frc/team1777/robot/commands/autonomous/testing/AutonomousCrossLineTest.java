package frc.team1777.robot.commands.autonomous.testing;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team1777.robot.commands.autonomous.AutonomousMoveLinear;
import frc.team1777.robot.commands.autonomous.AutonomousPause;

public class AutonomousCrossLineTest extends CommandGroup
{
	/**
	 * Moves far enough to land near the auto line (any part of the robot breaking
	 * the line counts, so falling short is okay) and then turns 40 degrees CCW
	 * for testing purposes.
	 */
	public AutonomousCrossLineTest()
	{
		addSequential(new AutonomousMoveLinear(168));
		addSequential(new AutonomousPause(0.75));
		// addSequential(new AutonomousMoveAngular(40));
	}
}
