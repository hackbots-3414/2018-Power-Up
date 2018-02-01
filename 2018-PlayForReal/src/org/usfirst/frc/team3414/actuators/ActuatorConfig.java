package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.Motor;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.actuators.DoubleMotor;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
		talonLeftOne = new TalonSRX(3);//Get the talon ports
		talonLeftTwo = new TalonSRX(2);
		talonRightOne = new TalonSRX(4);
		talonRightTwo = new TalonSRX(1);
		
		talonLiftOne = new TalonSRX(5);
		talonLiftTwo = new TalonSRX(6);
//		talonIntake = new TalonSRX();
	  
		motorLeftOne = new Motor(talonLeftOne);
	    motorLeftTwo = new Motor(talonLeftTwo);
		motorRightOne = new Motor(talonRightOne);
		motorRightTwo = new Motor(talonRightTwo);
		motorLiftOne = new Motor(talonLiftOne);
		motorLiftTwo = new Motor(talonLiftTwo);
	//	motorIntake = new Motor(talonIntake);
		
	//	motorLiftTwo.setMotorReversed(true);	
	
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		
		doubleMotorLift = new DoubleMotor(motorLiftOne, motorLiftTwo);
		
		talonLeftTwo.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		talonLeftTwo.getSensorCollection().getQuadraturePosition();

		talonRightTwo.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		talonRightTwo.getSensorCollection().getQuadraturePosition();
		
		talonLiftOne.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		talonLiftOne.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);
		talonLiftOne.overrideLimitSwitchesEnable(true);
		
		//rightTripleMotor.setMotorReveresed(true);
		doubleMotorRight.setMotorReveresed(true);
		
		drivetrain = new Drivetrain(doubleMotorLeft, doubleMotorRight);
		


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
	
	public DoubleMotor getLift()
	{
		return doubleMotorLift;
	}
	
	public TalonSRX getLiftLimitSwitch()
	{
		return talonLiftOne;
	}
	
//	public Motor getIntakeMotor()
//	{
//		return intakeMotor;
//	}
	
}
	
