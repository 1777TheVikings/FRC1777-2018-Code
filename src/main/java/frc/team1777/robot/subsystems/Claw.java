package frc.team1777.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team1777.robot.Robot;
import frc.team1777.robot.RobotMap;
import frc.team1777.robot.commands.TeleopClaw;

public class Claw extends Subsystem
{
	private static TalonSRX clawMotorLeft = new TalonSRX(RobotMap.clawLeftMotor);
	private static TalonSRX clawMotorRight = new TalonSRX(RobotMap.clawRightMotor);
	
	private static DoubleSolenoid clawSolenoid = new DoubleSolenoid(RobotMap.clawSolenoid[0], RobotMap.clawSolenoid[1]);
	
	public Claw()
	{
		// All set() calls should go to clawMotorLeft. clawMotorRight will follow automatically.
		clawMotorLeft.setNeutralMode(NeutralMode.Brake);
		
		clawMotorRight.setNeutralMode(NeutralMode.Brake);
	}
	
	protected void initDefaultCommand()
	{
		setDefaultCommand(new TeleopClaw());
	}
	
	public void teleopControl()
	{
		int wheel = Robot.oi.getClawWheel();
		boolean grabber = Robot.oi.getClawGrabber();
		control(wheel, grabber);
	}
	
	/**
	 * @param wheel Percent control for the wheel speed, where 1 is intake
	 * @param grabber Whether the claw is opened or not
	 */
	public void control(int wheel, boolean grabber)
	{
		clawMotorLeft.set(ControlMode.PercentOutput, wheel);
		clawMotorRight.set(ControlMode.PercentOutput, wheel);
		
		if (grabber)
		{
			openClaw();
		}
		else
		{
			closeClaw();
		}
	}
	
	public void openClaw()
	{
		clawSolenoid.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeClaw()
	{
		clawSolenoid.set(DoubleSolenoid.Value.kForward);
	}
}
