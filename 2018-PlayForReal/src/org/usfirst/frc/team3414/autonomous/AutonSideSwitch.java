package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.sensor.SensorConfig;


public class AutonSideSwitch extends AutonBase
{


	public AutonSideSwitch() 
	{
		super();
	}

	protected void left() 
	{
		if("LRL".equals(this.gameData)|| "LLL".equals(this.gameData)) //Left switch from left side
		{
			
			
			//deliver to switch
			AutonUtility.deliverSideSwitch(true);
			
			
			//Lincoln-Timed
//			ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
//			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(18, .35);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.36, 88);
//			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(-.40);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1600);
//			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(0);
//			ActuatorConfig.getInstance().getLift().setSpeed(-.40);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
//			ActuatorConfig.getInstance().getLift().setSpeed(0);
//			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
//			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);


			System.out.println("Position Left: Left switch");
		}
		else if("RRR".equals(this.gameData) || "RLR".equals(this.gameData)) //Park past switch from left side
		{
			
			
			//go forward
			ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(18, .35);
			
			System.out.println("Position Left: Park past switch");
		}
		else
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(10);
			System.out.println("Fail safe: Hannah, you done messed up. Fix your code!");
		}
		
		
//		Failsafes ahead ----->
//		else if("RRR".equals(gameData) || "RLR".equals(gameData))
//		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8,90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
//			System.out.println("Position Left: Park past switch");
//			}
//		else
//		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);
//			System.out.println("Fail safe: Hannah, you done messed up. Fix your code!");
//		}
		
	}

	protected void center() //Perhaps make a failsafe (but comment it out)
	{	
			System.out.println("Nothing here to see here o_o -Hannah");
	}

	protected void right() 
	{
		if("RLR".equals(this.gameData)|| "RRR".equals(this.gameData)) //Right switch from right position
		{
			
			
			//deliver to switch
			AutonUtility.deliverSideSwitch(false);
			
			
			//Lincoln - timed
//			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(-.40);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1600);
//			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(0);
//			ActuatorConfig.getInstance().getLift().setSpeed(-.40);
//			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
//			ActuatorConfig.getInstance().getLift().setSpeed(0);
//			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
//			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
			
		System.out.println("Position Right: Right switch");
		}
		else if ("LRL".equals(this.gameData) || "LLL".equals(this.gameData))
		{
			
			//go forward turn left
			ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(18, .35);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.35, 90);
//			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(3, .35);
			
			System.out.println("Position Right: Park past switch");	
		}
		else
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);
			System.out.println("Fail safe: Hannah, you done messed up. Fix your code!");
		}
			
	}

}