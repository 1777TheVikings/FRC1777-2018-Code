package frc.team1777.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team1777.robot.Robot;
import frc.team1777.robot.RobotMap;
import frc.team1777.robot.commands.TeleopElevator;

public class Elevator extends Subsystem
{
	private static VictorSP elevatorMotor = new VictorSP(RobotMap.elevatorLiftMotor);
	
	private static DigitalInput upperSwitch = new DigitalInput(RobotMap.upperSwitchID);
	private static DigitalInput middleSwitch = new DigitalInput(RobotMap.middleSwitchID);
	private static DigitalInput lowerSwitch = new DigitalInput(RobotMap.lowerSwitchID);
	
	public Elevator() {}
	
	protected void initDefaultCommand()
	{
		setDefaultCommand(new TeleopElevator());
	}
	
	public boolean isElevatorAtBottom() { return !lowerSwitch.get(); }
	public boolean isElevatorAtMiddle() { return !middleSwitch.get(); }
	public boolean isElevatorAtTop() { return !upperSwitch.get(); }

	
	public void teleopControl()
	{
		elevatorMove(Robot.oi.getElevator());
	}
	
	public void elevatorMove(double speed)
	{
		if (isElevatorAtMiddle())
		{
			speed *= 0.4;
		}
		else
		{
			speed *= 0.9;
		}
		
		if (Math.signum(speed) == 1)
		{
			if (isElevatorAtBottom())
			{
				elevatorMotor.set(RobotMap.elevatorIdleSpeed);
			}
			else
			{
				elevatorMotor.set(speed);
			}
		}
		else if (Math.signum(speed) == -1)
		{
			if (isElevatorAtTop())
			{
				elevatorMotor.set(RobotMap.elevatorIdleSpeed);
			}
			else
			{
				elevatorMotor.set(speed);
			}
		}
		else
		{
			elevatorMotor.set(speed);
		}
	}
}
