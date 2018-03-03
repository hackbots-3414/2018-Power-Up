package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.robot.RobotStatus;
import org.usfirst.frc.team3414.actuators.Servo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ActuatorConfig 
{   
	private static ActuatorConfig instance;
	
	private TalonSRX talonLeftFront;
	private TalonSRX talonLeftBack;
	private TalonSRX talonRightFront;
	private TalonSRX talonRightBack;
	
	private TalonSRX talonIntakeOne;
	private TalonSRX talonIntakeTwo;
	
	private TalonSRX talonIntakeAngler;
	
	private TalonSRX talonLiftOne;
	private TalonSRX talonLiftTwo;
	
	private TalonSRX talonWingOne;
	private TalonSRX talonWingTwo;
	
	private Motor motorLeftFront;
	private Motor motorLeftBack;
	private Motor motorRightFront;
	private Motor motorRightBack;
	
	private Motor motorIntakeOne;
	private Motor motorIntakeTwo;
	
	private Motor motorIntakeAngler;
	
	private Motor motorLiftOne;
	private Motor motorLiftTwo;
	
	private Motor motorWingOne;
	private Motor motorWingTwo;
	
	private Servo servoWingOne;
	private Servo servoWingTwo;
	
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
		talonLeftFront = new TalonSRX(1);//3  1   3
		talonLeftBack = new TalonSRX(0);//4  2   2
		talonRightFront = new TalonSRX(3);//1  3   4
		talonRightBack = new TalonSRX(2);//0  4   1

		talonIntakeOne = new TalonSRX(7);
		talonIntakeTwo = new TalonSRX(8);
		
		talonIntakeAngler = new TalonSRX(6);
		
		talonLiftOne = new TalonSRX(9);
		talonLiftTwo = new TalonSRX(10);
		
		talonWingOne = new TalonSRX(4);
		talonWingTwo = new TalonSRX(5);
		
		servoWingOne = new Servo(0);
		servoWingTwo = new Servo(1);
		
		
		//motors
		motorLeftFront = new Motor(talonLeftFront);
	    motorLeftBack = new Motor(talonLeftBack);
		motorRightFront = new Motor(talonRightFront);
		motorRightBack = new Motor(talonRightBack);
		
		motorIntakeOne = new Motor(talonIntakeOne);
		motorIntakeTwo = new Motor(talonIntakeTwo);
		
		motorIntakeAngler = new Motor(talonIntakeAngler);
		
		motorLiftOne = new Motor(talonLiftOne);
		motorLiftTwo = new Motor(talonLiftTwo);
		
		motorWingOne = new Motor(talonWingOne);
		motorWingTwo = new Motor(talonWingTwo);

//		motorWingTwo.setMotorReveresed(true);
		motorIntakeTwo.setMotorReveresed(true);
//		motorLiftTwo.setMotorReveresed(true);	
		
		
		//double motors
		doubleMotorLeft = new DoubleMotor(motorLeftFront, motorLeftBack);
		doubleMotorRight = new DoubleMotor(motorRightFront, motorRightBack);
				
		doubleMotorLift = new DoubleMotor(motorLiftOne, motorLiftTwo);
		doubleMotorWings = new DoubleMotor(motorWingOne, motorWingTwo);
		
		
		//talon configs
		talonRightFront.setSensorPhase(true);
		talonLeftFront.setSensorPhase(true);
		
		talonLeftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
		talonRightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
		
		talonIntakeAngler.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, kTimeoutMs);
		
		servoWingOne.disengage();
		servoWingTwo.disengage();
		
		//limit switch stuff
 		talonLiftTwo.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, kTimeoutMs);
		talonLiftTwo.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
 		talonLiftTwo.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
 		talonLiftTwo.overrideLimitSwitchesEnable(true);
 		talonLiftTwo.getSensorCollection().isRevLimitSwitchClosed();
		
		
			System.out.println("PIDing-----------------------------------------------------------------------------");
			talonLeftFront.configOpenloopRamp(RampTimeAuton,RampTimeoutMs);
			talonRightFront.configOpenloopRamp(RampTimeAuton, RampTimeoutMs);
			
			talonRightFront.configNominalOutputForward(0, kTimeoutMs);
			talonRightFront.configNominalOutputReverse(0, kTimeoutMs);
			talonRightFront.configPeakOutputForward(1, kTimeoutMs);
			talonRightFront.configPeakOutputReverse(-1, kTimeoutMs);
			talonLeftFront.configNominalOutputReverse(0, kTimeoutMs);
			talonLeftFront.configPeakOutputForward(1, kTimeoutMs);
			talonLeftFront.configPeakOutputReverse(-1, kTimeoutMs);
			
			talonRightFront.config_kF(kPIDLoopIdx, 0.09366, kTimeoutMs);//0.09053
			talonRightFront.config_kP(kPIDLoopIdx, 1.2, kTimeoutMs);
			talonRightFront.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
			talonRightFront.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
			talonLeftFront.config_kF(kPIDLoopIdx, 0.08999, kTimeoutMs);//3.9346
			talonLeftFront.config_kP(kPIDLoopIdx, 0.365357, kTimeoutMs);
			talonLeftFront.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
			talonLeftFront.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
						
		
		
		drivetrain = new Drivetrain(doubleMotorRight, doubleMotorLeft);
	}
	
	public TalonSRX getRightTalonOne()
	{
		return talonRightFront;
	}
	
	public TalonSRX getLeftTalonOne()
	{
		return talonLeftFront;
	}
	
	public TalonSRX getRightEncoder()
	{
		return talonRightFront;//Figure out which talons to use for encoders
	}
	
	public TalonSRX getLeftEncoder()
	{
		return talonLeftFront; //Figure out which talons to use for encoders
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
		return talonLiftTwo;
	}
	
	public Motor motorIntakeOne()
	{
		return motorIntakeOne;
	}
	
	public Motor motorIntakeTwo()
	{
		return motorIntakeTwo;
	}
	
	public Servo servoWingOne()
	{
		return servoWingOne;
	}
	
	public Servo servoWingTwo()
	{
		return servoWingTwo;
	}
	
	public DoubleMotor doubleMotorWings()
	{
		return doubleMotorWings;
	}
	
	public Motor motorIntakeAngler()
	{
		return motorIntakeAngler;
	}
	
	public TalonSRX talonIntakeAngler()
	{
		return talonIntakeAngler;
	}

}
	
