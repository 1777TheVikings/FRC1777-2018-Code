package frc.team1777.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1777.robot.commands.GetJetsonAngle;

public class Jetson extends Subsystem
{
	public double lastAngle;
	private double savedAngle;
	
	public Jetson()
	{
		lastAngle = 999;
	}
	
	protected void initDefaultCommand()
	{
		setDefaultCommand(new GetJetsonAngle());
	}
	
	public double getAngle()
	{
		lastAngle = SmartDashboard.getNumber("visionAngle", 999);
		return lastAngle;
	}
	
	public void saveAngle()
	{
		savedAngle = lastAngle;
	}
	
	public double getSavedAngle()
	{
		return savedAngle;
	}
}
