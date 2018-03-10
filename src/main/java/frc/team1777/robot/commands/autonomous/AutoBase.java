package frc.team1777.robot.commands.autonomous;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBase extends CommandGroup
{
	protected ArrayList<AutoHistory[]> moveHistory;
	/**
	 * Does initialization common to all competition autonomous runs. Should be
	 * inherited by all autonomous modes.
	 */
	public AutoBase()
	{
		super();
		moveHistory = new ArrayList<AutoHistory[]>();
		
		addSequential(new AutonomousElevatorToGround());
	}
}
