package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonGoForward extends AutonBase{

	protected void left(String gameData) //Goes forward
	{
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);
		System.out.println("Position left: Goes forward");
	}

	protected void center(String gameData) //Has a fail safe so if chosen in game, it'll run center auton.
	{
		if (gameData == "LRL" || gameData == "LLL")//Runs basic center auton for left switch
		{
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8,45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			System.out.println("Ya can't go forward unless you give gameData some dumb value. I won't allow it!");		
		}
		else if (gameData == "RLR" || gameData == "RRR")//Runs basic center auton for right switch
		{
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8,45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			System.out.println("Ya can't go forward unless you give gameData some dumb value. I won't allow it!");		
		}
		else //Goes forward
		{
			ActuatorConfig.getInstance().getDrivetrain().movePid(4);//NEVER USE THIS ONE IN GAME!!
			System.out.println("Position center: Goes forward (this better not be in game...)");
		}
	}

protected void right(String gameData) //Goes forward
	{
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);	
		System.out.println("Position right: Goes forward");
	}

}
