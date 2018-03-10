package frc.team1777.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FieldInfo extends Subsystem
{
	public static enum color {red, blue};
	public color allianceColor;
	
	public static enum side {left, right};
	/** 
	 * An array containing our alliance's side of (in this order) the near switch and the scale.
	 */
	public side[] switchScaleColors;
	
	public static DriverStation ds = DriverStation.getInstance();
	
	public void initDefaultCommand() {}
	
	/**
	 * Sets the allianceColor property of this subsystem.
	 * 
	 * Does not return the alliance color directly. Results are stored in FieldInfo.allianceColor.
	 * 
	 * @return True if successful; False otherwise
	 */
	public boolean getAllianceColor()
	{
		DriverStation.Alliance a = ds.getAlliance();
		
		if (a == DriverStation.Alliance.Invalid)
		{
			allianceColor = null;
			return false;
		}
		
		if (a == DriverStation.Alliance.Blue)
		{
			allianceColor = color.blue;
		}
		else
		{
			allianceColor = color.red;
		}
		
		return true;
	}
	
	/** Gets the positions of our team's side of the near switch and the scale, as set
	 * 	by the driver station (for testing) or by the FMS.
	 * 
	 *  Does not return a value. Results are stored in FieldInfo.switchScaleColors.
	 */
	public void getSwitchScaleColors()
	{
		String gameData = ds.getGameSpecificMessage();
		side[] out = new side[2];
		
		// Near switch
		if (gameData.charAt(0) == 'L')
		{
			out[0] = side.left;
		}
		else
		{
			out[0] = side.right;
		}
		
		// Scale
		if(gameData.charAt(0) == 'L')
		{
			out[1] = side.left;
		}
		else
		{
			out[1] = side.right;
		}
		
		switchScaleColors = out;
	}
}
