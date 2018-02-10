package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.Motor;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.actuators.DoubleMotor;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;

public class ActuatorConfig {
	
	private static ActuatorConfig instance;
	
	private TalonSRX talonLeftOne;
	private TalonSRX talonLeftTwo;
	private TalonSRX talonRightOne;
	private TalonSRX talonRightTwo;
	
//	private TalonSRX talonIntake;
	
	private TalonSRX talonLiftOne;
	private TalonSRX talonLiftTwo;
	
	private Motor motorLeftOne;
	private Motor motorLeftTwo;
	private Motor motorRightOne;
	private Motor motorRightTwo;
	
//	private Motor motorIntake;
	
	private Motor motorLiftOne;
	private Motor motorLiftTwo;
	
//	private Motor motorClimber;
	
	private DoubleMotor doubleMotorRight;
	private DoubleMotor doubleMotorLeft;
	
//	private DoubleMotor doubleMotorIntake;
 	private DoubleMotor doubleMotorLift;
	
	private Drivetrain drivetrain;
	
	public static final int kTimeoutMs = 10;
	public static final int kPIDLoopIdx = 0;
	
	public static final double RampTime = 0.5;
	public static final int RampTimeoutMs = 4000;
	
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
			
		talonLeftOne = new TalonSRX(2);//3
		talonLeftTwo = new TalonSRX(1);//2
		talonRightOne = new TalonSRX(4);//4
		talonRightTwo = new TalonSRX(3);//1
		
//		talonLiftOne = new TalonSRX(5);
//		talonLiftTwo = new TalonSRX(6);
//		talonIntake = new TalonSRX();
	  
		motorLeftOne = new Motor(talonLeftOne);
	    motorLeftTwo = new Motor(talonLeftTwo);
		motorRightOne = new Motor(talonRightOne);
		motorRightTwo = new Motor(talonRightTwo);
//		motorLiftOne = new Motor(talonLiftOne);
//		motorLiftTwo = new Motor(talonLiftTwo);
	//	motorIntake = new Motor(talonIntake);
		
	//	motorLiftTwo.setMotorReversed(true);	
	
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		
//		doubleMotorLift = new DoubleMotor(motorLiftOne, motorLiftTwo);
		
		talonLeftTwo.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		talonLeftTwo.getSensorCollection().getQuadraturePosition();

		talonRightTwo.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		talonRightTwo.getSensorCollection().getQuadraturePosition();
		
		
		//		talonLiftOne.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
//		talonLiftOne.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
//		talonLiftOne.overrideLimitSwitchesEnable(true);
//		talonLiftOne.getSensorCollection().isRevLimitSwitchClosed();
//		talonLiftOne.getSensorCollection().isFwdLimitSwitchClosed();
		
		//rightTripleMotor.setMotorReveresed(true);
		doubleMotorRight.setMotorReveresed(true);
		
		drivetrain = new Drivetrain(doubleMotorLeft, doubleMotorRight);
		
		//pid stuff
		
		
		
		
		double motorRightOneOutput = talonRightOne.getMotorOutputPercent();
		double motorLeftOneOutput = talonLeftOne.getMotorOutputPercent();
		talonRightOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
		talonLeftOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
		
		talonRightOne.setSensorPhase(true);
		talonLeftOne.setSensorPhase(true);
	
		talonLeftOne.configOpenloopRamp(RampTime,RampTimeoutMs);
		talonLeftTwo.configOpenloopRamp(RampTime, RampTimeoutMs);
		talonRightOne.configOpenloopRamp(RampTime, RampTimeoutMs);
		talonLeftTwo.configOpenloopRamp(RampTime, RampTimeoutMs);

		
		talonRightOne.configNominalOutputForward(0, kTimeoutMs);
		talonRightOne.configNominalOutputReverse(0, kTimeoutMs);
		talonRightOne.configPeakOutputForward(1, kTimeoutMs);
		talonRightOne.configPeakOutputReverse(-1, kTimeoutMs);
		talonLeftOne.configNominalOutputForward(0, kTimeoutMs);
		talonLeftOne.configNominalOutputReverse(0, kTimeoutMs);
		talonLeftOne.configPeakOutputForward(1, kTimeoutMs);
		talonLeftOne.configPeakOutputReverse(-1, kTimeoutMs);
		
		talonRightOne.config_kF(kPIDLoopIdx, 0.110918, kTimeoutMs);
		talonRightOne.config_kP(kPIDLoopIdx, 0.22, kTimeoutMs);
		talonRightOne.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
		talonRightOne.config_kD(kPIDLoopIdx, 2.5575, kTimeoutMs);
		talonLeftOne.config_kF(kPIDLoopIdx, 0.110918, kTimeoutMs);
		talonLeftOne.config_kP(kPIDLoopIdx, 0.22, kTimeoutMs);
		talonLeftOne.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
		talonLeftOne.config_kD(kPIDLoopIdx, 2.2, kTimeoutMs);
		
		
		

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
		return talonRightTwo;//Figure out which talons to use for encoders
	}
	
	public TalonSRX getLeftEncoder()
	{
		return talonLeftOne; //Figure out which talons to use for encoders
	}
	
	public Drivetrain getDrivetrain()
	{
		return drivetrain;
	}
	
//	public DoubleMotor getLift()
//	{
//		return doubleMotorLift;
//	}
	
//	public TalonSRX getLiftLimitSwitch()
//	{
//		return talonLiftOne;
//	}
	
//	public Motor getIntakeMotor()
//	{
//		return intakeMotor;
//	}
	
}
	
