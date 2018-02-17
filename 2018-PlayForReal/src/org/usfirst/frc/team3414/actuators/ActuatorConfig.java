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
	
	public static final double RampTime =  0.5;//0.5
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
		talonRightOne = new TalonSRX(1);//3   4
		talonRightTwo = new TalonSRX(2);//4   1

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
		motorLiftOne.setMotorReveresed(true);	
		
		
		//double motors
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		
//		doubleMotorIntake = new DoubleMotor(motorIntakeOne, motorIntakeTwo);		
		doubleMotorLift = new DoubleMotor(motorLiftOne, motorLiftTwo);
//		doubleMotorWings = new DoubleMotor(motorWingOne, motorWingTwo);
		
		
		//motor configurations
		talonLeftOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
		talonLeftOne.getSensorCollection().getQuadraturePosition();

		talonRightOne.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, kTimeoutMs);
		
		doubleMotorRight.setMotorReveresed(true);
//		doubleMotorLift.setMotorReveresed(true);

			
		drivetrain = new Drivetrain(doubleMotorLeft, doubleMotorRight);
		
		
		//limit switch stuff
//		talonLiftOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
//		talonLiftOne.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
//		talonLiftOne.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
//		talonLiftOne.overrideLimitSwitchesEnable(true);
//		talonLiftOne.getSensorCollection().isRevLimitSwitchClosed();
//		talonLiftOne.getSensorCollection().isFwdLimitSwitchClosed();
		
		//pid stuff	
		
		double motorRightOneOutput = talonRightOne.getMotorOutputPercent();
		double motorLeftOneOutput = talonLeftOne.getMotorOutputPercent();

		
		//		talonRightOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
//		talonLeftOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMs);
		talonRightOne.setSensorPhase(true);
		talonLeftOne.setSensorPhase(true);
		
		talonLeftOne.configOpenloopRamp(RampTime,RampTimeoutMs);
		talonLeftTwo.configOpenloopRamp(RampTime, RampTimeoutMs);
		talonRightOne.configOpenloopRamp(RampTime, RampTimeoutMs);
		talonLeftTwo.configOpenloopRamp(RampTime, RampTimeoutMs);
	
//		talonRightOne.configNominalOutputForward(0, kTimeoutMs);
//		talonRightOne.configNominalOutputReverse(0, kTimeoutMs);
//		talonRightOne.configPeakOutputForward(1, kTimeoutMs);
//		talonRightOne.configPeakOutputReverse(-1, kTimeoutMs);
//		talonLeftOne.configNominalOutputForward(0, kTimeoutMs);
//		talonLeftOne.configNominalOutputReverse(0, kTimeoutMs);
//		talonLeftOne.configPeakOutputForward(1, kTimeoutMs);
//		talonLeftOne.configPeakOutputReverse(-1, kTimeoutMs);
//		
//		talonRightOne.config_kF(kPIDLoopIdx, 0.09053, kTimeoutMs);
//		talonRightOne.config_kP(kPIDLoopIdx, 3.9346, kTimeoutMs);
//		talonRightOne.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
//		talonRightOne.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
//		talonLeftOne.config_kF(kPIDLoopIdx, 3.9346, kTimeoutMs);
//		talonLeftOne.config_kP(kPIDLoopIdx, 0, kTimeoutMs);
//		talonLeftOne.config_kI(kPIDLoopIdx, 0, kTimeoutMs);
//		talonLeftOne.config_kD(kPIDLoopIdx, 0, kTimeoutMs);
		

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
	
