package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.autonomous.Position;
import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.sensor.NavX;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public abstract class AutonBase implements Runnable {
	protected Drivetrain driveTrain = ActuatorConfig.getInstance().getDrivetrain();
	protected Timer timer = new Timer();
	
	protected NavX navX = SensorConfig.getInstance().getNavX();
	
	private Thread autonThread;
	
	private Position position;
	
	public void doAuto(boolean shoot, Position position)
	{
		this.position = position;
		
		autonThread = new Thread(this);
		autonThread.start();
	}
		
	protected abstract void left(String gameData);
	
	protected abstract void center(String gameData);
	
	protected abstract void right(String gameData);
	
	public void run()
	{
		String gameData;
		
		System.out.println("Running Auton Thread----------------------------------------");
		//ActuatorConfig.getInstance().getCompressor().stop();
		
		if(RobotStatus.isAuto())
		{
			  gameData = DriverStation.getInstance().getGameSpecificMessage();
			switch(position)
			{
				case LEFT:
				 
//				 left("LRL");
				left(gameData);
				break;
				
				case CENTER:
				center(gameData);
				break;
				
				case RIGHT:
				right(gameData);
				break;
				
			}		
		}
		
		System.out.println("Auton Thread Completed----------------------------------------");
	}
}

