package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonGoForward extends AutonBase
{
	public AutonGoForward()
	{
		super();
	}

	protected void left() //Goes forward
	{

//		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(.1, 0.3);
		ActuatorConfig.getInstance().getDrivetrain().movePid(100);
//		ActuatorConfig.getInstance().getDrivetrain().movePid(-20000);//16
		System.out.println("Position left: Goes forward");
	}

	protected void center() //Has a fail safe so if chosen in game, it'll run center auton.
	{
		if ("LRL".equals(this.gameData) || "LLL".equals(this.gameData))//Runs basic center auton for left switch
		{
//			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8, 45);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);//Go forward 
//			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8,45);
//			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
//			System.out.println("Ya can't go forward unless you give gameData some dumb value. I won't allow it!");		
		}
		else if ("RLR".equals(this.gameData)|| "RRR".equals(this.gameData))//Runs basic center auton for right switch
		{
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8,45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			System.out.println("Ya can't go forward unless you give gameData some dumb value. I won't allow it!");		
		}
		else //This does the right auton so be careful not to run into another robot.
		{
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.8, 45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.8,45);
			ActuatorConfig.getInstance().getDrivetrain().movePid(2);
			System.out.println("Fail Safe: (Hannah fix your code) Ya can't go forward unless you give gameData some dumb value. I won't allow it!");
		}
	}

protected void right() //Goes forward
	{
		ActuatorConfig.getInstance().getDrivetrain().movePid(4);	
		System.out.println("Position right: Goes forward");
	}



}
