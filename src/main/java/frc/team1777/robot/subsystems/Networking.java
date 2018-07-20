package frc.team1777.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team1777.robot.Robot;
import frc.team1777.robot.commands.UpdateNetworking;;

public class Networking extends Subsystem {
	private float lastJetsonAngle;
	
	public Networking() {
		this.lastJetsonAngle = 0.0f;
	}
	
	@Override
	protected void initDefaultCommand()
	{
		this.setDefaultCommand(new UpdateNetworking());
	}
	
	public float getJetsonAngle() {
		return this.lastJetsonAngle;
	}
	
	public void update() {
		this.updateJetsonAngle();
	}
	
	public void putAll() {
		this.putSensorData();
		this.putElevatorData();
		this.putRecordingData();
	}
	
	public void shutdownJetson() {
		SmartDashboard.putBoolean("vision_shutdown", true);
	}
	
	public void putSensorData() {
		SmartDashboard.putNumber("Sensors/leftEncoder", Sensors.leftEncoder.getDistance());
		SmartDashboard.putNumber("Sensors/rightEncoder", Sensors.rightEncoder.getDistance());
		SmartDashboard.putNumber("Sensors/angle", Robot.sensors.getRotation());
	}
	
	public void putElevatorData() {
		SmartDashboard.putBoolean("Elevator/atBottom", Robot.elevator.isElevatorAtBottom());
		SmartDashboard.putBoolean("Elevator/atMiddle", Robot.elevator.isElevatorAtMiddle());
		SmartDashboard.putBoolean("Elevator/atTop", Robot.elevator.isElevatorAtTop());
	}
	
	public void putRecordingData() {
		SmartDashboard.putBoolean("recording/isRecording", Robot.recorder.isRecording);
	}
	
	private void updateJetsonAngle() {
		boolean wasUpdated = SmartDashboard.getBoolean("vision_new_value", false);
		if (wasUpdated) {
			this.lastJetsonAngle = (float) SmartDashboard.getNumber("visoin_value", 0.0);
		}
	}
}
