package frc.team1777.robot.commands.autonomous.testing;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;

public class AutonomousPigeonTest extends Command
{
	/**
	 * Tests the Pigeon by slowly turning to "360 degrees". Useful
	 * for ensuring that the Pigeon is working, although accuracy
	 * will leave much to be desired.
	 */
	public AutonomousPigeonTest()
	{
		requires(Robot.drive);
		requires(Robot.sensors);
	}
	
	protected void initialize()
	{
		Robot.sensors.tarePigeon();
		Robot.sensors.resetEncoders();
	}
	
	protected void execute()
	{
		Robot.drive.autonomousDrive(0, -0.4);
		System.out.println("Pigeon rotation: " + Robot.sensors.getRotation());
	}
	
	protected boolean isFinished()
	{
		return (Math.round(Robot.sensors.getRotation()) > 360.0);
	}
	
	protected void end()
	{
		System.out.println("Done! Pigeon rotation: " + Robot.sensors.getRotation());
		Robot.sensors.tarePigeon();
	}
	
	protected void interrupted() {}
}
