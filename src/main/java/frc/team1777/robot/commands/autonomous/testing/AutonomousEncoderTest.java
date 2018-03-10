package frc.team1777.robot.commands.autonomous.testing;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1777.robot.Robot;

public class AutonomousEncoderTest extends Command
{
	/**
	 * Tests the encoders by outputting the encoder distance to the
	 * SmartDashboard.
	 */
	public AutonomousEncoderTest()
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
		Robot.drive.autonomousDrive(0.3, 0);
		SmartDashboard.putNumber("Encoders", Robot.sensors.getDistance());
	}
	
	protected boolean isFinished()
	{
		return (Robot.sensors.getDistance() >= 360);
	}
	
	protected void end()
	{
		System.out.println("Done! Encoder distance: " + Robot.sensors.getDistance());
		Robot.sensors.tarePigeon();
		Robot.sensors.resetEncoders();
	}
	
	protected void interrupted() {}
}
