package org.usfirst.frc.team3414.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3414.util.RotationalDirection;

public class Motor extends MotorBase {
	private TalonSRX talon;//The talon that controls the motor

	/**
	 * Creates a motor
	 * @param talon
	 */
	public Motor(TalonSRX talon)
	{
		this.talon = talon;
		// Added 4/10/17 to avoid brownouts. This limits the number of volts / sec to allow 
		//this.talon.setVoltageRampRate(6.0);
		direction = RotationalDirection.NONE;
	}
	
	public void setFollowerMotor(Motor follower) 
	{
		follower.talon.set(com.ctre.phoenix.motorcontrol.ControlMode.Follower, this.talon.getDeviceID());
	}
		
	public void setSpeed(double speed)
	{
		if(isReversed)
		{
			speed = 0 - speed;
		}
				
		if(speed < 0)
		{
			direction = RotationalDirection.COUNTERCLOCKWISE;
			setRunning(true);
		}
		else if(speed > 0)
		{
			direction = RotationalDirection.CLOCKWISE;
			setRunning(true);
		}
		else
		{
			direction = RotationalDirection.NONE;
			setRunning(false);
		}
		
		talon.set(ControlMode.PercentOutput, speed);
//		talon.set(ControlMode.Position, speed * 4096);
	}

	public void stop() 
	{
		setSpeed(0);		
	}

	public RotationalDirection getDirection() 
	{
		return direction;
	}
	
	public double getCurrent()
	{
		return this.getCurrent();
	}

	public void setDirection(RotationalDirection direction)
	{
		this.direction = direction;
	}	
	
	public void setMotorReveresed(boolean reverse)
	{
		//this.isReversed = reverse;
		talon.setInverted(reverse);
	}
	
	public void setCurrentLimit(int amps)
	{
		talon.configPeakCurrentLimit(amps, 10);
	}


}
