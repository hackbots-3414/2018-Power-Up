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
		TalonSRX talonLeftOne = new TalonSRX(5);
		TalonSRX talonLeftTwo = new TalonSRX(4);
		TalonSRX talonRightOne = new TalonSRX(0);
		TalonSRX talonRightTwo = new TalonSRX(1);
		
		
		Motor motorLeftOne = new Motor(talonLeftOne);
		Motor motorLeftTwo = new Motor(talonLeftTwo);
		Motor motorRightOne = new Motor(talonRightOne);
		Motor motorRightTwo = new Motor(talonRightTwo);
		
		doubleMotorLeft = new DoubleMotor(motorLeftOne, motorLeftTwo);
		doubleMotorRight = new DoubleMotor(motorRightOne, motorRightTwo);
		

		
	}
	
	
}
	
