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
	
//	private TalonSRX talonIntakeOne;
//	private TalonSRX talonIntakeTwo;
	
//	private TalonSRX talonLift;
	
//	private TalonSRX talonClimber;
	
	private Motor motorLeftOne;
	private Motor motorLeftTwo;
	private Motor motorRightOne;
	private Motor motorRightTwo;
	
//	private Motor motorIntakeOne;
//	private Motor motorIntakeTwo;
	
//	private Motor motorLift;
	
//	private Motor motorClimber;
	
	private DoubleMotor doubleMotorRight;
	private DoubleMotor doubleMotorLeft;
	
//	private DoubleMotor doubleMotorIntake;
	
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
		talonLeftOne = new TalonSRX(5);//Get the talon ports
		talonLeftTwo = new TalonSRX(4);
		talonRightOne = new TalonSRX(0);
		talonRightTwo = new TalonSRX(1);
		
//		talonClimber = new TalonSRX();
//		talonIntakeOne = new TalonSRX();
//		talonIntakeTwo = new TalonSRX();
	  
		motorLeftOne = new Motor(talonLeftOne);
	    motorLeftTwo = new Motor(talonLeftTwo);
		motorRightOne = new Motor(talonRightOne);
		motorRightTwo = new Motor(talonRightTwo);
	//	motorClimber = new Motor(talonClimber);
	//	motorIntakeOne = new Motor(talonIntakeOne);
	//	motorIntakeTwo = new Motor(talonIntakeTwo);
	
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		
//		doubleMotorIntake = new DoubleMotor(motorIntakeOne, motorIntakeTwo);
		
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
		return talonLeftTwo; //Figure out which talons to use for encoders
	}
	
	public Drivetrain getDrivetrain()
	{
		return drivetrain;
	}
	
//	public DoubleMotor getClimberMotor()
//	{
//		return climberMotors;
//	}
//	
//	public Motor getIntakeMotor()//double motor
//	{
//		return intakeMotor;
//	}
	
}
	
