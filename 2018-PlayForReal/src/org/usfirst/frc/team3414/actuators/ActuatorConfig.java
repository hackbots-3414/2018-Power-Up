package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.robot.RobotStatus;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ActuatorConfig 
{   
	private static ActuatorConfig instance;
	
	private TalonSRX talonLeftOne;
	private TalonSRX talonLeftTwo;
	private TalonSRX talonRightOne;
	private TalonSRX talonRightTwo;
	
	private TalonSRX talonIntakeOne;
	private TalonSRX talonIntakeTwo;
	
	private TalonSRX talonIntakeAngler;
	
	private TalonSRX talonLiftOne;
	private TalonSRX talonLiftTwo;
	
	private TalonSRX talonWingOne;
	private TalonSRX talonWingTwo;
	
	private Motor motorLeftOne;
	private Motor motorLeftTwo;
	private Motor motorRightOne;
	private Motor motorRightTwo;
	
	private Motor motorIntakeOne;
	private Motor motorIntakeTwo;
	
	private Motor motorIntakeAngler;
	
	private Motor motorLiftOne;
	private Motor motorLiftTwo;
	
	private Motor motorWingOne;
	private Motor motorWingTwo;
	
	
	private DoubleMotor doubleMotorRight;
	private DoubleMotor doubleMotorLeft;
	
	private DoubleMotor doubleMotorIntake;
 	private DoubleMotor doubleMotorLift;
 	
 	private DoubleMotor doubleMotorWings;
	
	private Drivetrain drivetrain;
	
	public static final int kTimeoutMs = 10;
	public static final int kPIDLoopIdx = 0;
	
	public static final double RampTimeTeleop =  0.5;//0.5
	public static final double RampTimeAuton =  1;
			
	public static final int RampTimeoutMs = 20000;
	
	
	public static ActuatorConfig getInstance()
	{
		if(instance == null)
		{
			instance = new ActuatorConfig();
		}
		
		return instance;
		
	}
	
	public void init()
	{   
			
		//talons
		talonLeftOne = new TalonSRX(3);//1   3
		talonLeftTwo = new TalonSRX(4);//2   2
		talonRightOne = new TalonSRX(0);//3   4
		talonRightTwo = new TalonSRX(1);//4   1

//		talonIntakeOne = new TalonSRX(5);
//		talonIntakeTwo = new TalonSRX(6);
		
//		talonIntakeAngler = new TalonSRX(7);
		
		talonLiftOne = new TalonSRX(8);
		talonLiftTwo = new TalonSRX(9);
		
//		talonWingOne = new TalonSRX(10);
//		talonWingTwo = new TalonSRX(11);
		
		
		//motors
		motorLeftOne = new Motor(talonLeftOne);
	    motorLeftTwo = new Motor(talonLeftTwo);
		motorRightOne = new Motor(talonRightOne);
		motorRightTwo = new Motor(talonRightTwo);
		
//		motorIntakeOne = new Motor(talonIntakeOne);
//		motorIntakeTwo = new Motor(talonIntakeTwo);
		
//		motorIntakeAngler = new Motor(talonIntakeAngler);
		
		motorLiftOne = new Motor(talonLiftOne);
		motorLiftTwo = new Motor(talonLiftTwo);
		
//		motorWingOne = new Motor(talonWingOne);
//		motorWingTwo = new Motor(talonWingTwo);

//		motorIntakeTwo.setMotorReveresed(true);
	//	motorLiftOne.setMotorReveresed(true);	
		
		
		//double motors
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		
//		doubleMotorIntake = new DoubleMotor(motorIntakeOne, motorIntakeTwo);		
		doubleMotorLift = new DoubleMotor(motorLiftOne, motorLiftTwo);
//		doubleMotorWings = new DoubleMotor(motorWingOne, motorWingTwo);
		talonRightOne.setSensorPhase(true);
		talonLeftOne.setSensorPhase(true);
		
		talonLeftOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);

		talonRightOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
//		startAngle = navX.getYaw();
		
		this.talonRightTwo.set(ControlMode.Follower, this.talonRightOne.getDeviceID());
		this.talonLeftTwo.set(ControlMode.Follower, this.talonLeftOne.getDeviceID());
	
		
		if(RobotStatus.isAuto())
		{
			
			talonLeftOne.configOpenloopRamp(RampTimeAuton,RampTimeoutMs);
			talonRightOne.configOpenloopRamp(RampTimeAuton, RampTimeoutMs);
			
			talonRightOne.configNominalOutputForward(0, kTimeoutMs);
			talonRightOne.configNominalOutputReverse(0, kTimeoutMs);
			talonRightOne.configPeakOutputForward(1, kTimeoutMs);
			talonRightOne.configPeakOutputReverse(-1, kTimeoutMs);
			talonLeftOne.configNominalOutputReverse(0, kTimeoutMs);
			talonLeftOne.configPeakOutputForward(1, kTimeoutMs);
			talonLeftOne.configPeakOutputReverse(-1, kTimeoutMs);
			
			talonRightOne.config_kF(kPIDLoopIdx, 0.09366, kTimeoutMs);//0.09053
			talonRightOne.config_kP(kPIDLoopIdx, 1.2, kTimeoutMs);
			talonRightOne.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
			talonRightOne.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
			talonLeftOne.config_kF(kPIDLoopIdx, 0.08999, kTimeoutMs);//3.9346
			talonLeftOne.config_kP(kPIDLoopIdx, 0.365357, kTimeoutMs);
			talonLeftOne.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
			talonLeftOne.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
						
		}
		
	}
	
	public TalonSRX getRightTalonOne()
	{
		return talonRightOne;
	}
	
	public TalonSRX getLeftTalonOne()
	{
		return talonLeftOne;
	}
	
	public TalonSRX getRightEncoder()
	{
		return talonRightOne;//Figure out which talons to use for encoders
	}
	
	public TalonSRX getLeftEncoder()
	{
		return talonLeftOne; //Figure out which talons to use for encoders
	}
	
	public Drivetrain getDrivetrain()
	{
		return drivetrain;
	}
	
	public DoubleMotor getLift()
	{
		return doubleMotorLift;
	}
	
	public TalonSRX getLiftLimitSwitch()
	{
		return talonLiftOne;
	}
	
//	public DoubleMotor getIntakeMotor()
//	{
//		return doubleMotorIntake;
//	}
	
}
	
