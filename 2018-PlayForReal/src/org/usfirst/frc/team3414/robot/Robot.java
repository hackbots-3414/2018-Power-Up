/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3414.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3414.robot.Robot.LogRunnable;
import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.sensor.SensorConfig;
import org.usfirst.frc.team3414.util.Status;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import org.usfirst.frc.team3414.teleop.PacbotTeleop;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

private PacbotTeleop teleop;
	
//	private SendableChooser<AutoBase> autonChooser;
	private SendableChooser<Object> shootChooser;
//	private SendableChooser<Alliance> allianceChooser;
	
	private Thread logThread = new Thread(new LogRunnable());
	private PowerDistributionPanel pdb;

	public void robotInit() 
	{
		RobotStatus.setIsRunning(true);
		
		CameraServer.getInstance().addAxisCamera("10.34.14.3");
		CameraServer.getInstance().startAutomaticCapture();
		
//		ActuatorConfig.getInstance().init();
		SensorConfig.getInstance().init();
		
		pdb = SensorConfig.getInstance().getPDB();
		
		teleop = new PacbotTeleop();
		
		//logThread.start();
		
		chooseAuto();	
		
		
	}

//	public void disabled()
//	{
//		System.out.println("Disabled");
//		// Mentor Francis added the next two lines to reset the encoders each time. This allows repeated testing of Auton without redeploying code
//		ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().setQuadraturePosition(0, 10);
//		ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().setQuadraturePosition(0, 10);
//		teleop.stop();
//	}

	public void operatorControl()
	{
		RobotStatus.setIsRunning(true);
		RobotStatus.setIsAuto(false);
		RobotStatus.setIsTeleop(true);
		
		System.out.println("Telop Running!");
//		teleop.init();		
	}
	
	/**
	 * Chose's the autonomous mode
	 */
	public void chooseAuto()
	{
	//	autonChooser = new SendableChooser<AutoBase>();
		shootChooser = new SendableChooser<Object>();
	//	allianceChooser = new SendableChooser<Alliance>();
		
		
		/*autonChooser.addObject("Left Start Center Gear Delivery", new AutonLeftStartCenterGear());
		autonChooser.addObject("Right Start Center Gear Delivery", new AutonRightStartCenterGear());
		autonChooser.addObject("Center Start Left Gear Delivery", new AutonCenterStartLeftGear());
		autonChooser.addObject("Blue Center Start Gear and Shoot", new AutonBlueAllianceCenterGearShoot());
		autonChooser.addObject("Red Center Start Gear and Shoot", new AutonRedAllianceCenterGearShoot());*/
					
//		SmartDashboard.putData("Autons", autonChooser);
		
		shootChooser.addObject("Yes", true);
		shootChooser.addObject("No", false);
		
		SmartDashboard.putData("Shoot Option", shootChooser);
		
//		allianceChooser.addObject("Red", Alliance.RED);
//		allianceChooser.addObject("Blue", Alliance.BLUE);
//		SmartDashboard.putData("Alliance", allianceChooser);
		
	//	SmartDashboard.putBoolean("Kill Switch", RobotStatus.isAuto());
	}
	
	public void autonomous()
	{			

		RobotStatus.setIsRunning(true);
		RobotStatus.setIsAuto(true);
		RobotStatus.setIsTeleop(false);
//		AutonStatus.getInstance().setStatus(Status.RUNNING);
//		System.out.println(autonChooser.getSelected());
//		
//		autonChooser.getSelected().doAuto((boolean)shootChooser.getSelected(), allianceChooser.getSelected());

	}
	
	public class LogRunnable implements Runnable
	{
		public void run()
		{
			while(RobotStatus.isRunning())
			{
				System.out.println("Battery Voltage: " + pdb.getVoltage());
			}
		}
	}
}
