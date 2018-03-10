package frc.team1777.robot.commands.autonomous;

/** 
 * Container for holding a single autonomous event. Always insert
 * the exact action performed; it will be inverted automatically.
 * 
 * @author HSRobotics
 *
 */
public class AutoHistory
{
	public static enum ActionType {linear, angular, elevator, claw};
	
	public final ActionType type;
	public final double amount;
	
	public AutoHistory(ActionType type, double amount)
	{
		this.type = type;
		this.amount = amount;
	}
}
