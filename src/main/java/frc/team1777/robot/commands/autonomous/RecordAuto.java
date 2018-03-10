package frc.team1777.robot.commands.autonomous;

import java.io.*;
import edu.wpi.first.wpilibj.command.Command;
import frc.team1777.robot.Robot;
import frc.team1777.utils.InputFrame;

public class RecordAuto extends Command
{
	private boolean finished;
	private FileWriter out;
	
	public RecordAuto(String fn)
	{
		requires(Robot.recorder);
		setTimeout(15);
		
		finished = false;
		
		try {
			out = new FileWriter(Robot.recorder.getRecordingName(fn));
		}
		catch (IOException e)
		{
			System.out.println("oops, IOException!");
			e.printStackTrace();
		}
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		if (Robot.oi.getStopRecordButton() | isTimedOut())
		{
			finished = true;
			return;
		}
		
		InputFrame input = new InputFrame();
		try
		{
			out.write(input.toString());
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	protected boolean isFinished() { return finished; }
	
	protected void end()
	{
		try {
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Robot.recorder.isRecording = false;
		System.out.println("Done recording!");
	}
	
	protected void interrupted()
	{
		end();
	}
}
