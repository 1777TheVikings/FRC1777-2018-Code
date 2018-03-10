package frc.team1777.robot.commands.autonomous;

import java.util.ArrayList;
import java.util.Collections;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class UndoHistory extends CommandGroup
{
	public UndoHistory(ArrayList<AutoHistory[]> moveHistory)
	{
		Collections.reverse(moveHistory);
		for (int i1 = 0; i1 < moveHistory.size(); i1++)
		{
			AutoHistory[] currentStep = moveHistory.get(i1);
			for (int i2 = 0; i2 < currentStep.length; i2++)
			{
				AutoHistory currentAction = currentStep[i2];
				Command nextCommand;
				switch (currentAction.type)
				{
					case linear: nextCommand = new AutonomousMoveLinear(-1 * currentAction.amount);
								 break;
					case angular: nextCommand = new AutonomousMoveAngular(-1 * currentAction.amount);
								  break;
					case claw:
						if (currentAction.amount == 1)  // output
							nextCommand = new AutonomousClawOutput();
						else  // currentAction.amount = -1  /  intake
							nextCommand = new AutonomousClawIntake();
						break;
					case elevator:
						if (currentAction.amount == 0)  // ground
							nextCommand = new AutonomousElevatorToSwitch();
						else  // currentAction.amount == 1  /  switch
							nextCommand = new AutonomousElevatorToGround();
					default: nextCommand = new AutonomousMoveLinear(0);
				}
				// If running multiple commands at once, only the last
				// one should be sequential.
				if (i2 + 1 == currentStep.length)
				{
					addSequential(nextCommand);
				}
				else
				{
					addParallel(nextCommand);
				}
			}
			addSequential(new AutonomousPause(0.5));
		}
	}
}
