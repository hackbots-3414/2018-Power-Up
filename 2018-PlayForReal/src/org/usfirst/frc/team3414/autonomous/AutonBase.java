package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drivetrain;
import org.usfirst.frc.team3414.autonomous.Alliance;
import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.sensor.NavX;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutonBase implements Runnable {
	protected Drivetrain driveTrain = ActuatorConfig.getInstance().getDrivetrain();
	protected Timer timer = new Timer();
	
	protected NavX navX = SensorConfig.getInstance().getNavX();
	
	private Thread autonThread;
	
	private boolean shoot;
	private Alliance alliance;
	
	public void doAuto(boolean shoot, Alliance alliance)
	{
		this.shoot = shoot;
		this.alliance = alliance;
		
		autonThread = new Thread(this);
		autonThread.start();
	}
	
	protected abstract void vanillaAuto();
		
	protected abstract void redShoot();
	
	protected abstract void blueShoot();
	
	public void run()
	{
		System.out.println("Running Auton Thread----------------------------------------");
		//ActuatorConfig.getInstance().getCompressor().stop();
		
		if(RobotStatus.isAuto())		
		{
			vanillaAuto();
				
			if(shoot)
			{
				switch(alliance)
				{
					case RED:
						redShoot();
						break;
					
					case BLUE:
						blueShoot();
						break;
				}
			}	
		}
		
		System.out.println("Auton Thread Completed----------------------------------------");
	}
}

