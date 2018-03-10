package frc.team1777.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Configs for controller
	public static double moveSens = 1;
	public static double turnSens = 0.75;
	public static double elevatorSens = 1;
	public static Hand moveStick = Hand.kLeft;
	public static Hand elevatorStick = Hand.kRight;
	public static Hand wheelOutputSide = Hand.kRight;
	public static Hand wheelIntakeSide = Hand.kLeft;
	
	// Max rate of change for motors (unused)
	public static double maxROC = 0.4;
	
	// Circumference of the wheels that the encoders are mounted on
	public static double wheelCircumference = 18.84;
	
	// Idle speeds to keep the lift from falling down while stopped, if
	// the motors can't hold it by themselves in brake mode.
	public static double elevatorIdleSpeed = 0.0;
	
	// Ports/IDs used for drive train motors (Talon SRX)
	public static int leftFrontMotor = 3;
	public static int rightFrontMotor = 2;
	public static int leftRearMotor = 4;
	public static int rightRearMotor = 1;
	
	// Ports used for elevator lift (Victor SP)
	public static int elevatorLiftMotor = 1;
	
	// Ports used for climber solenoid
	public static int[] climberSolenoid = {4, 5};
	
	// Ports/IDs used for claw (Talon SRX + double solenoid)
	public static int clawLeftMotor = 5;
	public static int clawRightMotor = 6;
	public static int[] clawSolenoid = {2, 3};
	
	// Ports for lift limit switches
	public static int upperSwitchID = 2;
	public static int middleSwitchID = 6;
	public static int lowerSwitchID = 3;
	
	// Ports/IDs of compressor and transmission solenoids
	public static int compressor = 0;
	public static int[] transSolenoidID = {0, 1};  // forward channel, reverse channel
	
	// Sensor ports
	public static int[] leftEncoderID = {4, 5};
	public static int[] rightEncoderID = {0, 1};
	public static int pigeonID = 0;
	
	// Closed loop tuning
	public static double pGainFactorTurning = 0.5;
	public static double pGainFactorMovement = 0.5;
	public static double pZeroRange = 0.5;
}
