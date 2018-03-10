package frc.team1777.utils;

import frc.team1777.robot.Robot;

public class InputFrame
{
	public double driveX;
	public double driveY;
	public double elevator;
	public boolean highGear;
	public boolean autoTurn;
	public boolean clawSolenoid;
	public int clawWheel;
	
	public InputFrame()
	{
		driveX = Robot.oi.getX();
		driveY = Robot.oi.getRotation();
		elevator = Robot.oi.getElevator();
		highGear = Robot.oi.getTransmission();
		autoTurn = Robot.oi.getAutoTurn();
		clawSolenoid = Robot.oi.getClawGrabber();
		clawWheel = Robot.oi.getClawWheel();
	}
	
	public InputFrame(String input)
	{
		String[] inputList = input.split(",");
		driveX = Double.parseDouble(inputList[0]);
		driveY = Double.parseDouble(inputList[1]);
		elevator = Double.parseDouble(inputList[2]);
		highGear = (Integer.parseInt(inputList[3]) != 0);
		clawSolenoid = (Integer.parseInt(inputList[4]) != 0);
		clawWheel = Integer.parseInt(inputList[5]);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%1.3f", driveX) + ",");
		sb.append(String.format("%1.3f", driveY) + ",");
		sb.append(String.format("%1.3f", elevator) + ",");
		sb.append((highGear ? 1 : 0) + ",");
		sb.append((clawSolenoid ? 1 : 0) + ",");
		sb.append(clawWheel + "\n");
		
		return sb.toString();
	}
}
