package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.robot.Robot;

public class AutonGoForward extends AutonBase
{

	public AutonGoForward(String gameData) {
		super(gameData);
	}

	protected void left() //Goes forward
	{
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);
		System.out.println("Position left: Goes forward");
	}

	protected void center() //Has a fail safe so if chosen in game, it'll run center auton.
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

protected void right() //Goes forward
	{
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);	
		System.out.println("Position right: Goes forward");
	}



}
