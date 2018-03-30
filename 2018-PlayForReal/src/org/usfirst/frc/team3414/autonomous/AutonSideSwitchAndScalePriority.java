package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;

public class AutonSideSwitchAndScalePriority extends AutonBase
{
	public AutonSideSwitchAndScalePriority() 
	{
		super();
	}

	
	protected void left()
	{
		if("LRL".equals(this.gameData)) //switch
		{
			AutonUtility.deliverSideSwitch(true);
		}
		else if ("LLL".equals(this.gameData)) //scale
		{
			AutonUtility.deliverSideScale(true);
		}
		else if ("RLR".equals(this.gameData)) //scale
		{
			AutonUtility.deliverSideScale(true);
		}
		else if ("RRR".equals(this.gameData)) //drive forward
		{
			ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(60);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(18, .35);
		}
		else
		{
			System.out.println("I'm a little confused");
		}
		
	}

	
	protected void center()
	{
		System.out.println("I'm a little confused");
	}

	
	protected void right()
	{
		if("RLR".equals(this.gameData)) //switch
		{
			AutonUtility.deliverSideSwitch(false);
		}
		else if ("RRR".equals(this.gameData)) //scale
		{
			AutonUtility.deliverSideScale(false);
		}
		else if ("LRL".equals(this.gameData)) //scale
		{
			AutonUtility.deliverSideScale(false);
		}
		else if ("LLL".equals(this.gameData)) //drive forward
		{
			ActuatorConfig.getInstance().getDrivetrain().setInitialServoPosition();
			ActuatorConfig.getInstance().getDrivetrain().lowerAnglerTo(60);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(18, .35);
		}
		else
		{
			System.out.println("I'm a little confused");
		}
	}
	

}
