package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.DoubleMotor;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class ActuatorConfig {
	private static ActuatorConfig instance;
	
	private TalonSRX talonLeftOne;
	private TalonSRX talonLeftTwo;
	private TalonSRX talonRightOne;
	private TalonSRX talonRightTwo;
	
	private TalonSRX talonIntakeOne;
	private TalonSRX talonIntakeTwo;
	
	private TalonSRX talonLift;
	
	private TalonSRX talonClimber;
	
	private Motor motorLeftOne;
	private Motor motorLeftTwo;
	private Motor motorRightOne;
	private Motor motorRightTwo;
	
	private Motor motorIntakeOne;
	private Motor motorIntakeTwo;
	
	private Motor motorLift;
	
	private Motor motorClimber;
	
	private DoubleMotor doubleMotorRight;
	private DoubleMotor doubleMotorLeft;
	
	private DoubleMotor doubleMotorIntake;
	
	public void init()
	{
		talonLeftOne = new TalonSRX(5);//Get the talon ports
		talonLeftTwo = new TalonSRX(4);
		talonRightOne = new TalonSRX(0);
		talonRightTwo = new TalonSRX(1);
		
		
		motorLeftOne = new Motor(talonLeftOne);
	    motorLeftTwo = new Motor(talonLeftTwo);
		motorRightOne = new Motor(talonRightOne);
		motorRightTwo = new Motor(talonRightTwo);
		
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		

		
	}
	
	
}
	
