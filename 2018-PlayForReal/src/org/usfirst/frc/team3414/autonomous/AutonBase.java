package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.autonomous.Position;
import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.sensor.NavX;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutonBase implements Runnable 
{
	
	protected Drivetrain driveTrain = ActuatorConfig.getInstance().getDrivetrain();
	protected Timer timer = new Timer();
	
	protected String gameData;

	public AutonBase(String gameData)
	{
		this.gameData = gameData;	
	}
	
	protected NavX navX = SensorConfig.getInstance().getNavX();
	
	private Thread autonThread;
	
	private Position position;
	
	public void doAuto(Position position)
	{
		this.position = position;
		
		autonThread = new Thread(this);
		autonThread.start();
	}
		
	protected abstract void left();
	
	protected abstract void center();
	
	protected abstract void right();
	
	public void run()
	{

		
		System.out.println("Running Auton Thread----------------------------------------");
		//ActuatorConfig.getInstance().getCompressor().stop();
		
		if(RobotStatus.isAuto())
		{

			switch(position)
			{
				case LEFT:
				left();
				break;
				
				case CENTER:
				center();
				break;
				
				case RIGHT:
				right();
				break;
				
			}		
		}
		
		System.out.println("Auton Thread Completed----------------------------------------");
	}
}

