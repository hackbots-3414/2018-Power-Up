package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.Motor;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.actuators.DoubleMotor;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ActuatorConfig {
	
	private static ActuatorConfig instance;
	
	private TalonSRX talonLeftOne;
	private TalonSRX talonLeftTwo;
	private TalonSRX talonRightOne;
	private TalonSRX talonRightTwo;
	
//	private TalonSRX talonIntake;
	
//	private TalonSRX talonLiftOne;
//	private TalonSRX talonLiftTwo;
	
	private Motor motorLeftOne;
	private Motor motorLeftTwo;
	private Motor motorRightOne;
	private Motor motorRightTwo;
	
//	private Motor motorIntake;
	
//	private Motor motorLiftOne;
//	private Motor motorLiftTwo;
	
//	private Motor motorClimber;
	
	private DoubleMotor doubleMotorRight;
	private DoubleMotor doubleMotorLeft;
	
//	private DoubleMotor doubleMotorIntake;
// 	private DoubleMotor doubleMotorLift;
	
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
		
//		talonLiftOne = new TalonSRX();
//		talonLiftTwo = new TalonSRX();
//		talonIntake = new TalonSRX();
	  
		motorLeftOne = new Motor(talonLeftOne);
	    motorLeftTwo = new Motor(talonLeftTwo);
		motorRightOne = new Motor(talonRightOne);
		motorRightTwo = new Motor(talonRightTwo);
	//	motorLiftOne = new Motor(talonLiftOne);
	//	motorLiftTwo = new Motor(talonLiftTwo);
	//	motorIntake = new Motor(talonIntake);
		
	//	motorLiftTwo.setMotorReversed(true);	
	
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		
//		doubleMotorLift = new DoubleMotor(motorLiftOne, motorLiftTwo);
		
		talonLeftTwo.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		//leftTalonThree.configEncoderCodesPerRev(2048);
		talonLeftTwo.getSensorCollection().getQuadraturePosition();
		

		talonRightTwo.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		//rightTalonTwo.configEncoderCodesPerRev(2048);
		talonRightTwo.getSensorCollection().getQuadraturePosition();
		
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
	
//	public DoubleMotor getLift()
//	{
//		return talonLiftOne;
//	}
//	
//	public Motor getIntakeMotor()
//	{
//		return intakeMotor;
//	}
	
}
	
