package frc.team1777.robot.subsystems;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Recorder extends Subsystem
{
	public static String root = "/home/lvuser/autos/";
	public boolean isRecording;
	
	public Recorder()
	{
		isRecording = false;
		
		if (new File(root).mkdirs())
		{
			System.out.println("Created directory " + root);
		}
	}
	
	public void initDefaultCommand() {}
	
	public String[] getAllRecordings()
	{
		File f = new File(root);
		return (String[]) new ArrayList<String>(Arrays.asList(f.list())).toArray();
	}
	
	public String getRecordingName(String fn)
	{
		return root + fn;
	}
}
