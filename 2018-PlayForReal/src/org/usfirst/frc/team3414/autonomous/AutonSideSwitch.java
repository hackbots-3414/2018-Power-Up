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
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(15, .35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.2, 90);
			ActuatorConfig.getInstance().getLift().setSpeed(-.40);

		//	ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().se
//			if (ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition() < 7800)
//			{
//				ActuatorConfig.getInstance().getLift().setSpeed(.40);
//			}
			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			ActuatorConfig.getInstance().getLift().setSpeed(0);
			ActuatorConfig.getInstance().getMotorIntakeOne().setSpeed(.40);
			ActuatorConfig.getInstance().getMotorIntakeTwo().setSpeed(.40);
//			ActuatorConfig.getInstance().getLift().setSpeed(-40);
//			if (ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection()..getQuadraturePosition(7800))
//			{
//				
//			}
//			
			
			//testing
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4000);//14 find actual measurements
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
			//Drop the power cube
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
			System.out.println("Position Left: Left switch");
		}
		else if("RRR".equals(this.gameData) || "RLR".equals(this.gameData)) //Park past switch from left side
		{
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, .35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.2, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, .35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.2, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(.2, .35);
			
			//testing
//			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8,90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
//			//Drop the power cube
//			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
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
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1.5, .35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.2, 90);
			
			
			//testing
//		ActuatorConfig.getInstance().getDrivetrain().movePid(4);//find actual measurements
//		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
//		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop the power cube
//		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		System.out.println("Position Right: Right switch");
		}
		else if ("LRL".equals(this.gameData) || "LLL".equals(this.gameData))
		{
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, .35);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.2, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, .35);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.2, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(.2, .35);
			
			//testing
//			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8,90);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
			System.out.println("Position Right: Park past switch");	
		}
		else
		{
//			ActuatorConfig.getInstance().getDrivetrain().movePid(4);
			System.out.println("Fail safe: Hannah, you done messed up. Fix your code!");
		}
			
	}

}