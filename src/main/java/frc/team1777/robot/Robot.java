package frc.team1777.robot;

import frc.team1777.robot.RobotMap;
import frc.team1777.robot.commands.autonomous.RecordAuto;
import frc.team1777.robot.commands.autonomous.ReplayAuto;
import frc.team1777.robot.commands.autonomous.competition.*;
import frc.team1777.robot.subsystems.*;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot
{
	public static OI oi;
	
	public static DriveTrain drive;
	public static Elevator elevator;
	public static Claw claw;
	public static Sensors sensors;
	public static FieldInfo fieldInfo;
	public static Networking networking;
	public static Recorder recorder;
	public static PowerDistributionPanel pdp;
	
	public static XboxController controller;
	
	public static Compressor comp;
	
	public static Command autonomousCommand;
	public static SendableChooser<String> autoPositionChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drive = new DriveTrain();
		elevator = new Elevator();
		claw = new Claw();
		sensors = new Sensors();
		fieldInfo = new FieldInfo();
		networking = new Networking();
		recorder = new Recorder();
		
		controller = new XboxController(0);
		
		oi = new OI();  // must be initialized AFTER the drive train
		
		comp = new Compressor(RobotMap.compressor);
		comp.setClosedLoopControl(true);
		
		SmartDashboard.putString("recorder/FileName", "");
		
		NetworkTableInstance.getDefault().getTable("CameraPublisher").getSubTable("Jetson").getEntry("streams")
							.setStringArray(new String[] {"http://tegra-ubuntu.local:80/mjpg"});
		
		autoPositionChooser = new SendableChooser<String>();
		autoPositionChooser.addDefault("Left side", "L");
		autoPositionChooser.addObject("Middle", "M");
		autoPositionChooser.addObject("Right side", "R");
		autoPositionChooser.addObject("Everything is broken", "B");
		SmartDashboard.putData("autonomous/autoPosition", autoPositionChooser);
		
		CameraServer.getInstance().startAutomaticCapture();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		sensors.tarePigeon();
		sensors.resetEncoders();
		
		fieldInfo.allianceColor = null;
		fieldInfo.switchScaleColors = null;
	}

	@Override
	public void disabledPeriodic() {
		fieldInfo.getAllianceColor();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		fieldInfo.getSwitchScaleColors();
		
		char autoPosition = SmartDashboard.getString("autonomous/autoPosition", "B").charAt(0);
		
		if (autoPosition == 'B')
		{
			System.out.println("everything is ded, so let's just cross line");
			autonomousCommand = new AutonomousCrossLine();
		}
		
//		if (autoPosition == 'L')
//		{
//			System.out.println("Left autonomous selected");
//			if (fieldInfo.switchScaleColors[0] == FieldInfo.side.left)
//			{
//				System.out.println("We guessed right! :D");
//				autonomousCommand = new ReplayAuto("left_right.auto");
//			}
//			else
//			{
//				System.out.println("We guessed wrong! D:");
//				autonomousCommand = new ReplayAuto("left_wrong.auto");
//			}
//		}
//		else if (autoPosition == 'M')
//		{
//			System.out.println("Middle autonomous selected");
//			if (fieldInfo.switchScaleColors[0] == FieldInfo.side.left)
//			{
//				System.out.println("It's on the left. *double oof*");
//				autonomousCommand = new ReplayAuto("middle_left.auto");
//			}
//			else
//			{
//				System.out.println("It's on the right. *oof*");
//				autonomousCommand = new ReplayAuto("middle_right.auto");
//			}
//		}
//		else  // autoPosition == 'R'
//		{
//			System.out.println("Right autonomous selected");
//			if (fieldInfo.switchScaleColors[0] == FieldInfo.side.right)
//			{
//				System.out.println("We guessed right! :D");
//				autonomousCommand = new ReplayAuto("right_right.auto");
//			}
//			else
//			{
//				System.out.println("We guessed wrong! D:");
//				autonomousCommand = new ReplayAuto("right_wrong.auto");
//			}
//		}
		
		// autonomousCommand = new ReplayAuto("test.auto");
		// autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Sensors/angle", sensors.getRotation());
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}
	

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if (oi.getStartRecordButton() & !recorder.isRecording)
		{
			if (SmartDashboard.getString("recording/fileName", "") != "")
			{
				recorder.isRecording = true;
				System.out.println("Starting recording: " +
								   SmartDashboard.getString("recording/fileName", ""));
				Command cmd = new RecordAuto(SmartDashboard.getString("recording/fileName", "auto.auto"));
				cmd.start();
			}
			else
			{
				DriverStation.reportWarning("No file name specified to start recording", false);
			}
		}
		
		networking.putAll();
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
}
