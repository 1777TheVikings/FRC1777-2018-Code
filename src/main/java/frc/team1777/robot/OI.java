package frc.team1777.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI {
	public XboxController controller = new XboxController(0);
	
	public OI() {}
	
	// DRIVE TRAIN CONTROL
	
	public double getX()
	{
		if (getAccuracyButton())
			return controller.getY(RobotMap.moveStick) * RobotMap.moveSens * 0.5;
		else
			return controller.getY(RobotMap.moveStick) * RobotMap.moveSens;
	}
	
	public double getRotation()
	{
		if (getAccuracyButton())
			return controller.getX(RobotMap.moveStick) * RobotMap.turnSens * 0.5;
		else
			return controller.getX(RobotMap.moveStick) * RobotMap.turnSens;
	}
	
	/**
	 * @return true is high gear, false is low gear
	 */
	public boolean getTransmission()
	{
		return controller.getAButton();
	}
	
	/**
	 * @return true makes the robot move at half speed
	 */
	public boolean getAccuracyButton()
	{
		return controller.getBackButton();
	}
	
	/**
	 * Turns towards the nearest cube.
	 * @return true if should be turning
	 */
	public boolean getAutoTurn()
	{
		return controller.getYButton();
	}
	
	// RECORDING CONTROL
	
	/**
	 * Starts recording an autonomous mode. Don't press this during competition.
	 * @return Whether the "start recording" button is pressed on the controller.
	 */
	public boolean getStartRecordButton()
	{
		return controller.getBackButton();
	}
	
	/**
	 * Stops recording an autonomous mode. Don't press this during competition.
	 * @return Whether the "stop recording" button is pressed on the controller.
	 */
	public boolean getStopRecordButton()
	{
		return controller.getStartButton();
	}
	
	// ELEVATOR CONTROL
	
	public double getElevator()
	{
		return controller.getY(RobotMap.elevatorStick) * RobotMap.elevatorSens;
	}
	
	// CLAW CONTROL
	
	public int getClawWheel()
	{
		if (controller.getBumper(RobotMap.wheelOutputSide))
		{
			return -1;
		}
		else if (controller.getBumper(RobotMap.wheelIntakeSide))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public boolean getClawGrabber()
	{
		return controller.getXButton();
	}
}
