package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;


public class AutonSideSwitch extends AutonBase
{


	public AutonSideSwitch(String gameData) 
	{
		super(gameData);
	}

	protected void left() 
	{
		if("LRL".equals(gameData)|| "LLL".equals(gameData)) //Left switch from left side
		{
			ActuatorConfig.getInstance().getDrivetrain().movePid(4);//14 find actual measurements
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8, 90);
			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
			//Drop the power cube
			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
			System.out.println("Position Left: Left switch");
		}
		else if("RRR".equals(gameData) || "RLR".equals(gameData)) //Park past switch from left side
		{
			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.8,90);
			ActuatorConfig.getInstance().getDrivetrain().movePid(1);
			//Drop the power cube
			ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
			System.out.println("Position Left: Park past switch");
		}
		else
		{
			ActuatorConfig.getInstance().getDrivetrain().movePid(10);
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
		if("RLR".equals(gameData)|| "RRR".equals(gameData)) //Right switch from right position
		{
		
		ActuatorConfig.getInstance().getDrivetrain().movePid(4d);//find actual measurements
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8, 90);
		ActuatorConfig.getInstance().getDrivetrain().movePid(1);
		//Drop the power cube
		ActuatorConfig.getInstance().getDrivetrain().movePid(-1);
		System.out.println("Position Right: Right switch");
		}
		else if ("LRL".equals(gameData) || "LLL".equals(gameData))
		{
			ActuatorConfig.getInstance().getDrivetrain().movePid(5);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(.8,90);
			ActuatorConfig.getInstance().getDrivetrain().movePid(3);
			System.out.println("Position Right: Park past switch");	
		}
		else
		{
			ActuatorConfig.getInstance().getDrivetrain().movePid(4);
			System.out.println("Fail safe: Hannah, you done messed up. Fix your code!");
		}
			
	}

}