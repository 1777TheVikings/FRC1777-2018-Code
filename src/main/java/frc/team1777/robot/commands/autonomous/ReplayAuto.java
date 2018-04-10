package frc.team1777.robot.commands.autonomous;

import java.io.*;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;
import frc.team1777.utils.InputFrame;

public class ReplayAuto extends Command
{
	private boolean finished;
	private BufferedReader in;
	private InputFrame lastFrame;
	
	public ReplayAuto(String fn)
	{
		requires(Robot.recorder);
		requires(Robot.drive);
		requires(Robot.elevator);
		requires(Robot.claw);
		
		finished = false;
		lastFrame = new InputFrame("0.000,0.000,0.000,0,0,0");
		
		try
		{
			in = new BufferedReader(new FileReader(Robot.recorder.getRecordingName(fn)));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		String line = null;
		try
		{
			line = in.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		if (line == null || line == "")
		{
			finished = true;
			return;
		}
		
		lastFrame = new InputFrame(line);
		
		Robot.drive.autonomousDrive(lastFrame.driveX, lastFrame.driveY);
		if (lastFrame.highGear)
		{
			Robot.drive.fastTransmission();
		}
		else
		{
			Robot.drive.slowTransmission();
		}
		Robot.elevator.elevatorMove(lastFrame.elevator);
		Robot.claw.control(lastFrame.clawWheel, lastFrame.clawSolenoid);
	}
	
	protected boolean isFinished()
	{
		return finished;
	}
	
	protected void end()
	{
		try
		{
			in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Robot.drive.autonomousDrive(0, 0);
		Robot.drive.slowTransmission();
		Robot.claw.control(0, false);
		Robot.elevator.elevatorMove(0);
	}
	
	protected void interrupted()
	{
		end();
	}
}
